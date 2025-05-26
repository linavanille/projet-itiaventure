package fr.insarouen.iti.prog.aventure.elements.objets;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Exception liée aux objets dans le jeu.
 * Utilisée pour signaler des erreurs spécifiques aux objets.
 */
public class ObjetException extends ITIAventureException {

	/**
	 * Crée une exception avec un message d'erreur.
	 *
	 * @param message message expliquant l'erreur.
	 */
	public ObjetException(String message) {
		super(message);
	}

	/**
	 * Crée une exception avec un message et une cause.
	 *
	 * @param message message expliquant l'erreur.
	 * @param cause exception ayant causé cette erreur.
	 */
	public ObjetException(String message, Throwable cause) {
		super(message, cause);
	}
}

