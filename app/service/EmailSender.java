package service;

import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;

/**
 * @author fblarel
 *         Date: 14/08/15
 */
public class EmailSender {

    public static final String SUBJECT = "subject";

    public void sendMail(final String dest){
        Email email = new Email();

        email.setSubject(SUBJECT);
        email.setFrom("fblarel.spam@gmail.com");
        email.addTo(dest);
        email.setBodyText("Coucou, voici la photo prise");

        MailerPlugin.send(email);
    }

}
