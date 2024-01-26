package khuja.example.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ){
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (DuplicateKeyException e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User with this email already exists", e
            );
        } catch (DataIntegrityViolationException e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data integrity violation", e
            );
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error", e
            );
        }
    }
    @PostMapping("/authenticate")
    public AuthResponse authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return service.authenticate(request);
    }
}
