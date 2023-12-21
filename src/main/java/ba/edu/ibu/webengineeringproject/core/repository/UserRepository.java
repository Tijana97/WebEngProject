package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findFirstByEmailAddress(String emailPattern);

    @Query(value="{'$or':[{email:'?0'}, {username:'?0'}]}")
    Optional<User> findByUsernameOrEmailAddress(String username);

    Optional<User> findFirstByUsername(String username);
}
