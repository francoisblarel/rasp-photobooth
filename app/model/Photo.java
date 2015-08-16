package model;

import java.util.Date;

/**
 * @author fblarel
 *         Date: 10/05/15
 */
public class Photo {

    private String message;
    private String author;
    private Date date;
    private String email;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "message=" + this.getMessage()
                + ",author="+this.getAuthor()
                + ",date="+this.getDate();

    }
}
