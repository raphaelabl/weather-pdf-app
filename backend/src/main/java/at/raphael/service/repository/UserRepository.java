package at.raphael.service.repository;

import at.raphael.boundary.dto.UserRegisterDTO;
import at.raphael.boundary.dto.UserResponseDTO;
import at.raphael.entity.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.Objects;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

    @Inject
    Logger log;

    public User registerUser(UserRegisterDTO user){
        User registeredUser = getUserByEmailOrUsername(user.email(), user.username());

        if(registeredUser == null){
            registeredUser = new User(user.username(), user.password(), user.email());
            persist(registeredUser);
        }

        return registeredUser;
    }

    public User loginUser(UserRegisterDTO user){

        User persistedUser = getUserByEmailOrUsername(user.email(), user.username());

        if (persistedUser == null) {
            log.info("User not found");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        if (!BcryptUtil.matches(user.password(), persistedUser.passwordHash)) {
            log.warn("Invalid password hash");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        return persistedUser;
    }

    public User getUserByEmailOrUsername(String email, String username){
        return find("email = ?1 or username = ?2", email, username).firstResult();
    }

}
