package at.raphael.service;

import at.raphael.boundary.client.WeatherForecastClient;
import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.boundary.dto.WeatherForecastResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class WeatherForecastService {

    @RestClient
    private WeatherForecastClient weatherForecastClient;

    /***
     * Calling the open-meteo API to get the weatherforecast of position
     * @param geoCodeResponseDTO GeoCode of the Forecast
     * @return WeatherForecast data or status code if not found
     */
    public WeatherForecastResponseDTO getFromGeoCodeResponseDTO(GeoCodeResponseDTO geoCodeResponseDTO) {

        Response response = weatherForecastClient.getWeatherForecastOfPosition(
                        geoCodeResponseDTO.position().latitude(),
                        geoCodeResponseDTO.position().longitude(),
                        "temperature_2m,wind_speed_10m",
                        "temperature_2m,relative_humidity_2m,wind_speed_10m"
                );

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return response.readEntity(WeatherForecastResponseDTO.class);
        }

        return new WeatherForecastResponseDTO(response.getStatus(), null, null);
    }

}
