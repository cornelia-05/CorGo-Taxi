import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RideRequest, NearbyLocation } from '../models/location.model';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  // Ride endpoints
  createRideRequest(request: RideRequest): Observable<RideRequest> {
    return this.http.post<RideRequest>(`${this.apiUrl}/rides/request`, request);
  }

  getRideHistory(): Observable<RideRequest[]> {
    return this.http.get<RideRequest[]>(`${this.apiUrl}/rides/history`);
  }

  cancelRide(rideId: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/rides/${rideId}/cancel`, {});
  }

  // Location endpoints
  getNearbyLocations(
    latitude: number,
    longitude: number,
    radius: number = 5000,
  ): Observable<NearbyLocation[]> {
    return this.http.get<NearbyLocation[]>(
      `${this.apiUrl}/locations/nearby?lat=${latitude}&lng=${longitude}&radius=${radius}`,
    );
  }

  getAddressSuggestions(query: string, latitude?: number, longitude?: number): Observable<any[]> {
    let url = `${this.apiUrl}/locations/suggestions?q=${query}`;
    if (latitude && longitude) {
      url += `&lat=${latitude}&lng=${longitude}`;
    }
    return this.http.get<any[]>(url);
  }

  reverseGeocode(latitude: number, longitude: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/locations/reverse?lat=${latitude}&lng=${longitude}`);
  }
}
