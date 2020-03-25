package pl.poloch.imgfighter.database_models;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserModel extends TimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, columnDefinition = "text")
    private String password;

    @Column(nullable = false)
    private String email;

    @Column()
    private String name;

    @Column()
    private String surname;

    public UserModel(String nickname, String password, String email, String name, String surname) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public UserModel() {
    }

    public UserModel(String nickname) {
        super();
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
