package at.raphael.service;

import at.raphael.boundary.client.GeoCodeClient;
import at.raphael.boundary.dto.GeoCodeResponseDTO;
import at.raphael.boundary.dto.GeoCodeResponseDTO.PositionResponseDTO;
import at.raphael.boundary.dto.GeoCodeResponseDTO.AddressResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GeoCodeService {

    @RestClient
    GeoCodeClient geoCodeClient;

    public GeoCodeResponseDTO getGeoCodeFromAddress(String address) {
        //return geoCodeClient.getGeoCodeFromAddress(address);

        return new GeoCodeResponseDTO(
                new PositionResponseDTO(48.10845F,13.62274F),
                new AddressResponseDTO("Mühlbachweg", "18", "4901", "Ottnang am Hausruck", "Österreich", "AT")
        );
    }

}
