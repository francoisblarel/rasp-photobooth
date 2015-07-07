package controllers;

import model.Photo;
import play.Play;
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
    public static final String TEMPFILE2 = "/public/images/";
    public static final String TEMP_PICTURE_PNG = "tempPicture.png";

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
     * Page de livre d'or sur laquelle on peut prendre une photo et écrire un message.
     * @return
     */
    public static Result livreDOr(){
        Photo photo = new Photo();
        photo.setAuthor("toto");
        Form<Photo> photoForm = Form.form(Photo.class).fill(photo);

        try {
            takePictureMock();
        } catch (AWTException e) {
            e.printStackTrace();
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
        File publicDir = Play.application().getFile(TEMPFILE2);
        File file = new File(publicDir.getAbsolutePath() + "/" + TEMP_PICTURE_PNG);
        ImageIO.write(image,"png", file);
        System.out.println("Capture screen saved in " + file.getAbsolutePath());
    }

}
