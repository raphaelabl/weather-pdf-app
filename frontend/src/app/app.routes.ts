import { Routes } from '@angular/router';
import {WeatherForecast} from './components/weather-forecast/weather-forecast';

export const routes: Routes = [
  {path: "**", component: WeatherForecast}
];
