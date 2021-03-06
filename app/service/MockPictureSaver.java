package service;

import play.Logger;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author fblarel
 * Date: 07/07/15
 */
public class MockPictureSaver implements PictureSaver {


    @Override
    public void takePicture(final String mode) throws IOException {
        try {
            Logger.info("mode : " + mode);
            // determine le taille courante du screen
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            //creer le screenshot
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);
            // sauvegarde de l'image vers un fichier "jpg"
            File file = new File(Constants.TMP_IMG_PATH);
            ImageIO.write(image, "jpg", file);
            Logger.info("Capture screen saved in " + file.getAbsolutePath());
        }catch (AWTException awt){
            throw new IOException(awt);
        }
    }

}
