package xiaolan.daigou.model.exception;

public class DaigouException extends Exception {
	
    private static final long serialVersionUID = 7396387896678144779L;

    /**
     * Constructeur par défaut.
     * 
     */
    public DaigouException() {
        super();
    }

    /**
     * Constructeur.
     * 
     * @param message
     *            message d'erreur
     */
    public DaigouException(final String message) {
        super(message);
    }

    /**
     * Constructeur.
     * 
     * @param message
     *            message d'erreur
     * @param e
     *            exception
     */
    public DaigouException(final String message, final Throwable e) {
        super(message, e);
    }
}
