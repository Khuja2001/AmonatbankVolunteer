package khuja.example.auth;


import khuja.example.config.JwtService;
import khuja.example.user.Role;
import khuja.example.user.User;
import khuja.example.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){

        if (userRepository.existsByLogin(request.getLogin())) {
            throw new DuplicateKeyException("User with this email already exists");
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .patronymic(request.getPatronymic())
                .birthday(request.getBirthday())
                .phone(request.getPhone())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthenticationRequest request) {
        try {

            System.out.println("Attempting authentication for user: " + request.getLogin());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        System.out.println("Authentication successful for user: " + request.getLogin());

        var user = userRepository.findByLogin(request.getLogin())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        String nameSurname = user.getFirstname() + " " + user.getLastname();
        Integer id = user.getId();
        String phone = user.getPhone();

            System.out.println(id + " " + phone);
            return new AuthResponse(true, jwtToken, nameSurname, id, phone);
    } catch (UsernameNotFoundException ex) {

        System.out.println("User not found: " + request.getLogin());
        throw ex;
    } catch (Exception e) {

        System.out.println("Authentication failed for user: " + request.getLogin());
        throw e;
    }
    }
}