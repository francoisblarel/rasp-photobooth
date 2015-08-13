package service;

import java.io.IOException;

/**
 * @author fblarel
 *         Date: 07/07/15
 */
public interface PictureSaver {

    void takePicture() throws IOException, InterruptedException;

}
