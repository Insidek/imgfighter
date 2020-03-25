package pl.poloch.imgfighter.controllers.users;

import com.google.common.hash.Hashing;
import pl.poloch.imgfighter.database_models.TimeStamp;
import pl.poloch.imgfighter.repositories.UserRepository;

import java.nio.charset.StandardCharsets;


public class UserController extends TimeStamp {


    private long id;
    private String nickname = "";
    private String password = "";
    private String email = "";
    private String name;
    private String surname;

    public boolean requiredFieldsNotNull() {
        if (nickname.isEmpty()) { return false; }
        else if (password.isEmpty()) { return false; }
        else if (email.isEmpty()) { return false; }
        else { return true; }
    }

    public Boolean nicknameAndEmailExist(UserRepository repository){
        if (repository.findByNickname(nickname).size() > 0) { return true; }
        else if (repository.findByEmail(email).size() > 0) { return true; }
        else { return false; }
    }

    public void hashPassword() {
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        password = sha256hex;
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