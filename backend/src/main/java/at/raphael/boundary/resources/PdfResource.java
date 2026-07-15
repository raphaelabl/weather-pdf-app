package at.raphael.boundary.resources;

import at.raphael.boundary.client.GeoCodeClient;
import at.raphael.boundary.client.WeatherForecastClient;
import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.boundary.dto.HtmlToPdfDTO;
import at.raphael.boundary.dto.WeatherForecastResponseDTO;
import at.raphael.service.GeoCodeService;
import at.raphael.service.WeatherForecastService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("pdf")
public class PdfResource {

    @RestClient
    GeoCodeClient geoCodeClient;

    @POST
    @Produces("application/pdf")
    public Response getWeatherPdfFromAddress(String htmlContent){
        HtmlToPdfDTO htmlToPdfDTO = new HtmlToPdfDTO(htmlContent);

        Response response = geoCodeClient.getPdfFromHTML(htmlToPdfDTO);

        byte[] pdf = response.readEntity(byte[].class);

        return Response.ok(pdf, "application/pdf")
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"document.pdf\""
                )
                .header(
                        HttpHeaders.CONTENT_LENGTH,
                        pdf.length
                )
                .build();

    }

}
