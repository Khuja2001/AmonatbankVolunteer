package khuja.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "amonat_mobile_clients")

public class AmonatMobileClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amonat_mobile_client")
    public int idAmonatMobileClient;

    @Column(name = "id_volunteer")
    public int idVolunteer;

    @Column(name = "name_amonat_mobile_client", nullable = false)
    public String nameAmonatMobileClient;

    @Column(name = "surname_amonat_mobile_client", nullable = false)
    public String surnameAmonatMobileClient;

    @Column(name = "patronymic_amonat_mobile_client")
    public String patronymicAmonatMobileClient;

    @Column(name = "sex_amonat_mobile_client", nullable = false)
    public String sexAmonatMobileClient;

    @Column(name = "phone_amonat_mobile_client", nullable = false)
    public String phoneAmonatMobileClient;

    @Column(name = "tin_amonat_mobile_client", nullable = false)
    public String tinAmonatMobileClient;

    @Column(name = "email_amonat_mobile_client")
    public String emailAmonatMobileClient;

    @Column(name = "birthday_amonat_mobile_client", nullable = false)
    public String birthdayAmonatMobileClient;



    public int getIdAmonatMobileClient() {
        return idAmonatMobileClient;
    }

    public void setIdAmonatMobileClient(int idAmonatMobileClient) {
        this.idAmonatMobileClient = idAmonatMobileClient;
    }

    public int getIdVolunteer() {
        return idVolunteer;
    }

    public void setIdVolunteer(int idVolunteer) {
        this.idVolunteer = idVolunteer;
    }

    public String getNameAmonatMobileClient() {
        return nameAmonatMobileClient;
    }

    public void setNameAmonatMobileClient(String nameAmonatMobileClient) {
        this.nameAmonatMobileClient = nameAmonatMobileClient;
    }

    public String getSurnameAmonatMobileClient() {
        return surnameAmonatMobileClient;
    }

    public void setSurnameAmonatMobileClient(String surnameAmonatMobileClient) {
        this.surnameAmonatMobileClient = surnameAmonatMobileClient;
    }

    public String getPatronymicAmonatMobileClient() {
        return patronymicAmonatMobileClient;
    }

    public void setPatronymicAmonatMobileClient(String patronymicAmonatMobileClient) {
        this.patronymicAmonatMobileClient = patronymicAmonatMobileClient;
    }

    public String getSexAmonatMobileClient() {
        return sexAmonatMobileClient;
    }

    public void setSexAmonatMobileClient(String sexAmonatMobileClient) {
        this.sexAmonatMobileClient = sexAmonatMobileClient;
    }

    public String getPhoneAmonatMobileClient() {
        return phoneAmonatMobileClient;
    }

    public void setPhoneAmonatMobileClient(String phoneAmonatMobileClient) {
        this.phoneAmonatMobileClient = phoneAmonatMobileClient;
    }

    public String getTinAmonatMobileClient() {
        return tinAmonatMobileClient;
    }

    public void setTinAmonatMobileClient(String tinAmonatMobileClient) {
        this.tinAmonatMobileClient = tinAmonatMobileClient;
    }

    public String getEmailAmonatMobileClient() {
        return emailAmonatMobileClient;
    }

    public void setEmailAmonatMobileClient(String emailAmonatMobileClient) {
        this.emailAmonatMobileClient = emailAmonatMobileClient;
    }

    public String getBirthdayAmonatMobileClient() {
        return birthdayAmonatMobileClient;
    }

    public void setBirthdayAmonatMobileClient(String birthdayAmonatMobileClient) {
        this.birthdayAmonatMobileClient = birthdayAmonatMobileClient;
    }
}