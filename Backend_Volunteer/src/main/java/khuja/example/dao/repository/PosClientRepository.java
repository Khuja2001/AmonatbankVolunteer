package khuja.example.dao.repository;

import khuja.example.model.PosClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosClientRepository extends CrudRepository<PosClient, Long> {

}