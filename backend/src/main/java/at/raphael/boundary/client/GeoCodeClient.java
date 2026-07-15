package at.raphael.boundary.client;

import at.raphael.boundary.dto.GeoCodeResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "apistax-api")
@ClientHeaderParam(name = "Authorization", value = "Bearer ${apistax-api-key}")
public interface GeoCodeClient {

    /***
     * APIStax API: Getting GeoCode From Address Query
     * @param query Address String
     * @return GeoCode Response
     */
    @GET
    @Path("geocode/search")
    Response getGeoCodeFromAddress(@QueryParam("query") String query);

}
