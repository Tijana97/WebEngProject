package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository(){
        this.users = Arrays.asList(
                new User(1, "Tijana", "Burazorovic", "tijana.burazorovic@stu.ibu.edu.ba", "0038761111111", "Test123!", "Francuske Revolucije 1", false, false, true),
                 new User(2, "Becir", "Isakovic", "becir.isakovic@ibu.edu.ba", "0038761112112", "Test123!", "Francuske Revolucije 2", true, false, true),
                new User(3, "Aldin", "Kovacevic", "aldin.kovacevic@ibu.edu.ba", "0038761113113", "Test123!", "Francuske Revolucije 3", false, true, true)
        );


    }

    public List<User> findAll(){
        return users;
    }

    public User findById(int id){
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

}
