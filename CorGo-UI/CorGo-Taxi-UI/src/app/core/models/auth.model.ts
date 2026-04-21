export interface AuthState {
  isAuthenticated: boolean;
  user: any;
  token: string | null;
  loading: boolean;
  error: string | null;
}
