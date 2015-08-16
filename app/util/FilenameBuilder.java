package util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author fblarel
 *         Date: 28/07/15
 */
public class FilenameBuilder {

    public static final String INCONNU = "inconnu";
    public static final String FILE_TYPE = "jpg";

    public static String buildPictureName(String author){
        if(StringUtils.isEmpty(author)){
            author = INCONNU;
        }
        return LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + "-" + author + "." + FILE_TYPE;

    }

}
