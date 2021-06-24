package pja.edu.pl.darth.c0mp1ler.finalProject.exceptions;

/**
 * Unchecked exception that is thrown if something that is checked is null or empty
 */
public class NullValidationException extends RuntimeException{
    /**
     * Constructor
     * @param message to be shown
     */
    public NullValidationException(String message) {
        super(message);
    }
}
