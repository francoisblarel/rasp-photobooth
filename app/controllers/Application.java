package controllers;

import model.Photo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import service.PhotoService;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Application extends Controller {

    public static final String IMAGE_MIME_TYPE = "image/jpg";
    private static PhotoService photoService = new PhotoService();

    /**
     * Page d'accueil
     * @return
     */
    public static Result index(final String message) {
        return ok(views.html.index.render(message));
    }


    /**
     * Page de livre d'or sur laquelle on peut prendre une photo et écrire un message.
     * @return
     */
    public static Result livreDOr(final String mode){
        Photo photo = new Photo();
        Form<Photo> photoForm = Form.form(Photo.class).fill(photo);
        try {
           photoService.takePicture(mode);
        } catch (IOException | InterruptedException e) {
            return badRequest(e.getLocalizedMessage());
        }
        return ok(views.html.livredor.render(photoForm));
    }


    /**
     * Sauvegarde du formulaire.
     * @return
     */
    public static Result submitForm(){
        Form<Photo> photoForm = Form.form(Photo.class).bindFromRequest();
        Photo photo = photoForm.get();
        try {
            photoService.savePicture(photo);
        } catch (IOException e) {
            return internalServerError(e.getLocalizedMessage());
        }
        return play.mvc.Results.redirect("/?s=Hop, dans la boite. Merci!");
    }


    /**
     * Retourne l'image temporaire actuelle.
     * @return
     */
    public static Result getTmpImage(){
        try {
            File image = new File(Constants.TMP_IMG_PATH);
            BufferedImage bimage  = ImageIO.read(image);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "jpg", baos);

            return ok(baos.toByteArray()).as(IMAGE_MIME_TYPE);
        } catch (IOException e) {
            return notFound();
        }
    }


    public static Result getRandomMessage(){
        Random random = new Random();
        String text = Constants.phrases.get(random.nextInt(Constants.phrases.size()));
        return ok(text).as("text");
    }


}
