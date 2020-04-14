package pl.poloch.imgfighter.services.users;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import pl.poloch.imgfighter.database_models.StandardModel;
import pl.poloch.imgfighter.repositories.UserRepository;

import java.nio.charset.StandardCharsets;

public class UserController extends StandardModel {

    private long id;
    private String nickname = "";
    private String password = "";
    private String email = "";
    private String name;
    private String surname;


    public Boolean nicknameAndEmailExist(UserRepository repository){
        return ((repository.findByEmail(email).size() > 0) || (repository.findByNickname(nickname).size() > 0));
    }

    public void hashPassword() {
        password =  Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public Boolean isNickname() {
        return !(StringUtils.isBlank(nickname));
    }

    public Boolean isEmail() {
        return !(StringUtils.isBlank(email));
    }

    public Boolean isPassword() {
        return !(StringUtils.isBlank(password));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}