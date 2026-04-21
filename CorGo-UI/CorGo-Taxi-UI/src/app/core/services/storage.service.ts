import { Injectable } from '@angular/core';
import { Preferences } from '@capacitor/preferences';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  async setItem(key: string, value: string): Promise<void> {
    try {
      await Preferences.set({ key, value });
    } catch (error) {
      console.error('Error setting item in storage:', error);
    }
  }

  getItem(key: string): string | null {
    // For development, fallback to localStorage
    return localStorage.getItem(key);
  }

  async removeItem(key: string): Promise<void> {
    try {
      await Preferences.remove({ key });
    } catch (error) {
      console.error('Error removing item from storage:', error);
    }
    localStorage.removeItem(key);
  }

  async clear(): Promise<void> {
    try {
      await Preferences.clear();
    } catch (error) {
      console.error('Error clearing storage:', error);
    }
    localStorage.clear();
  }
}
