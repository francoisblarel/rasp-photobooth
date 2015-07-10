package controllers;

import model.Photo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.io.IOException;

public class Application extends Controller {

    /**
     * Page d'accueil
     * @return
     */
    public static Result index() {
        return ok(index.render("Your new application is maybe ready."));
    }


    /**
     * Page de livre d'or sur laquelle on peut prendre une photo et écrire un message.
     * @return
     */
    public static Result livreDOr(){
        PhotoSaver photoSaver = new PhotoSaver();
        Photo photo = new Photo();
        photo.setAuthor("toto");
        Form<Photo> photoForm = Form.form(Photo.class).fill(photo);
        try {
           photoSaver.takePicture();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok(views.html.livredor.render(photoForm));
    }



    public static Result submitForm(){
        Form<Photo> photoForm = Form.form(Photo.class).bindFromRequest();
        Photo photo = photoForm.get();
        System.out.println(photo.toString());
        return ok("ok");
    }


}
