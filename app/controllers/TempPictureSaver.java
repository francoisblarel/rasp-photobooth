package controllers;

import java.io.IOException;

/**
 * @author fblarel
 *         Date: 07/07/15
 */
public class TempPictureSaver implements PictureSaver {

    @Override
    public void takePicture() throws IOException{
        //TODO voir avec Raspberry.
        Process proc = Runtime.getRuntime().exec("ls");
    }
}
