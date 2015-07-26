package controllers;

import model.Photo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Application extends Controller {

    /**
     * Page d'accueil
     * @return
     */
    public static Result index(final String message) {
        return ok(index.render(message));
    }


    /**
     * Page de livre d'or sur laquelle on peut prendre une photo et écrire un message.
     * @return
     */
    public static Result livreDOr(){
        PhotoService photoService = new PhotoService();
        Photo photo = new Photo();
        photo.setAuthor("anonym");
        Form<Photo> photoForm = Form.form(Photo.class).fill(photo);
        try {
           photoService.takePicture();
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        } catch (InterruptedException e) {
            //TODO
            e.printStackTrace();
        }
        return ok(views.html.livredor.render(photoForm));
    }



    public static Result submitForm(){
        Form<Photo> photoForm = Form.form(Photo.class).bindFromRequest();
        Photo photo = photoForm.get();
        System.out.println(photo.toString());
        return index("La photo a bien été enregistrée");
    }


    public static Result getTmpImage(){
        try {
            File image = new File(Constants.TMP_IMG_PATH);
            BufferedImage bimage  = ImageIO.read(image);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "jpg", baos);

            return ok(baos.toByteArray()).as("image/jpg");
        } catch (IOException e) {
            return notFound();
        }
    }

}
