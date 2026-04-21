export interface Location {
  latitude: number;
  longitude: number;
  address?: string;
  timestamp?: Date;
}

export interface NearbyLocation {
  id: string;
  name: string;
  address: string;
  latitude: number;
  longitude: number;
  category: string;
}

export interface RideRequest {
  id?: string;
  passengerId: string;
  pickupLocation: Location;
  dropoffLocation: Location;
  pickupAddress: string;
  dropoffAddress: string;
  rideType: 'economy' | 'comfort' | 'premium';
  status?: string;
  createdAt?: Date;
}
