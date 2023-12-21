package ba.edu.ibu.webengineeringproject.rest.controllers;

import ba.edu.ibu.webengineeringproject.core.model.User;
import ba.edu.ibu.webengineeringproject.core.model.enums.UserType;
import ba.edu.ibu.webengineeringproject.core.service.JWTService;
import ba.edu.ibu.webengineeringproject.core.service.UserService;
import ba.edu.ibu.webengineeringproject.rest.configuration.SecurityConfiguration;
import ba.edu.ibu.webengineeringproject.rest.dto.UserDTO;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
@Import(SecurityConfiguration.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JWTService jwtService;

    @MockBean
    UserService userService;

    @MockBean
    AuthenticationProvider authenticationProvider;

    @Test
    public void shouldReturnAllUsers() throws Exception {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmailAddress("TestUser");
        user.setUsername("testUser");
        user.setPassword("TestBest");
        user.setUserType(UserType.CLIENT);
        user.setAddress("Test Address");
        user.setAuthorized(true);

        UserDTO userDTO = new UserDTO(user);

        Mockito.when(userService.getUsers()).thenReturn(List.of(userDTO));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(1, (Integer) JsonPath.read(response, "$.length()"));
    }

}
