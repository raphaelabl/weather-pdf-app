package at.raphael.boundary.resources;

import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.service.GeoCodeService;
import at.raphael.service.HtmlService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("html")
public class HtmlResource {

    @Inject
    HtmlService htmlService;

    @Inject
    GeoCodeService geoCodeService;

    /***
     * Getting HTML of WeatherForecast from an Address
     * @param address Address of WeatherForecast
     * @return HTML-String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getHtmlFromAddress(@QueryParam("query") String address) {
        String htmlContent = htmlService.getWeatherHTMLFromAddress(address);
        return Response.ok(htmlContent).build();
    }

}
