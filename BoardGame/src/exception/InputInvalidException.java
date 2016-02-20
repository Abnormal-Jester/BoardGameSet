package exception;

public class InputInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InputInvalidException() {
		super();
	}

	public InputInvalidException(String message) {
		super(message);
	}
}
