package at.raphael.boundary.resources;

import at.raphael.boundary.client.WeatherForecastClient;
import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.boundary.dto.WeatherForecastResponseDTO;
import at.raphael.service.GeoCodeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("pdf")
public class PdfResource {

    @Inject
    GeoCodeService geoCodeService;

    @RestClient
    WeatherForecastClient weatherForecastClient;

    @GET
    @Path("weatherFromAddress")
    public Response getWeatherPdfFromAddress(){
        GeoCodeResponseDTO geoResponseDTO = geoCodeService.getGeoCodeFromAddress("Mühlbachweg 18, 4901 Ottnang");

        if(geoResponseDTO == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        WeatherForecastResponseDTO weatherForecastResponseDTO = weatherForecastClient.getWeatherForecastOfPosition(
                geoResponseDTO.position().latitude(),
                geoResponseDTO.position().longitude(),
                "temperature_2m,wind_speed_10m",
                "temperature_2m,relative_humidity_2m,wind_speed_10m"
        );

        return Response.ok(weatherForecastResponseDTO).build();
    }

}
