package khuja.example.dao.impl;

import khuja.example.dao.AmonatbankVolunteerDao;
import khuja.example.dao.repository.*;
import khuja.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Primary
@Component
public class AmonatbankVolunteerDaoImpl2 implements AmonatbankVolunteerDao {

    @Autowired
    private AmonatMobileClientRepository amonatMobileClientRepository;

    @Autowired
    private InternetBankingClientRepository internetBankingClientRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PosClientRepository posClientRepository;

    @Autowired
    private QrClientRepository qrClientRepository;

    @Autowired
    private SmsNotificationsClientRepository smsNotificationsClientRepository;


    @Override
    public void createAmonatMobileClient(AmonatMobileClient amonatMobileClient) {
        amonatMobileClientRepository.save(amonatMobileClient);
    }

    @Override
    public void createInternetBankingClient(InternetBankingClient internetBankingClient) {
        internetBankingClientRepository.save(internetBankingClient);
    }

    @Override
    public void createPosClient(PosClient posClient) {
        posClientRepository.save(posClient);
    }

    @Override
    public void createQrClient(QrClient qrClient) {
        qrClientRepository.save(qrClient);
    }

    @Override
    public void createSmsNotificationsClient(SmsNotificationsClient smsNotificationsClient) {
        smsNotificationsClientRepository.save(smsNotificationsClient);
    }
    @Override
    public void uploadPhoto(Photo photo) {
        photoRepository.save (photo);
    }
}
