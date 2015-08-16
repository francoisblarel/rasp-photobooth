package service;

import play.Logger;
import play.Play;
import util.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author fblarel
 *         Date: 07/07/15
 */
public class TempPictureSaver implements PictureSaver {

    public static final String CMD = Play.application().configuration().getString("raspistill.cmd");


    @Override
    public void takePicture() throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec(CMD);
        proc.waitFor();

        Path pathToMove = Paths.get(Constants.TMP_IMG_PATH);

        File toMove = new File("bin/image.jpg");
        Path capture = Paths.get(toMove.getAbsolutePath());

        Logger.info("image capture : " + capture.toString());
        Logger.info("target : " + pathToMove.toString());

        Files.move(capture, pathToMove, StandardCopyOption.REPLACE_EXISTING);

        Logger.info("Capture screen saved in " + pathToMove.toString());


    }
}
