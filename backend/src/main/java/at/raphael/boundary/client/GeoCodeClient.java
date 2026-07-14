package at.raphael.boundary.client;

import at.raphael.boundary.dto.GeoCodeResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "apistax-api")
@ClientHeaderParam(name = "Authorization", value = "Bearer ${apistax-api-key}")
public interface GeoCodeClient {

    @GET
    @Path("geocode/search")
    GeoCodeResponseDTO getGeoCodeFromAddress(@QueryParam("query") String query);

}
