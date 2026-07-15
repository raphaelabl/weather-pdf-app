import {ChangeDetectorRef, Component, SecurityContext} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
import {Http} from '../../services/http';

@Component({
  selector: 'app-weather-forecast',
  imports: [
    FormsModule
  ],
  templateUrl: './weather-forecast.html',
  styleUrl: './weather-forecast.css',
})
export class WeatherForecast {
  addressStruct: {
    street: string,
    houseNumber: string,
    postalCode: string,
    city: string,
    country: string,
  } = {
    street: "",
    houseNumber: "",
    postalCode: "",
    city: "",
    country: ""
  }

  forecasts: SafeHtml[] = []

  constructor(private http: Http, private sanitizer: DomSanitizer, private cdr: ChangeDetectorRef) { }

  getForecast(){
    var addressString = `${this.addressStruct.street} ${this.addressStruct.houseNumber}, ${this.addressStruct.postalCode} ${this.addressStruct.city}`;
    //addressString = addressString + (this.addressStruct.country)? `, ${this.addressStruct.country}` : '';

    console.log(addressString);

    this.http.getHTMLForecastFromAddress(addressString).subscribe({
      next: data => {

        this.forecasts = [...this.forecasts, this.sanitizer.bypassSecurityTrustHtml(data)];

        this.cdr.markForCheck();
      },
      error: err => {
        console.log(err);
      }
    })
  }

  downloadAsPdf(forecast: SafeHtml) {
    var htmlString = this.sanitizer.sanitize(SecurityContext.HTML, forecast);

    if(!htmlString){
      return;
    }

    this.http.getPdfFromForecast(htmlString).subscribe({
      next: data => {
        const url = URL.createObjectURL(data);

        const link = document.createElement('a');
        link.href = url;
        link.download = 'document.pdf';

        document.body.appendChild(link);
        link.click();
        link.remove();

        URL.revokeObjectURL(url);
      },
      error: err => {
        console.log(err);
      }
    });
  }

}
