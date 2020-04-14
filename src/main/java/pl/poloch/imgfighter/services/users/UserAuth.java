package pl.poloch.imgfighter.services.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import pl.poloch.imgfighter.repositories.UserRepository;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;


@JsonIgnoreProperties(
        value = { "password", "nickname" },
        allowSetters = true
)
public class UserAuth implements Serializable {

    private String nickname = "";
    private String password = "";
    private String token;

    public void hashPassword() {
        password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public Boolean isNickname() {
        return !(StringUtils.isBlank(nickname));
    }

    public Boolean isPassword() {
        return !(StringUtils.isBlank(password));
    }

    public Boolean existUser(UserRepository repository) {
        return (repository.findByNickname(nickname).size() > 0);
    }

    public Boolean verifyPassword(UserRepository repository) {
        hashPassword();
        return (repository.findByNickname(nickname).get(0).getPassword().equals(password));
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
