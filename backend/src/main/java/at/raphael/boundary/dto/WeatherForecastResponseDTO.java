package at.raphael.boundary.dto;

import java.time.LocalDateTime;
import java.util.List;

public record WeatherForecastResponseDTO(
        int status,
        CurrentWeatherDTO current,
        HourlyWeatherDTO hourly
) {

    //Default value of status
    public WeatherForecastResponseDTO{
        if (status == 0) status = 200;
    }

    public record CurrentWeatherDTO(
            LocalDateTime time,
            Integer interval,
            Float temperature_2m,
            Float wind_speed_10m
    ) {}

    public record HourlyWeatherDTO(
            List<LocalDateTime> time,
            List<Float> temperature_2m,
            List<Float> relative_humidity_2m,
            List<Float> wind_speed_10m
    ){}
}
