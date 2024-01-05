package khuja.example.model;


import jakarta.persistence.*;

@Entity
@Table(name = "internet_banking_clients")
public class InternetBankingClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_internet_banking_client")
    private int idIBClient;

    @Column(name = "id_volunteer")
    public int idVolunteer;

    @Column(name = "name_internet_banking_client")
    private String nameIBClient;

    @Column(name = "address_internet_banking_client")
    private String addressIBClient;

    @Column(name = "tin_internet_banking_client")
    private String tinIBClient;

    @Column(name = "phone_number_director_internet_banking_client")
    private String phoneNumDirectorIBClient;

    @Column(name = "phone_number_chief_accountant_internet_banking_client")
    private String phoneNumChiefAccountantIBClient;

    // Геттеры и сеттеры

    public int getIdIBClient() {
        return idIBClient;
    }

    public void setIdIBClient(int idIBClient) {
        this.idIBClient = idIBClient;
    }

    public int getIdVolunteer() {
        return idVolunteer;
    }

    public void setIdVolunteer(int idVolunteer) {
        this.idVolunteer = idVolunteer;
    }

    public String getNameIBClient() {
        return nameIBClient;
    }

    public void setNameIBClient(String nameIBClient) {
        this.nameIBClient = nameIBClient;
    }

    public String getAddressIBClient() {
        return addressIBClient;
    }

    public void setAddressIBClient(String addressIBClient) {
        this.addressIBClient = addressIBClient;
    }

    public String getTinIBClient () {
        return tinIBClient;
    }

    public void setTinIBClient(String tinIBClient) {
        this.tinIBClient = tinIBClient;
    }

    public String getPhoneNumDirectorIBClient() {
        return phoneNumDirectorIBClient;
    }

    public void setPhoneNumDirectorIBClient(String phoneNumDirectorIBClient) {
        this.phoneNumDirectorIBClient = phoneNumDirectorIBClient;
    }

    public String getPhoneNumChiefAccountantIBClient() {
        return phoneNumChiefAccountantIBClient;
    }

    public void setPhoneNumChiefAccountantIBClient(String phoneNumChiefAccountantIBClient) {
        this.phoneNumChiefAccountantIBClient = phoneNumChiefAccountantIBClient;
    }

}
