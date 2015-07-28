package controllers;

import com.dropbox.core.DbxException;
import model.Photo;
import util.Constants;
import util.FilenameBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author fblarel
 *         Date: 08/07/15
 */
public class PhotoService {

    private PictureSaver pictureSaver;
    private DropboxService dropboxService;

    public PhotoService() {
        this.pictureSaver = new MockPictureSaver();
//        this.pictureSaver = new TempPictureSaver();

        this.dropboxService = new DropboxService();
    }

    public void  takePicture() throws IOException, InterruptedException {
        pictureSaver.takePicture();
    }

    public void savePicture(final Photo photo) throws IOException {
        Path tmpPicturePath = Paths.get(Constants.TMP_IMG_PATH);
        Path pictureTarget = Paths.get(Constants.FINAL_IMG_PATH + FilenameBuilder.buildPictureName(photo.getAuthor()));
//        Files.move(tmpPicturePath, pictureTarget, StandardCopyOption.REPLACE_EXISTING);

        // save picture to dropbox asynchronously
        Thread a = new Thread(() -> {
            try {
                dropboxService.upload(FilenameBuilder.buildPictureName(photo.getAuthor()));
            } catch (DbxException |IOException e) {
                e.printStackTrace();
            }
        });
        a.start();


    }
}
