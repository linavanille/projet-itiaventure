package fr.insarouen.iti.prog.aventure.elements.structure;

public class PorteInexistanteDansLaPieceException extends PieceException {

	/**
	 * Exception si une porte n'existe pas dans une pièce donnée.
	 */
	public PorteInexistanteDansLaPieceException(String message) {
		super(message);
	}

	public PorteInexistanteDansLaPieceException(String message, Throwable cause) {
		super(message, cause);
	}
}

