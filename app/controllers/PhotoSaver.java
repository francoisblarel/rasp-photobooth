package controllers;

import java.io.IOException;

/**
 * @author fblarel
 *         Date: 08/07/15
 */
public class PhotoSaver {

    private PictureSaver pictureSaver;

    public PhotoSaver() {
        this.pictureSaver = new MockPictureSaver();
    }

    public void  takePicture() throws IOException {
        pictureSaver.takePicture();
    }
}
