package fr.insarouen.iti.prog.aventure.elements.structure;

public class PieceException extends ElementStructurelException {

	/**
	 * Exception liée à un problème dans une pièce.
	 */
	public PieceException(String message) {
		super(message);
	}

	/**
	 * Exception avec cause.
	 */
	public PieceException(String message, Throwable cause) {
		super(message, cause);
	}
}

