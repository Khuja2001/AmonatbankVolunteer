package khuja.example.dao;

import khuja.example.model.*;

public interface AmonatbankVolunteerDao {

    void createAmonatMobileClient(AmonatMobileClient amonatMobileClient);
    void createInternetBankingClient(InternetBankingClient internetBankingClient);
    void createPosClient(PosClient posClient);
    void createQrClient(QrClient qrClient);
    void createSmsNotificationsClient(SmsNotificationsClient smsNotificationsClient);
    void createVolunteers(Volunteers volunteers);
    String findByLoginVolunteer(String login);
    void uploadPhoto(Photo photo);

}
