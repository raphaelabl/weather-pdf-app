package at.raphael.boundary.dto;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedMap;

import static io.quarkus.rest.client.reactive.runtime.BasicAuthUtil.headerValue;

public record CurrentUser(String id, String username, String email, String source) {
    public static CurrentUser fromHeaders(HttpHeaders headers) {
        return fromHeaderValues(headers.getRequestHeaders());
    }

    public static CurrentUser fromHeaderValues(MultivaluedMap<String, String> headers) {
        return new CurrentUser(
                headerValue(headers, "X-User-Id"),
                headerValue(headers, "X-User-Name"),
                headerValue(headers, "X-User-Email"),
                headerValue(headers, "X-User-Source")
        );
    }

    private static String headerValue(MultivaluedMap<String, String> headers, String name) {
        var values = headers.get(name);
        if (values == null || values.isEmpty() || values.get(0) == null || values.get(0).isBlank()) {
            return "";
        }
        return values.get(0);
    }
}
