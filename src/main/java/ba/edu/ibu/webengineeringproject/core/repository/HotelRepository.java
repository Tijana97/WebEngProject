package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String > {

    @Query(value = "{'name': {$regex: ?0, $options:  'i'}}")
    List<Hotel> findAllByName(String name);


    @Query(value = "{'city': {$regex: ?0, $options:  'i'}}")
    List<Hotel> findByCity(String city);

}
