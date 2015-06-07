package model;

import play.data.validation.Constraints;

import java.util.Date;

/**
 * @author fblarel
 *         Date: 10/05/15
 */
public class Photo {

    private String message;
    @Constraints.Required
    private String author;
    private byte[] photo;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
}
