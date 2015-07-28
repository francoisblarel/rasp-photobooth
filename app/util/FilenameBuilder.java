package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author fblarel
 *         Date: 28/07/15
 */
public class FilenameBuilder {

    public static String buildPictureName(final String author){
        return "/" + LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + "-" + author + ".jpg";

    }

}
