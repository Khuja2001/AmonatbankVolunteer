package khuja.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")

public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImage")
    private int idImage;

    @Column(name = "idAmonatMobileClient")
    public Integer idAmonatMobileClient;

    @Column(name = "type")
    public Integer type;

    @Column(name = "filePath")
    public String filePath;

    // Геттеры и сеттеры


    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdAmonatMobileClient(Integer idAmonatMobileClient) {
        this.idAmonatMobileClient = idAmonatMobileClient;
    }

    public Integer getIdAmonatMobileClient() {
        return idAmonatMobileClient;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setFilePath(String imagePath) {
        this.filePath = imagePath;
    }

    public String getFilePath() {
        return filePath;
    }
}

