package at.raphael.boundary.dto;

public record GeoCodeResponseDTO(
        int status,
        PositionResponseDTO position,
        AddressResponseDTO address
) {
    //Default value of status
    public GeoCodeResponseDTO{
        if(status == 0) status = 200;
    }

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

