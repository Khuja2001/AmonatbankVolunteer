package khuja.example.dao.repository;

import khuja.example.model.QrClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrClientRepository extends CrudRepository<QrClient, Long> {
}
