package mvc.exception;

/**
 * Custom checked exception to handle Title duplication.
 */
public class DuplicateTitleException extends Exception {

    public DuplicateTitleException(String title) {
        super("Already presented title: " + title);
    }

}
