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
import java.util.Random;

/**
 * @author fblarel
 *         Date: 07/07/15
 */
public class TempPictureSaver implements PictureSaver {

    public static final String CMD = Play.application().configuration().getString("raspistill.cmd");
    public static final String ROTATION_CMD = " --rotation ";
    public static final String NORMAL_ROTATION = "270";


    private String[] hardcoreRotate = new String[]{
            "90 ", "0 -hf ", "0 -vf ", " 0 -vf -hf ", "90 -hf ", "90 -vf", "90 -hf -vf"
    };


    @Override
    public void takePicture(final String mode) throws IOException, InterruptedException {
        String command = CMD + ROTATION_CMD;
        if(mode != null){
            Random random = new Random();
            String rotation = hardcoreRotate[random.nextInt(hardcoreRotate.length-1)];
            command += rotation;
        }else{
            command += NORMAL_ROTATION;
        }
        Process proc = Runtime.getRuntime().exec(command);

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
