package service;

import com.dropbox.core.*;
import play.Logger;
import play.Play;
import util.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * Service for Dropbox access.
 * @author fblarel
 * Date: 28/07/15
 *
 */
public class DropboxService {

    private String code = Play.application().configuration().getString("dropbox.secret");

    DbxRequestConfig config = new DbxRequestConfig("test app fbl", Locale.getDefault().toString());
    DbxClient client;

    public DropboxService(){
        // Connect to Dropbox account.
        client = new DbxClient(config, code);
        try {
            Logger.info("Linked account: " + client.getAccountInfo().displayName);
        } catch (DbxException e) {
            Logger.info("No linked account, authentication failed!!");
        }
    }

    /**
     * Upload file to Dropbox
     * @throws IOException
     * @throws DbxException
     */
    public void upload(final String pictureName) throws IOException, DbxException {
        File inputFile = new File(Constants.TMP_IMG_PATH);

        try ( FileInputStream inputStream = new FileInputStream(inputFile)) {
            DbxEntry.File uploadedFile = client.uploadFile("/" + pictureName, DbxWriteMode.add(), inputFile.length(), inputStream);
            Logger.info("Uploaded: " + uploadedFile.toString());
        }catch (Exception e){
            Logger.error("Impossible d'uploader sur Dropbox. " + e.getMessage());
        }
    }



}
