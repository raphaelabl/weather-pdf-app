import {
  ApplicationConfig,
  inject,
  PLATFORM_ID,
  provideAppInitializer,
  provideBrowserGlobalErrorListeners
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay, withNoIncrementalHydration } from '@angular/platform-browser';
import {AuthService} from './services/auth-service';
import {firstValueFrom} from 'rxjs';
import {HTTP_INTERCEPTORS, provideHttpClient} from '@angular/common/http';
import {isPlatformBrowser} from '@angular/common';

function initializeAuth(authService: AuthService) {
  return () => firstValueFrom(authService.loadSession())
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideClientHydration(withEventReplay(), withNoIncrementalHydration()),
    provideHttpClient(),

    provideAppInitializer(() => {
      const platformId = inject(PLATFORM_ID);
      const authService = inject(AuthService);
      if (!isPlatformBrowser(platformId)) {
        console.log('SSR: Sessionprüfung übersprungen');
        return;
      }
      console.log('Browser: Sessionprüfung startet');
      return firstValueFrom(authService.loadSession());
    })
  ]
};
