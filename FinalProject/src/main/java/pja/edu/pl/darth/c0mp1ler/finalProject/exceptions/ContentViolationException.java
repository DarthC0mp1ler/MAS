package pja.edu.pl.darth.c0mp1ler.finalProject.exceptions;

/**
 * Unchecked Exception that is thrown when something is not according to the content rules of the system
 */
public class ContentViolationException extends RuntimeException{
    /**
     * Constructor
     * @param message message to be shown
     */
    public ContentViolationException(String message) {
        super(message);
    }
}
