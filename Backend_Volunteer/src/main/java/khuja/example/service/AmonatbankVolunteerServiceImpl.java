package khuja.example.service;

import jakarta.transaction.Transactional;
import khuja.example.dao.impl.AmonatbankVolunteerDaoImpl;
import khuja.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class AmonatbankVolunteerServiceImpl implements AmonatbankVolunteerService{

    @Autowired
    AmonatbankVolunteerDaoImpl amonatbankVolunteerDao;

    @Transactional
    @Override
    public void createAmonatMobileClient(AmonatMobileClient amonatMobileClient) {
        amonatbankVolunteerDao.createAmonatMobileClient(amonatMobileClient);
    }

    @Transactional
    @Override
    public void createInternetBankingClient(InternetBankingClient internetBankingClient) {
        amonatbankVolunteerDao.createInternetBankingClient(internetBankingClient);
    }

    @Transactional
    @Override
    public void createPosClient(PosClient posClient) {
        amonatbankVolunteerDao.createPosClient(posClient);
    }

    @Transactional
    @Override
    public void createQrClient(QrClient qrClient) {
        amonatbankVolunteerDao.createQrClient(qrClient);
    }

    @Transactional
    @Override
    public void createSmsNotificationsClient(SmsNotificationsClient smsNotificationsClient) {
        amonatbankVolunteerDao.createSmsNotificationsClient(smsNotificationsClient);
    }


    @Transactional
    @Override
    public void uploadPhoto(MultipartFile file, int idAmonatMobileClient, int type) {
        String fullPath;
        try {
            if (!file.isEmpty()) {
                String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                fullPath = "C:/Users/Husniddinkhuja/Desktop/imagesStorage/" + uniqueFileName;

                file.transferTo(new File(fullPath));

                Photo photo = new Photo();
                photo.setFilePath(fullPath);
                photo.setIdAmonatMobileClient(idAmonatMobileClient);
                photo.setType(type);
                    amonatbankVolunteerDao.uploadPhoto(photo);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}