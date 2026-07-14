package at.raphael.boundary.dto;

public record GeoCodeResponseDTO(
        PositionResponseDTO position,
        AddressResponseDTO address
) {
}

