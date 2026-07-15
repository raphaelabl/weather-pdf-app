package at.raphael.service;

import at.raphael.boundary.client.GeoCodeClient;
import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.boundary.dto.GeoCodeResponseDTO.PositionResponseDTO;
import at.raphael.boundary.dto.GeoCodeResponseDTO.AddressResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GeoCodeService {

    @RestClient
    GeoCodeClient geoCodeClient;

    /***
     * Calling the ApiStax API to get the geocode from an address
     * @param address Address as string
     * @return GeoCodeResponseDTO or Status code if not found
     */
    public GeoCodeResponseDTO getGeoCodeFromAddress(String address) {
        /*Response response = geoCodeClient.getGeoCodeFromAddress(address);

        if(response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(GeoCodeResponseDTO.class);
        }

        return new GeoCodeResponseDTO(response.getStatus(), null, null);*/

        return new GeoCodeResponseDTO(
                Response.Status.OK.getStatusCode(),
                new PositionResponseDTO(52.52f, 13.419998f),
                new AddressResponseDTO("Mühlbachweg", "18", "4901", "Ottnang", "Austria", "AT")
        );
    }

}
