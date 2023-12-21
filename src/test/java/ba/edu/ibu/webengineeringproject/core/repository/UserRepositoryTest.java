package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldReturnAllUsers(){
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(5, users.size());
    }

    @Test
    public void shouldFindUserByEmailAddress(){
        Optional<User> user = userRepository.findFirstByEmailAddress("guest@test.com");
        Assertions.assertNotNull(user.orElse(null));
    }
}
