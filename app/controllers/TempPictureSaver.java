package controllers;

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


    @Override
    public void takePicture() throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec("raspistill -o image.jpg");
        proc.waitFor();

        Path pathToMove = Paths.get(Constants.TMP_IMG_PATH);

        File toMove = new File("bin/image.jpg");
        Path capture = Paths.get(toMove.getAbsolutePath());

        System.out.println("image capture : " + capture.toString());
        System.out.println("target : " + pathToMove.toString());

        Files.move(capture, pathToMove, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Capture screen saved in " + pathToMove.toString());


    }
}
