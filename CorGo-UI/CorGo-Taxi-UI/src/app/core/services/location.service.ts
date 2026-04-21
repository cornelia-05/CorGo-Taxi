import { Injectable } from '@angular/core';
import { Geolocation } from '@capacitor/geolocation';
import { BehaviorSubject, Observable, interval } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Location } from '../models/location.model';

@Injectable({
  providedIn: 'root',
})
export class LocationService {
  private currentLocationSubject = new BehaviorSubject<Location | null>(null);
  public currentLocation$ = this.currentLocationSubject.asObservable();
  private locationWatchId: string | null = null;

  constructor() {
    this.initializeLocation();
  }

  async initializeLocation(): Promise<void> {
    try {
      const position = await Geolocation.getCurrentPosition();
      this.currentLocationSubject.next({
        latitude: position.coords.latitude,
        longitude: position.coords.longitude,
        timestamp: new Date(),
      });
    } catch (error) {
      console.error('Error getting initial location:', error);
    }
  }

  async getCurrentLocation(): Promise<Location> {
    try {
      const position = await Geolocation.getCurrentPosition();
      const location: Location = {
        latitude: position.coords.latitude,
        longitude: position.coords.longitude,
        timestamp: new Date(),
      };
      this.currentLocationSubject.next(location);
      return location;
    } catch (error) {
      console.error('Error getting current location:', error);
      throw error;
    }
  }

  async startWatchingLocation(): Promise<void> {
    try {
      this.locationWatchId = await Geolocation.watchPosition(
        { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 },
        (position, err) => {
          if (position) {
            this.currentLocationSubject.next({
              latitude: position.coords.latitude,
              longitude: position.coords.longitude,
              timestamp: new Date(),
            });
          }
        },
      );
    } catch (error) {
      console.error('Error watching location:', error);
    }
  }

  async stopWatchingLocation(): Promise<void> {
    if (this.locationWatchId) {
      await Geolocation.clearWatch({ id: this.locationWatchId });
      this.locationWatchId = null;
    }
  }

  calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
    const R = 6371; // Earth's radius in km
    const dLat = this.toRad(lat2 - lat1);
    const dLon = this.toRad(lon2 - lon1);
    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.toRad(lat1)) *
        Math.cos(this.toRad(lat2)) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c;
  }

  private toRad(value: number): number {
    return (value * Math.PI) / 180;
  }
}
