import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, catchError, Observable, of, tap} from 'rxjs';
import {OrySession} from '../model/ory-session';
import {environment} from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {

  constructor(private http: HttpClient) { }

  private readonly sessionSubject = new BehaviorSubject<OrySession | null>(null);

  readonly session$ = this.sessionSubject.asObservable();

  get session(): OrySession | null{
    return this.sessionSubject.value;
  }

  get isAuthenticated(): boolean {
    return this.sessionSubject.value?.active === true;
  }

  loadSession(): Observable<OrySession | null>{
    return this.http.get<OrySession>(environment.oryBrowserUrl + '/sessions/whoami', {withCredentials: true})
      .pipe(
        tap(session => { console.log('Ory-Session:', session);this.sessionSubject.next(session)}),
        catchError(error => {
          console.error('Whoamai fehlgeschlagen:', {status: error.status, error: error.error});
          this.sessionSubject.next(null)
          return of(null)
        }));
  }

  login(returnTo = window.location.origin){
    window.location.href = environment.oryBrowserUrl + '/self-service/login/browser?return_to=' + encodeURIComponent(returnTo);
  }

  register(returnTo = window.location.origin){
    window.location.href = environment.oryBrowserUrl + '/self-service/registration/browser?return_to=' + encodeURIComponent(returnTo);
  }

  logout(returnTo = window.location.origin){
    this.http.get<{logout_url: string}>(environment.oryBrowserUrl + "/self-service/logout/browser", {withCredentials: true})
      .subscribe({
        next: data=> {
          window.location.href = data.logout_url;
        },
        error: () => {
          this.sessionSubject.next(null);
          window.location.href = '/';
        }
      })
  }
}
