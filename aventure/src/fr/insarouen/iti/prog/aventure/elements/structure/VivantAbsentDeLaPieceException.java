package fr.insarouen.iti.prog.aventure.elements.structure;

public class VivantAbsentDeLaPieceException extends PieceException {

	/**
	 * Exception si on tente une action sur un vivant absent.
	 */
	public VivantAbsentDeLaPieceException(String message) {
		super(message);
	}

	public VivantAbsentDeLaPieceException(String message, Throwable cause) {
		super(message, cause);
	}
}

