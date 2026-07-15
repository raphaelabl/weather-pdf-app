package at.raphael.boundary.client;

import at.raphael.boundary.dto.WeatherForecastResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/forecast")
@RegisterRestClient(configKey = "weather-forecast-api")
public interface WeatherForecastClient {

    /***
     * Getting Weather Forecast of Position
     * @param latitude Latitude of Position
     * @param longitude Longitude of Position
     * @param valuesCurrent Response values from Actual
     * @param valuesHourly Response values from Hourly
     * @return Response; Weather forecast from Position
     */
    @GET
    Response getWeatherForecastOfPosition(
            @QueryParam("latitude") Float latitude,
            @QueryParam("longitude") Float longitude,
            @QueryParam("current") String valuesCurrent,
            @QueryParam("hourly") String valuesHourly
    );
}
