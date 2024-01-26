package khuja.example.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String email);

}
