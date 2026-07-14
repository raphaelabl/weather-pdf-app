package at.raphael.boundary.dto;

public record GeoCodeResponseDTO(
        PositionResponseDTO position,
        AddressResponseDTO address
) {
    public record AddressResponseDTO(
            String street,
            String houseNumber,
            String postalCode,
            String city,
            String country,
            String countryCode
    ){}
    public record PositionResponseDTO(
            Float latitude,
            Float longitude
    ){}

}

