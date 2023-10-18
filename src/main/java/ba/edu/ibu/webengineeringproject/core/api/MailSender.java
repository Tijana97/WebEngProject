package ba.edu.ibu.webengineeringproject.core.api;

import ba.edu.ibu.webengineeringproject.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MailSender {
    public String send(List<User> users, String message);
}
