package khuja.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import khuja.example.dao.AmonatbankVolunteerDao;
import khuja.example.model.*;
import org.springframework.stereotype.Repository;


@Repository
public class AmonatbankVolunteerDaoImpl implements AmonatbankVolunteerDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createAmonatMobileClient(AmonatMobileClient amonatMobileClient) {
        entityManager.persist(amonatMobileClient);
    }

    @Override
    public void createInternetBankingClient(InternetBankingClient internetBankingClient) {
        entityManager.persist(internetBankingClient);
    }

    @Override
    public void createPosClient(PosClient posClient) {
        entityManager.persist(posClient);
    }

    @Override
    public void createQrClient(QrClient qrClient) {
        entityManager.persist(qrClient);
    }

    @Override
    public void createSmsNotificationsClient(SmsNotificationsClient smsNotificationsClient) {
        entityManager.persist(smsNotificationsClient);
    }

    @Override
    public void uploadPhoto(Photo photo) {
        entityManager.persist(photo);
    }
}
