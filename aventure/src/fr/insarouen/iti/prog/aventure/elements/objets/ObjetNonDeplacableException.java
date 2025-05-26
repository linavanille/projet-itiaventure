package fr.insarouen.iti.prog.aventure.elements.objets;

/**
 * Exception levée lorsqu'on essaie de déplacer un objet qui ne peut pas l'être.
 */
public class ObjetNonDeplacableException extends ObjetException {

	/**
	 * Crée une exception avec un message d'erreur.
	 *
	 * @param message message expliquant l'erreur.
	 */
	public ObjetNonDeplacableException(String message) {
		super(message);
	}

	/**
	 * Crée une exception avec un message et une cause.
	 *
	 * @param message message expliquant l'erreur.
	 * @param cause cause sous-jacente de l'erreur.
	 */
	public ObjetNonDeplacableException(String message, Throwable cause) {
		super(message, cause);
	}
}

