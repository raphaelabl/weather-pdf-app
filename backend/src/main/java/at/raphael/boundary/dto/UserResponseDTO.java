package at.raphael.boundary.dto;

import at.raphael.entity.User;

public record UserResponseDTO(String id, String username, String email) {
    public static UserResponseDTO fromEntity(User entity) {
        return new UserResponseDTO(
                entity.id != null ? entity.id.toHexString() : null,
                entity.username,
                entity.email
        );
    }
}
