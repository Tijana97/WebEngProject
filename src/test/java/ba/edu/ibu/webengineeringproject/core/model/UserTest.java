package ba.edu.ibu.webengineeringproject.core.model;

import ba.edu.ibu.webengineeringproject.core.model.enums.UserType;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserTest {

    @Test
    public void shouldCreateNewUser(){
        User user = new User("userid", "Tijana", "Burazorovic", "tijana@email.com", "+387111111", "Test123!", "test", UserType.valueOf("OWNER"), "tiki", true);

        Assertions.assertEquals("Tijana", user.getFirstName());
        Assertions.assertEquals("Test123!", user.getPassword());
    }

    @Test
    public void shouldCompareUsers(){
        User user1 = new User("userid", "Tijana", "Burazorovic", "tijana@email.com", "+387111111", "Test123!", "test", UserType.valueOf("OWNER"), "tiki", true);

        User user2 = new User("userid", "Tijana", "Burazorovic", "tijana@email.com", "+387111111", "Test123!", "test", UserType.valueOf("OWNER"), "tiki", true);

        AssertionsForInterfaceTypes.assertThat(user1).usingRecursiveComparison().isEqualTo(user2);
    }


}
