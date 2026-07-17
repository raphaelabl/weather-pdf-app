package at.raphael.boundary.resources;

import at.raphael.boundary.dto.UserRegisterDTO;
import at.raphael.boundary.dto.UserResponseDTO;
import at.raphael.entity.User;
import at.raphael.service.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.Objects;

@Path("/auth")
public class AuthenticationResource {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/register")
    public Response register(UserRegisterDTO user) {
        User persistedUser = userRepository.registerUser(user);
        return Response.ok(UserResponseDTO.fromEntity(persistedUser)).build();
    }

    @POST
    @Path("/login")
    public Response login(UserRegisterDTO user) {
        User persistedUser = userRepository.loginUser(user);

        if (Objects.isNull(persistedUser)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(UserResponseDTO.fromEntity(persistedUser)).build();
    }

}
