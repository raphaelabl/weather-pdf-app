package at.raphael.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.bson.types.ObjectId;


@MongoEntity(collection="weather_user")
public class User{

    public ObjectId id;
    public String username;
    public String passwordHash;
    public String email;

    public User(String username, String rawPassword, String email) {
        this.username = username;
        this.email = email;

        hashPassword(rawPassword);
    }

    public User() {}

    public void hashPassword(String rawPassword) {
        this.passwordHash = BcryptUtil.bcryptHash(rawPassword);
    }


}
