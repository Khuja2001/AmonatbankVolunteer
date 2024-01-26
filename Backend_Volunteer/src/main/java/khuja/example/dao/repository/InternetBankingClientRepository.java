package khuja.example.dao.repository;

import khuja.example.model.InternetBankingClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternetBankingClientRepository extends CrudRepository<InternetBankingClient, Long> {

}
