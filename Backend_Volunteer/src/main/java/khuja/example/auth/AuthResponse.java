package khuja.example.auth;

import lombok.Data;

@Data
public class AuthResponse {

    private Boolean condition;
    private String token;
    private String nameSurname_user;
    private Integer id_user;
    private String phone_user;


    public AuthResponse(Boolean condition, String token, String nameSurname_volunteer, Integer id_volunteer, String phone_volunteer) {
        this.condition= condition;
        this.token = token;
        this.nameSurname_user = nameSurname_volunteer;
        this.id_user = id_volunteer;
        this.phone_user = phone_volunteer;
    }
}
