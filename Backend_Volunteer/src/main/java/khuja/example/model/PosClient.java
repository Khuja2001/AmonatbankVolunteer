package khuja.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pos_clients")
public class PosClient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_pos_client")
        private int idPosClient;

        @Column(name = "id_volunteer")
        public int idVolunteer;

        @Column(name = "name_pos_client")
        private String namePosClient;

        @Column(name = "tin_pos_client")
        private String tinPosClient;

        @Column(name = "address_pos_client")
        private String addressPosClient;

        @Column(name = "install_address_pos_client")
        private String installAddressPosClient;

        @Column(name = "phone_num_pos_client")
        private String phoneNumPosClient;


        // Геттеры и сеттеры

        public int getIdPosClient() {
            return idPosClient;
        }

        public void setIdPosClient(int idPosClient) {
            this.idPosClient = idPosClient;
        }

        public int getIdVolunteer() {
            return idVolunteer;
        }

        public void setIdVolunteer(int idVolunteer) {
            this.idVolunteer = idVolunteer;
        }

        public String getNamePosClient() {
            return namePosClient;
        }

        public void setNamePosClient(String namePosClient) {
            this.namePosClient = namePosClient;
        }


        public String getTinPosClient () {
        return tinPosClient;
    }

        public void setTinPosClient(String tinPosClient) {
        this.tinPosClient = tinPosClient;
        }

        public String getAddressPosClient() {
            return addressPosClient;
        }

        public void setAddressPosClient(String addressPosClient) {
            this.addressPosClient = addressPosClient;
        }

        public String getInstallAddressPosClient() {
        return installAddressPosClient;
    }

        public void setInstallAddressPosClient(String installAddressPosClient) {
        this.installAddressPosClient = installAddressPosClient;
        }

        public String getPhoneNumPosClient() {
            return phoneNumPosClient;
        }

        public void setPhoneNumPosClient(String phoneNumPosClient) {
            this.phoneNumPosClient = phoneNumPosClient;
        }
    }

