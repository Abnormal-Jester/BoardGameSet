package exception;

/**
 * This exception occurs when the the board fails to place a piece where the
 * user indicated.
 */
public class PlacementFailedException extends RuntimeException {
	/** Default serial Version UID */
	private static final long serialVersionUID = 1L;

	public PlacementFailedException() {
		super();
	}

	public PlacementFailedException(String message) {
		super(message);
	}
}