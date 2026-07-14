package at.raphael.boundary.dto;

public record AddressResponseDTO(
        String street,
        String houseNumber,
        String postalCode,
        String city,
        String country,
        String countryCode
){}
