export type Role = 'DRIVER' | 'PASSENGER';

export interface User {
  id: string;
  email: string;
  phone: string;
  firstName: string;
  lastName: string;
  role: Role;
  profileImage?: string;
  createdAt: Date;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface SignupRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  phone: string;
  role: Role;
}

export interface AuthResponse {
  token: string;
  user: User;
}
