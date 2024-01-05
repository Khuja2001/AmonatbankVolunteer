package khuja.example.service;

import khuja.example.model.*;
import org.springframework.web.multipart.MultipartFile;


public interface AmonatbankVolunteerService {

    void createAmonatMobileClient(AmonatMobileClient amonatMobileClient);
    void createInternetBankingClient(InternetBankingClient internetBankingClient);
    void createPosClient(PosClient posClient);
    void createQrClient(QrClient qrClient);
    void createSmsNotificationsClient(SmsNotificationsClient smsNotificationsClient);
    void createVolunteers(Volunteers volunteers);
    String findByLoginVolunteer(String login);
    void uploadPhoto(MultipartFile file, int idAmonatMobileClient, int type);

}
