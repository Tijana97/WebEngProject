package ba.edu.ibu.webengineeringproject.api.impl.mailsender;

import ba.edu.ibu.webengineeringproject.core.api.MailSender;
import ba.edu.ibu.webengineeringproject.core.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendgridSender implements MailSender {

    @Override
    public String send(List<User> users, String message){
        for (User user: users){
            System.out.println("Message sent to " + user.getFirstName());
        }
        return "Message:" + message + "  | Sent via Sendgrid.";
    }


}
