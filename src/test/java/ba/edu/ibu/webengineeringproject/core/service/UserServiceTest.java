package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.model.User;
import ba.edu.ibu.webengineeringproject.core.model.enums.UserType;
import ba.edu.ibu.webengineeringproject.core.repository.UserRepository;
import ba.edu.ibu.webengineeringproject.rest.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void shouldReturnUserWHenCrated(){
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmailAddress("TestUser");
        user.setUsername("testUser");
        user.setPassword("TestBest");
        user.setUserType(UserType.CLIENT);
        user.setAddress("Test Address");
        user.setAuthorized(true);

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        UserDTO newUser = userService.addUser(user);
        Assertions.assertEquals(user.getUsername(), newUser.getUsername());
    }


}
