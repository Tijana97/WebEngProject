package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.api.MailSender;
import ba.edu.ibu.webengineeringproject.core.model.User;
import ba.edu.ibu.webengineeringproject.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        return userRepository.findById(id);
    }

    public String sendEmailToAllUsers(String message) {
        List<User> users = userRepository.findAll();
        //return mailgunSender.send(users, message);
        return sendgridSender.send(users,message);
    }

}
