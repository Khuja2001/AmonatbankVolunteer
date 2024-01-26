package khuja.example.dao.repository;

import khuja.example.model.AmonatMobileClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmonatMobileClientRepository extends CrudRepository<AmonatMobileClient, Long> {

}
