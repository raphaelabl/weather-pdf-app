package at.raphael.boundary.client;

import at.raphael.boundary.dto.WeatherForecastResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/forecast")
@RegisterRestClient(configKey = "weather-forecast-api")
public interface WeatherForecastClient {

    @GET
    WeatherForecastResponseDTO getWeatherForecastOfPosition(
            @QueryParam("latitude") Float latitude,
            @QueryParam("longitude") Float longitude,
            @QueryParam("current") String valuesCurrent,
            @QueryParam("hourly") String valuesHourly
    );
}
