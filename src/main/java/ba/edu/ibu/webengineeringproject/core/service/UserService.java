package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.api.MailSender;
import ba.edu.ibu.webengineeringproject.core.exceptions.auth.UserAlreadyExistsException;
import ba.edu.ibu.webengineeringproject.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.webengineeringproject.core.model.User;
import ba.edu.ibu.webengineeringproject.core.repository.UserRepository;
import ba.edu.ibu.webengineeringproject.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private MailSender mailgunSender;

    @Autowired
    private MailSender sendgridSender;

    public  UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

   public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(toList());
   }

    public UserDTO addUser(User payload) {
        Optional<User> userExists = userRepository.findFirstByEmailAddress(payload.getEmailAddress());
        if(userExists.isPresent()){
            throw new UserAlreadyExistsException("User with the provided email already exists");
        }
        User user = userRepository.save(payload);
        return new UserDTO(user);
    }

    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        return new UserDTO(user.get());
    }

    public UserDTO updateUser(String id, User payload) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        Optional<User> emailExists = userRepository.findFirstByEmailAddress(payload.getEmailAddress());
        if(emailExists.isPresent() && !(emailExists.get().getId().equals(payload.getId()))){
            throw new UserAlreadyExistsException("User with the provided email already exists");
        }
        User updatedUser = payload;
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

}
