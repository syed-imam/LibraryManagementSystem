package library.jdbc;

import library.media.Media;
import library.media.MediaAcademic;
import library.media.MediaBook;
import library.media.MediaMovie;
import java.sql.*;
import java.util.ArrayList;
import library.media.Reservation;

/**
 *
 * @author Adil Imam
 */
public class MediaJdbcClass {

    private Media m = null;
    private Connection con = null;
    private ResultSet rs = null;
    private Statement statement = null;
    private PreparedStatement prepared = null;

    /**
     * Constructor for MediaJdbcClass.
     */
    public MediaJdbcClass() {
    }

    /**
     * This methods adds a new media to the catalog.
     *
     * @param m Media to be added
     * @return true if the insertion was successful or false otherwise
     */
    public boolean addMedia(Media m) {
        return true;
    }

    /**
     * This method edits an existing media in the catalog. Each subtype class
     * will have their own implementation of this method
     *
     * @param editedMedia Media object with all modifications
     * @return true if the edition was successful or false otherwise
     */
    public boolean editMedia(Media editedMedia) {
        return true;
    }

    /**
     * This methods deletes an existing media from the catalog.
     *
     * @param mediaId Media id to delete
     * @return boolean if media was deleted successfully, false otherwise
     */
    public boolean deleteMedia(String mediaId) {
        return true;
    }

    /**
     * This method will perform a search in the catalog.
     *
     * @param attribute attribute that serves as the key for search
     * @param value value typed by user to perform search
     * @return an ArrayList of Media with all entries retrieved from the search
     */
    public ArrayList<Media> searchMedia(String attribute, String value) {
        ArrayList<Media> resultSet = new ArrayList<>();
        return resultSet;
    }

    /**
     * This methods performs a reservation of a media to a patron account.
     *
     * @param patronId Patron Id
     * @param mediaId Media Id to be reserved
     * @return an object of type Reservation
     */
    public Reservation reserveMedia(String patronId, String mediaId) {
        Reservation reserved = new Reservation(patronId, mediaId);
        return reserved;
    }

}
