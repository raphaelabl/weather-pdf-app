package at.raphael.boundary.dto;

import java.time.LocalDateTime;

public record HourlyWeatherForecast(
        LocalDateTime time,
        Float temperature,
        Float humidity,
        Float wind
) {
}
