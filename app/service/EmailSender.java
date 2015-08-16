package service;

import model.Photo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.Play;
import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;
import util.Constants;
import util.FilenameBuilder;

import java.io.File;

/**
 * @author fblarel
 *         Date: 14/08/15
 */
public class EmailSender {

    public static final String SUBJECT = "Une nouvelle photo a été prise";
    public static final String FROM = Play.application().configuration().getString("from.mail");

    private static final String ADDR_1 = Play.application().configuration().getString("dest.mail1");
    private static final String ADDR_2 = Play.application().configuration().getString("dest.mail2");

    public void sendMail(final Photo photo){

        try {
            Email email = new Email();
            StringBuilder mailContent = new StringBuilder("Hello, Une nouvelle photo a été prise ");
            final String authorName = photo.getAuthor();
            final String authorMail = photo.getEmail();
            final String message = photo.getMessage();

            if(StringUtils.isNotEmpty(authorName)){
                mailContent.append("par ").append(authorName).append(".");
            }
            if(StringUtils.isNotEmpty(message)) {
                mailContent.append("\n")
                        .append("Cette personne vous a laissé un petit message : \n\n")
                        .append(message);
            }
            if (StringUtils.isNotEmpty(authorMail)) {
                mailContent.append("\n")
                        .append("Son adresse mail :").append("\n").append(authorMail);
            }
            mailContent.append("\n").append("Enjoy.");

            email.setSubject(SUBJECT);
            email.setFrom(FROM);

            email.addTo(ADDR_1);
            email.addTo(ADDR_2);

            email.setBodyText(mailContent.toString());
            email.addAttachment(FilenameBuilder.buildPictureName(authorName), new File(Constants.TMP_IMG_PATH));

            MailerPlugin.send(email);
        } catch (Exception e) {
            Logger.error("Impossible d'envoyer un email. " + e.getMessage());
        }
    }

}
