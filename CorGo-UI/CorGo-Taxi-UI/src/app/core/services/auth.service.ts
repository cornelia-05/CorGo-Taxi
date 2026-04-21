import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User, LoginRequest, SignupRequest, AuthResponse } from '../models/user.model';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser$: Observable<User | null>;
  private tokenSubject: BehaviorSubject<string | null>;
  public token$: Observable<string | null>;

  constructor(
    private http: HttpClient,
    private storageService: StorageService,
  ) {
    this.currentUserSubject = new BehaviorSubject<User | null>(
      this.storageService.getItem('currentUser')
        ? JSON.parse(this.storageService.getItem('currentUser')!)
        : null,
    );
    this.currentUser$ = this.currentUserSubject.asObservable();

    this.tokenSubject = new BehaviorSubject<string | null>(this.storageService.getItem('token'));
    this.token$ = this.tokenSubject.asObservable();
  }

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, request).pipe(
      tap((response) => {
        this.storageService.setItem('token', response.token);
        this.storageService.setItem('currentUser', JSON.stringify(response.user));
        this.tokenSubject.next(response.token);
        this.currentUserSubject.next(response.user);
      }),
    );
  }

  signup(request: SignupRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, request).pipe(
      tap((response) => {
        this.storageService.setItem('token', response.token);
        this.storageService.setItem('currentUser', JSON.stringify(response.user));
        this.tokenSubject.next(response.token);
        this.currentUserSubject.next(response.user);
      }),
    );
  }

  logout(): void {
    this.storageService.removeItem('token');
    this.storageService.removeItem('currentUser');
    this.tokenSubject.next(null);
    this.currentUserSubject.next(null);
  }

  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  getToken(): string | null {
    return this.tokenSubject.value;
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
