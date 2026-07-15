package at.raphael.boundary.client;

import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.boundary.dto.HtmlToPdfDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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

    @POST
    @Path("html-to-pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    Response getPdfFromHTML(HtmlToPdfDTO htmlToPdfDTO);

}
