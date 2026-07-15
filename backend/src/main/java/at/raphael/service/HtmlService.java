package at.raphael.service;

import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.boundary.dto.HourlyWeatherForecast;
import at.raphael.boundary.dto.WeatherForecastResponseDTO;
import io.quarkus.qute.Template;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class HtmlService {

    @Inject
    Template temperature;
    @Inject
    Template errorResponse;

    @Inject
    WeatherForecastService weatherForecastService;
    @Inject
    GeoCodeService geoCodeService;

    /***
     * Renders an HTML via QUTE Template from GeoCodeResponseDTO
     * @param address Location information from GeoCode API
     * @return HTML-String of rendered Weather-Forecast Data or Error HTML-String if data could not be found
     */
    public String getWeatherHTMLFromAddress(String address)
    {
        GeoCodeResponseDTO geoCodeResponseDTO = geoCodeService.getGeoCodeFromAddress(address);

        if(geoCodeResponseDTO.status() != 200){
            return errorResponse
                    .data("error-message", "Geo-Data could not be Loaded")
                    .render();
        }

        WeatherForecastResponseDTO weatherForecastResponseDTO = weatherForecastService.getFromGeoCodeResponseDTO(geoCodeResponseDTO);

        if(weatherForecastResponseDTO.status() != 200){
            return errorResponse
                    .data("error-message", "Weather-Data could not be Loaded")
                    .render();
        }

        List<HourlyWeatherForecast> hourlyWeatherForecastList = mapToHourlyWeatherForecastList(weatherForecastResponseDTO);

        // If mapped List is empty, error on Validating data
        if(hourlyWeatherForecastList.isEmpty()){
            return errorResponse
                    .data("error-message", "Weather-Data could not be Loaded Correctly")
                    .render();
        }

        // Put values into Template and render HTML
        return temperature
                .data("addressString", getAddressStringFromGeoCodeAddress(geoCodeResponseDTO.address()))
                .data("currentTime", weatherForecastResponseDTO.current().time().toString())
                .data("latitude", geoCodeResponseDTO.position().latitude())
                .data("longitude", geoCodeResponseDTO.position().longitude())
                .data("currentTemp", weatherForecastResponseDTO.current().temperature_2m())
                .data("currentWind", weatherForecastResponseDTO.current().wind_speed_10m())
                .data("hourlyCount", weatherForecastResponseDTO.hourly().time().size())
                .data("hourlyRows", hourlyWeatherForecastList)
                .data("chartUrl", "")
                .render();
    }

    /***
     * Creates a list of Hourly Weather Forecasts from the Response of the WeatherForecast API
     * @param weatherForecastResponseDTO response of the WeatherForecast API
     * @return List of Hourly Weather Forecasts
     */
    public List<HourlyWeatherForecast> mapToHourlyWeatherForecastList(WeatherForecastResponseDTO weatherForecastResponseDTO){

        // Check if weather Forecast Data is valid
        if(
                weatherForecastResponseDTO.hourly().time().size() != weatherForecastResponseDTO.hourly().temperature_2m().size() ||
                weatherForecastResponseDTO.hourly().time().size() != weatherForecastResponseDTO.hourly().relative_humidity_2m().size() ||
                weatherForecastResponseDTO.hourly().time().size() != weatherForecastResponseDTO.hourly().wind_speed_10m().size()
        ){
            return null;
        }

        // Mapping WeatherForecastDTO to List of HourlyWeatherForecast
        List<HourlyWeatherForecast> hourlyWeatherForecastList = weatherForecastResponseDTO.hourly().time().stream().map(time -> {
            int index = weatherForecastResponseDTO.hourly().time().indexOf(time);
            return new HourlyWeatherForecast(
                    time,
                    weatherForecastResponseDTO.hourly().temperature_2m().get(index),
                    weatherForecastResponseDTO.hourly().relative_humidity_2m().get(index),
                    weatherForecastResponseDTO.hourly().wind_speed_10m().get(index)
            );
        }).toList();

        return hourlyWeatherForecastList;
    }

    /***
     * Creates a Beautiful Address String from the AddressResponseDTO of the GeoCode API
     * @param addressResponseDTO Address Info
     * @return Address String
     */
    public String getAddressStringFromGeoCodeAddress(GeoCodeResponseDTO.AddressResponseDTO addressResponseDTO) {
        return addressResponseDTO.street() + " "
                + addressResponseDTO.houseNumber() + ", "
                + addressResponseDTO.postalCode() + " "
                + addressResponseDTO.city() + ", "
                + addressResponseDTO.countryCode();
    }
}
