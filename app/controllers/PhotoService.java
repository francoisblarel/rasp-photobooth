package controllers;

import java.io.IOException;

/**
 * @author fblarel
 *         Date: 08/07/15
 */
public class PhotoService {

    private PictureSaver pictureSaver;

    public PhotoService() {
//        this.pictureSaver = new MockPictureSaver();
        this.pictureSaver = new TempPictureSaver();
    }

    public void  takePicture() throws IOException, InterruptedException {
        pictureSaver.takePicture();
    }
}
