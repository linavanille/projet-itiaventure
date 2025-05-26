package fr.insarouen.iti.prog.aventure.elements.structure;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Exception de base pour toutes les erreurs liées aux éléments structurels.
 */
public class ElementStructurelException extends ITIAventureException {

	/**
	 * Crée une exception avec un message.
	 *
	 * @param message description de l'erreur.
	 */
	public ElementStructurelException(String message) {
		super(message);
	}

	/**
	 * Crée une exception avec message et cause.
	 *
	 * @param message description de l'erreur.
	 * @param cause cause sous-jacente.
	 */
	public ElementStructurelException(String message, Throwable cause) {
		super(message, cause);
	}
}

