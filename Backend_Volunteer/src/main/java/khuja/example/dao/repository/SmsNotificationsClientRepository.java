package khuja.example.dao.repository;

import khuja.example.model.SmsNotificationsClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsNotificationsClientRepository extends CrudRepository<SmsNotificationsClient, Long> {
}
