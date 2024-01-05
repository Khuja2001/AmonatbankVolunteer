package khuja.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sms_notifications_clients")
public class SmsNotificationsClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sms_notifications_client")
    private int idSmsNotificationsClient;

    @Column(name = "id_volunteer")
    public int idVolunteer;

    @Column(name = "name_sms_notifications_client", nullable = false)
    public String nameSmsNotificationsClient;

    @Column(name = "surname_sms_notifications_client", nullable = false)
    public String surnameSmsNotificationsClient;

    @Column(name = "patronymic_sms_notifications_client")
    public String patronymicSmsNotificationsClient;

    @Column(name = "phone_sms_notifications_client", nullable = false)
    public String phoneSmsNotificationsClient;

    @Column(name = "last_four_digit_card_sms_notifications_client", nullable = false)
    public String lastFourDigitCardSmsNotificationsClient;

    @Column(name = "comments_sms_notifications_client")
    public String commentsSmsNotificationsClient;

///геттеры и сеттеры

    public int getIdSmsNotificationsClient() {
        return idSmsNotificationsClient;
    }

    public void setIdSmsNotificationsClient(int idSmsNotificationsClient) {
        this.idSmsNotificationsClient = idSmsNotificationsClient;
    }

    public int getIdVolunteer() {
        return idVolunteer;
    }

    public void setIdVolunteer(int idVolunteer) {
        this.idVolunteer = idVolunteer;
    }

    public String getNameSmsNotificationsClient() {
        return nameSmsNotificationsClient;
    }

    public void setNameSmsNotificationsClient(String nameSmsNotificationsClient) {
        this.nameSmsNotificationsClient = nameSmsNotificationsClient;
    }

    public String getSurnameSmsNotificationsClient() {
        return surnameSmsNotificationsClient;
    }

    public void setSurnameSmsNotificationsClient(String surnameSmsNotificationsClient) {
        this.surnameSmsNotificationsClient = surnameSmsNotificationsClient;
    }

    public String getPatronymicSmsNotificationsClient() {
        return patronymicSmsNotificationsClient;
    }

    public void setPatronymicSmsNotificationsClient(String patronymicSmsNotificationsClient) {
        this.patronymicSmsNotificationsClient = patronymicSmsNotificationsClient;
    }

    public String getPhoneSmsNotificationsClient() {
        return phoneSmsNotificationsClient;
    }

    public void setPhoneSmsNotificationsClient(String phoneSmsNotificationsClient) {
        this.phoneSmsNotificationsClient = phoneSmsNotificationsClient;
    }

    public String getLastFourDigitCardSmsNotificationsClient() {
        return lastFourDigitCardSmsNotificationsClient;
    }

    public void setLastFourDigitCardSmsNotificationsClient(String lastFourDigitCardSmsNotificationsClient) {
        this.lastFourDigitCardSmsNotificationsClient = lastFourDigitCardSmsNotificationsClient;
    }

    public String getCommentsSmsNotificationsClient() {
        return commentsSmsNotificationsClient;
    }

    public void setCommentsSmsNotificationsClient(String commentsSmsNotificationsClient) {
        this.commentsSmsNotificationsClient = commentsSmsNotificationsClient;
    }

}
