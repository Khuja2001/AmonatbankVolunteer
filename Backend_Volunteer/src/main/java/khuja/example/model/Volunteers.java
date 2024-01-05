package khuja.example.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Volunteers")
public class Volunteers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_volunteer")
    private Integer id;

    @Column(name = "name_volunteer", nullable = false)
    private String name;

    @Column(name = "surname_volunteer", nullable = false)
    private String surname;

    @Column(name = "patronymic_volunteer")
    private String patronymic;

    @Column(name = "phone_volunteer", nullable = false)
    private String phone;

    @Column(name = "login_volunteer", unique = true, nullable = false)
    private String login;

    @Column(name = "password_volunteer", nullable = false)
    private String password;

    @Column(name = "birthday_volunteer", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    // Геттеры и сеттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}