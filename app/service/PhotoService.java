package service;

import com.dropbox.core.DbxException;
import model.Photo;
import play.Logger;
import play.Play;
import util.Constants;
import util.FilenameBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author fblarel
 *         Date: 08/07/15
 */
public class PhotoService {

    private PictureSaver pictureSaver;
    private DropboxService dropboxService;
    private static EmailSender emailSender;

    private static final boolean SENDMAIL_ACTIVE = Play.application().configuration().getBoolean("send.mail.active");
    private static boolean DROPBOX_ACTIVE = Play.application().configuration().getBoolean("dropbox.active");
    private static boolean MOCK_PHOTO = Play.application().configuration().getBoolean("mock.photo");

    public PhotoService() {
        if(MOCK_PHOTO){
            this.pictureSaver = new MockPictureSaver();
        }else{
            this.pictureSaver = new TempPictureSaver();
        }
        this.dropboxService = new DropboxService();
        this.emailSender = new EmailSender();
    }

    public void  takePicture(String mode) throws IOException, InterruptedException {
        pictureSaver.takePicture(mode);
    }

    public void savePicture(final Photo photo) throws IOException {
        Path tmpPicturePath = Paths.get(Constants.TMP_IMG_PATH);
        Path pictureTarget = Paths.get(Constants.FINAL_IMG_PATH + "/" + FilenameBuilder.buildPictureName(photo.getAuthor()));

        // save picture in the final dir
        Files.copy(tmpPicturePath, pictureTarget, StandardCopyOption.REPLACE_EXISTING);

        if(DROPBOX_ACTIVE) {
            uploadPhotoToDropbox(photo);
        }

        if(SENDMAIL_ACTIVE) {
            sendPhotoByMail(photo);
        }

    }


    private void sendPhotoByMail(Photo photo) {
        Thread mailThread = new Thread(() -> {
            try{
                emailSender.sendMail(photo);
            }catch (Exception e){
                Logger.error(e.getMessage());
            }
        });
        mailThread.start();
    }


    private void uploadPhotoToDropbox(Photo photo) {
        // save picture to dropbox asynchronously
        Thread dropboxThread = new Thread(() -> {
            try {
                dropboxService.upload("/" + FilenameBuilder.buildPictureName(photo.getAuthor()));
            } catch (DbxException |IOException e) {
                Logger.error(e.getMessage());
            }
        });
        dropboxThread.start();
    }
}
