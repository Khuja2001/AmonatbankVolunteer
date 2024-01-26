package khuja.example.controller;

import khuja.example.model.*;
import khuja.example.service.AmonatbankVolunteerServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping ( "/amonatbankvolunteer" )
public record AmonatbankVolunteerController(AmonatbankVolunteerServiceImpl service) {


    @PostMapping ( "/addNewAmonatMobileClient" )
    public ApiResponse addNewAmonatMobileClient(@RequestBody AmonatMobileClient amonatMobileClient) {
        service.createAmonatMobileClient(amonatMobileClient);
        return new ApiResponse("Client added successfully", amonatMobileClient.getIdAmonatMobileClient());
    }

    @PostMapping ( "/addNewInternetBankingClient" )
    public ApiResponse addNewInternetBankingClient(@RequestBody InternetBankingClient internetBankingClient) {
        service.createInternetBankingClient(internetBankingClient);
        return new ApiResponse("Client added successfully");
    }

    @PostMapping ( "/addNewPhoto" )
    public ApiResponse addNewPhoto(
            @RequestParam MultipartFile file,
            @RequestParam int idAmonatMobileClient,
            @RequestParam int type
    ) {
        service.uploadPhoto(file, idAmonatMobileClient, type);
        return new ApiResponse("Client added successfully");
    }

    @PostMapping ( "/addNewPosClient" )
    public ApiResponse addNewPosClient(@RequestBody PosClient posClient) {
        service.createPosClient(posClient);
        return new ApiResponse("Client added successfully");
    }

    @PostMapping ( "/addNewQrClient" )
    public ApiResponse addNewQrClient(@RequestBody QrClient qrClient) {
        service.createQrClient(qrClient);
        return new ApiResponse("Client added successfully");
    }

    @PostMapping ( "/addNewSmsNotificationsClient" )
    public ApiResponse addNewSmsNotificationsClient(@RequestBody SmsNotificationsClient smsNotificationsClient) {
        service.createSmsNotificationsClient(smsNotificationsClient);
        return new ApiResponse("Client added successfully");
    }
}
