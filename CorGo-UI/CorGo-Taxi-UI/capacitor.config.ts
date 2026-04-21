import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.corgotaxi.app',
  appName: 'CorGo Taxi',
  webDir: 'dist/CorGo-Taxi-UI/browser',
  server: {
    androidScheme: 'https',
  },
  plugins: {
    Geolocation: {
      permissions: ['fine', 'coarse'],
    },
    LocalNotifications: {
      smallIcon: 'ic_stat_icon_config_sample',
      iconColor: '#d4af37',
      sound: 'beep.wav',
    },
  },
};

export default config;
