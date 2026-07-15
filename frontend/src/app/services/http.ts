import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Http {

  BACKEND_API_URL = environment.BACKEND_API_URL;
  API_STAX_URL = environment.API_STAX_URL;

  constructor(private http: HttpClient) { }

  getHTMLForecastFromAddress(address: string): Observable<string>{
    return this.http.get(this.BACKEND_API_URL + "html", {params:{query: address}, responseType: 'text'})
  }

  getPdfFromForecast(forecastHtml: string):Observable<Blob>{
    return this.http.post(
      this.BACKEND_API_URL + 'pdf',
      forecastHtml,
      {
        responseType: 'blob'
      })
  }

}
