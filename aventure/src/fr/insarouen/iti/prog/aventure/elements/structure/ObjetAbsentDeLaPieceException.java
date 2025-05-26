package fr.insarouen.iti.prog.aventure.elements.structure;

public class ObjetAbsentDeLaPieceException extends PieceException {

	/**
	 * Exception levée si un objet est introuvable dans une pièce.
	 */
	public ObjetAbsentDeLaPieceException(String message) {
		super(message);
	}

	/**
	 * Exception avec cause.
	 */
	public ObjetAbsentDeLaPieceException(String message, Throwable cause) {
		super(message, cause);
	}
}

