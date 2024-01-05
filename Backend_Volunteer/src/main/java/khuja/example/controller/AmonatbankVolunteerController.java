package khuja.example.controller;

import khuja.example.model.*;
import khuja.example.service.AmonatbankVolunteerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping ( "/amonatbankvolunteer" )
public record AmonatbankVolunteerController(AmonatbankVolunteerServiceImpl service) {


    @PostMapping ( "/addNewAmonatMobileClient" )
    public ResponseEntity<String> addNewAmonatMobileClient(@RequestBody AmonatMobileClient amonatMobileClient) {
        service.createAmonatMobileClient(amonatMobileClient);
        return ResponseEntity.ok("Client added successfully");
    }

    @PostMapping ( "/addNewInternetBankingClient" )
    public ResponseEntity<String> addNewInternetBankingClient(@RequestBody InternetBankingClient internetBankingClient) {
        service.createInternetBankingClient(internetBankingClient);
        return ResponseEntity.ok("Client added successfully");
    }

    @PostMapping ( "/addNewPhoto" )
    public ResponseEntity<String> addNewPhoto(
            @RequestParam MultipartFile file,
            @RequestParam int idAmonatMobileClient,
            @RequestParam int type
    ) {
        service.uploadPhoto(file, idAmonatMobileClient, type);
        return ResponseEntity.ok("Photo added successfully");
    }

    @PostMapping ( "/addNewPosClient" )
    public ResponseEntity<String> addNewPosClient(@RequestBody PosClient posClient) {
        service.createPosClient(posClient);
        return ResponseEntity.ok("Client added successfully");
    }

    @PostMapping ( "/addNewQrClient" )
    public ResponseEntity<String> addNewQrClient(@RequestBody QrClient qrClient) {
        service.createQrClient(qrClient);
        return ResponseEntity.ok("Client added successfully");
    }

    @PostMapping ( "/addNewSmsNotificationsClient" )
    public ResponseEntity<String> addNewSmsNotificationsClient(@RequestBody SmsNotificationsClient smsNotificationsClient) {
        service.createSmsNotificationsClient(smsNotificationsClient);
        return ResponseEntity.ok("Client added successfully");
    }

    @PostMapping ( "/addNewVolunteer" )
    public ResponseEntity<String> addNewVolunteer(@RequestBody Volunteers volunteers) {
        service.createVolunteers(volunteers);
        return ResponseEntity.ok("Volunteer added successfully");
    }

    @GetMapping("/findPasswordByLogin")
    public ResponseEntity<String> findPasswordByLogin(@RequestParam String login) {
        return ResponseEntity.ok(service.findByLoginVolunteer(login));
    }
}
