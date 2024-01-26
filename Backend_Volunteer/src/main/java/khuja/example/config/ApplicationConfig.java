package khuja.example.config;


import khuja.example.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userRepository.findByEmail (username)
//                .orElseThrow(() -> new UsernameNotFoundException("User NOT found!"));
//    }
@Bean
public UserDetailsService userDetailsService() {
    return username -> {
        System.out.println("Looking for user by username: " + username);

        // Добавим отладочную информацию перед вызовом UserRepository
        return userRepository.findByLogin(username)
                .orElseThrow(() -> {
                    System.out.println("User not found for username: " + username);
                    return new UsernameNotFoundException("User not found");
                });
    };
}
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider ();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder (passwordEncoder());
//        return authProvider;
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        // Добавим отладочную информацию перед возвратом AuthenticationProvider
        authProvider.setPostAuthenticationChecks(userDetails -> {
            System.out.println("Authentication successful for user: " + userDetails.getUsername());
        });

        // Добавим отладочную информацию в случае неудачной аутентификации
        authProvider.setPreAuthenticationChecks(userDetails -> {
            System.out.println("Pre-authentication checks for user: " + userDetails.getUsername());
        });

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
