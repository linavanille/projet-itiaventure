package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Exception de base pour toutes les erreurs liées à un vivant.
 */
public class VivantException extends ITIAventureException {

	/**
	 * Crée une exception avec un message.
	 */
	public VivantException(String message) {
		super(message);
	}

	/**
	 * Crée une exception avec un message et une cause.
	 */
	public VivantException(String message, Throwable cause) {
		super(message, cause);
	}
}

