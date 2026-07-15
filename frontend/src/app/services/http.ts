import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Http {

  constructor(private http: HttpClient) { }

  getHTMLForecastFromAddress(address: string): Observable<string>{
    return this.http.get("http://localhost:8080/html", {params:{query: address}, responseType: 'text'})
  }

}
