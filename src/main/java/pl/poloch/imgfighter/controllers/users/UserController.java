package pl.poloch.imgfighter.controllers.users;


import pl.poloch.imgfighter.database_models.TimeStamp;
import pl.poloch.imgfighter.database_models.UserModel;


public class UserController extends TimeStamp {

    private long id;
    private String nickname;
    private String password;
    private String email;
    private String name;
    private String surname;

    public void addUser(UserModel userModel) {
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