package khuja.example.dao.repository;

import khuja.example.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

}
