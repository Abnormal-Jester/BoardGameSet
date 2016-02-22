package exception;

/**
 * This Exception triggers during runtime when the user inputs an invalid input.
 */
public class InputInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InputInvalidException() {
		super();
	}

	public InputInvalidException(String message) {
		super(message);
	}
}
