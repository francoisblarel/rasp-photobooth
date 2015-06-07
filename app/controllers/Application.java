package controllers;

import model.Photo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application extends Controller {

    public static final String TEMPFILE = "/tmp/tempfile.png";

    /**
     * Page d'accueil
     * @return
     */
    public static Result index() {
        return ok(index.render("Your new application is maybe ready."));
    }

    /**
     * Methode de test
     * @param name
     * @return
     */
//    public static Result test1(final String name){
//        return ok("got request " + request() + "  : name="+ name);
//        return ok(views.html.test.render());
//    }

    /**
     * Page de livre d'or sur laquelle on peut prendre une photo et ecrire un message.
     * @return
     */
    public static Result livreDOr(){
//        return ok("got request " + request() + "  : name="+ name);
        Form<Photo> photoForm = Form.form(Photo.class);

        return ok(views.html.livredor.render(photoForm));
    }



    public static Result submitForm(){
        Form<Photo> photoForm = Form.form(Photo.class).bindFromRequest();
        try {
            takePictureMock();
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
        return ok("ok");
    }

    private static void takePicture() throws IOException {
        Process proc = Runtime.getRuntime().exec("ls");
    }


    private static void takePictureMock() throws AWTException, IOException {
        // determine le taille courante du screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        //creer le screenshot
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRect);
        // sauvegarde de l'image vers un fichier "png"
        ImageIO.write(image,"png", new File(TEMPFILE));
    }

}
