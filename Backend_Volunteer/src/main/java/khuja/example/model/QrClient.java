package khuja.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "qr_clients")
public class QrClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qr_client")
    private int idQrClient;

    @Column(name = "id_volunteer")
    public int idVolunteer;

    @Column(name = "name_qr_client")
    private String nameQrClient;

    @Column(name = "tin_qr_client")
    private String tinQrClient;

    @Column(name = "address_qr_client")
    private String addressQrClient;

    @Column(name = "ryam_qr_client")
    private String ryamQrClient;

    @Column(name = "quote_qr_client")
    private String quoteQrClient;

    @Column(name = "phone_number_director_qr_client")
    private String phoneNumDirectorQrClient;

    @Column(name = "phone_number_chief_accountant_qr_client")
    private String phoneNumChiefAccountantQrClient;

    // Геттеры и сеттеры

    public int getIdQrClient() {
        return idQrClient;
    }

    public void setIdQrClient(int idQrClient) {
        this.idQrClient = idQrClient;
    }

    public int getIdVolunteer() {
        return idVolunteer;
    }

    public void setIdVolunteer(int idVolunteer) {
        this.idVolunteer = idVolunteer;
    }

    public String getNameQrClient() {
        return nameQrClient;
    }

    public void setNameQrClient(String nameQrClient) {
        this.nameQrClient = nameQrClient;
    }

    public String getTinQrClient () {
        return tinQrClient;
    }

    public void setQrPosClient(String tinQrClient) {
        this.tinQrClient = tinQrClient;
    }

    public String getAddressQrClient() {
        return addressQrClient;
    }

    public void setAddressQrClient(String addressQrClient) {
        this.addressQrClient = addressQrClient;
    }

    public String getRyamQrClient() {
        return ryamQrClient;
    }

    public void setRyamQrClient(String ryamQrClient) {
        this.ryamQrClient = ryamQrClient;
    }

    public String getQuoteQrClient() {
        return quoteQrClient;
    }

    public void setQuoteQrClient(String quoteQrClient) {
        this.quoteQrClient = quoteQrClient;
    }

    public String getPhoneNumDirectorQrClient() {
        return phoneNumDirectorQrClient;
    }

    public void setPhoneNumDirectorQrClient(String phoneNumDirectorQrClient) {
        this.phoneNumDirectorQrClient = phoneNumDirectorQrClient;
    }

    public String getPhoneNumChiefAccountantQrClient() {
        return phoneNumChiefAccountantQrClient;
    }

    public void setPhoneNumChiefAccountantQrClient(String phoneNumChiefAccountantQrClient) {
        this.phoneNumChiefAccountantQrClient = phoneNumChiefAccountantQrClient;
    }
}
