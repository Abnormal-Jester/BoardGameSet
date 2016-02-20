package exception;

public class PlacementFailedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlacementFailedException() {
		super();
	}

	public PlacementFailedException(String message) {
		super(message);
	}
}