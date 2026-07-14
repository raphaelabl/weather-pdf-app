package at.raphael.boundary.resources;

import at.raphael.boundary.client.GeoCodeClient;
import at.raphael.boundary.dto.GeoCodeResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("pdf")
public class PdfResource {

    @RestClient
    GeoCodeClient geoCodeClient;

    @GET
    @Path("weatherFromAddress")
    public Response getWeatherPdfFromAddress(){
        GeoCodeResponseDTO geoResponseDTO = geoCodeClient.getGeoCodeFromAddress("Mühlbachweg 18, 4901 Ottnang");
        return Response.ok(geoResponseDTO).build();
    }

}
