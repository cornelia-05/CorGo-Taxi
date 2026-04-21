import { Component, OnInit, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { LocationService } from 'src/app/core/services/location.service';
import { ApiService } from 'src/app/core/services/api.service';
import { Location, NearbyLocation, RideRequest } from 'src/app/core/models/location.model';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

declare let google: any;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
  standalone: true,
  imports: [FormsModule, CommonModule],
})
export class MainComponent implements OnInit, OnDestroy {
  @ViewChild('map') mapElement: ElementRef | undefined;

  map: any;
  currentLocation: Location | null = null;
  pickupAddress = '';
  dropoffAddress = '';
  pickupCoords: Location | null = null;
  dropoffCoords: Location | null = null;
  nearbyLocations: NearbyLocation[] = [];
  suggestions: any[] = [];
  showSuggestions = false;
  showRideDetails = false;
  rideRequested = false;
  isLoading = false;
  errorMessage = '';

  selectedRideType = 'economy';
  rideTypes = [
    { name: 'Economy', value: 'economy', icon: 'assets/car-economy.svg' },
    { name: 'Comfort', value: 'comfort', icon: 'assets/car-comfort.svg' },
    { name: 'Premium', value: 'premium', icon: 'assets/car-premium.svg' },
  ];

  distance = 0;
  estimatedPrice = 0;
  estimatedTime = 0;
  secondsElapsed = 0;

  private destroy$ = new Subject<void>();
  private geocoder: any;
  private currentRideId: string | null = null;

  constructor(
    private authService: AuthService,
    private locationService: LocationService,
    private apiService: ApiService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.initializeMap();
    this.getCurrentLocation();
    this.subscribeToLocationUpdates();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
    this.locationService.stopWatchingLocation();
  }

  private initializeMap(): void {
    // Initialize Google Map
    setTimeout(() => {
      const mapDiv = document.getElementById('map');
      if (mapDiv) {
        this.map = new google.maps.Map(mapDiv, {
          zoom: 15,
          center: { lat: 0, lng: 0 },
          styles: this.getMapStyles(),
          fullscreenControl: false,
          mapTypeControl: false,
          streetViewControl: false,
        });
        this.geocoder = new google.maps.Geocoder();
      }
    }, 500);
  }

  private getMapStyles(): any[] {
    return [
      {
        featureType: 'all',
        elementType: 'geometry.fill',
        stylers: [{ color: '#f5f5f5' }],
      },
      {
        featureType: 'poi',
        elementType: 'labels.text',
        stylers: [{ visibility: 'off' }],
      },
    ];
  }

  private getCurrentLocation(): void {
    this.isLoading = true;
    this.locationService.getCurrentLocation().then(
      (location) => {
        this.currentLocation = location;
        this.pickupCoords = location;
        this.updateMapCenter(location);
        this.reverseGeocodeLocation(location.latitude, location.longitude, 'pickup');
        this.loadNearbyLocations(location);
        this.isLoading = false;
      },
      (error) => {
        this.isLoading = false;
        this.showError('Could not get your location. Please enable location services.');
        console.error('Location error:', error);
      },
    );
  }

  private subscribeToLocationUpdates(): void {
    this.locationService.currentLocation$.pipe(takeUntil(this.destroy$)).subscribe((location) => {
      if (location) {
        this.currentLocation = location;
      }
    });
  }

  private updateMapCenter(location: Location): void {
    if (this.map) {
      const center = { lat: location.latitude, lng: location.longitude };
      this.map.setCenter(center);
    }
  }

  private loadNearbyLocations(location: Location): void {
    this.apiService
      .getNearbyLocations(location.latitude, location.longitude, 2000)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (locations) => {
          this.nearbyLocations = locations;
        },
        error: (error) => {
          console.error('Error loading nearby locations:', error);
        },
      });
  }

  private reverseGeocodeLocation(lat: number, lng: number, type: 'pickup' | 'dropoff'): void {
    this.apiService.reverseGeocode(lat, lng).subscribe({
      next: (response) => {
        if (type === 'pickup') {
          this.pickupAddress = response.address;
        } else {
          this.dropoffAddress = response.address;
        }
      },
      error: (error) => {
        console.error('Reverse geocode error:', error);
      },
    });
  }

  useCurrentLocation(): void {
    if (this.currentLocation) {
      this.pickupCoords = this.currentLocation;
      this.pickupAddress = 'Current Location';
      this.updateMapCenter(this.currentLocation);
    }
  }

  onPickupAddressChange(event: any): void {
    const query = (event.target as HTMLInputElement).value;
    if (query.length > 2) {
      this.getAddressSuggestions(query, true);
    } else {
      this.suggestions = [];
      this.showSuggestions = false;
    }
  }

  onDropoffAddressChange(event: any): void {
    const query = (event.target as HTMLInputElement).value;
    if (query.length > 2) {
      this.getAddressSuggestions(query, false);
    } else {
      this.suggestions = [];
      this.showSuggestions = false;
    }
  }

  private getAddressSuggestions(query: string, isPickup: boolean): void {
    this.apiService
      .getAddressSuggestions(query, this.currentLocation?.latitude, this.currentLocation?.longitude)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (suggestions) => {
          this.suggestions = suggestions;
          this.showSuggestions = true;
        },
        error: (error) => {
          console.error('Error getting suggestions:', error);
        },
      });
  }

  selectSuggestion(suggestion: any): void {
    if (!this.dropoffAddress || this.pickupAddress === '') {
      this.pickupAddress = suggestion.name;
      this.pickupCoords = {
        latitude: suggestion.latitude,
        longitude: suggestion.longitude,
      };
    } else {
      this.dropoffAddress = suggestion.name;
      this.dropoffCoords = {
        latitude: suggestion.latitude,
        longitude: suggestion.longitude,
      };
    }
    this.showSuggestions = false;
    this.suggestions = [];

    if (this.pickupCoords && this.dropoffCoords) {
      this.calculateRoute();
    }
  }

  selectNearbyLocation(location: NearbyLocation): void {
    this.dropoffAddress = location.name;
    this.dropoffCoords = {
      latitude: location.latitude,
      longitude: location.longitude,
    };

    if (this.pickupCoords && this.dropoffCoords) {
      this.calculateRoute();
      this.showRideDetails = true;
    }
  }

  swapLocations(): void {
    const tempAddress = this.pickupAddress;
    const tempCoords = this.pickupCoords;

    this.pickupAddress = this.dropoffAddress;
    this.pickupCoords = this.dropoffCoords;

    this.dropoffAddress = tempAddress;
    this.dropoffCoords = tempCoords;

    if (this.pickupCoords && this.dropoffCoords) {
      this.calculateRoute();
    }
  }

  private calculateRoute(): void {
    if (!this.pickupCoords || !this.dropoffCoords) {
      return;
    }

    this.distance = this.locationService.calculateDistance(
      this.pickupCoords.latitude,
      this.pickupCoords.longitude,
      this.dropoffCoords.latitude,
      this.dropoffCoords.longitude,
    );

    // Simple estimation logic (you can replace with actual API call)
    const basePrice = 3.5;
    const pricePerKm = 1.25;
    this.estimatedPrice = Math.round((basePrice + this.distance * pricePerKm) * 100) / 100;
    this.estimatedTime = Math.max(Math.round(this.distance * 2), 5); // Simple estimation

    this.showRideDetails = true;
  }

  selectRideType(type: string): void {
    this.selectedRideType = type;
    // Adjust price based on ride type
    const multipliers: { [key: string]: number } = {
      economy: 1,
      comfort: 1.5,
      premium: 2,
    };
    this.estimatedPrice = Math.round(this.estimatedPrice * multipliers[type] * 100) / 100;
  }

  requestRide(): void {
    if (!this.pickupCoords || !this.dropoffCoords || !this.currentLocation) {
      this.showError('Please select both pickup and dropoff locations');
      return;
    }

    const currentUser = this.authService.getCurrentUser();
    if (!currentUser) {
      this.showError('User not authenticated');
      return;
    }

    const rideRequest: RideRequest = {
      passengerId: currentUser.id,
      pickupLocation: this.pickupCoords,
      dropoffLocation: this.dropoffCoords,
      pickupAddress: this.pickupAddress,
      dropoffAddress: this.dropoffAddress,
      rideType: this.selectedRideType as 'economy' | 'comfort' | 'premium',
    };

    this.isLoading = true;
    this.apiService.createRideRequest(rideRequest).subscribe({
      next: (response) => {
        this.isLoading = false;
        this.currentRideId = null;
        this.rideRequested = true;
        this.startCountdown();
      },
      error: (error) => {
        this.isLoading = false;
        this.showError(error.error?.message || 'Failed to request ride');
      },
    });
  }

  private startCountdown(): void {
    let seconds = 0;
    const interval = setInterval(() => {
      seconds++;
      this.secondsElapsed = seconds;

      if (seconds > 60) {
        clearInterval(interval);
        this.showError('Request timeout. Please try again.');
        this.rideRequested = false;
      }
    }, 1000);
  }

  cancelRide(): void {
    if (!this.currentRideId) {
      return;
    }

    this.isLoading = true;
    this.apiService.cancelRide(this.currentRideId).subscribe({
      next: () => {
        this.isLoading = false;
        this.rideRequested = false;
        this.currentRideId = null;
        this.secondsElapsed = 0;
        this.showError('Ride cancelled');
      },
      error: (error) => {
        this.isLoading = false;
        this.showError('Failed to cancel ride');
      },
    });
  }

  public getCategoryIcon(category: string): string {
    const icons: { [key: string]: string } = {
      restaurant: 'assets/restaurant-icon.svg',
      hotel: 'assets/hotel-icon.svg',
      shopping: 'assets/shopping-icon.svg',
      hospital: 'assets/hospital-icon.svg',
      airport: 'assets/airport-icon.svg',
    };
    return icons[category] || 'assets/location-marker.svg';
  }

  openProfile(): void {
    this.router.navigate(['/passenger/profile']);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/auth/login']);
  }

  private showError(message: string): void {
    this.errorMessage = message;
    setTimeout(() => {
      this.errorMessage = '';
    }, 5000);
  }
}
