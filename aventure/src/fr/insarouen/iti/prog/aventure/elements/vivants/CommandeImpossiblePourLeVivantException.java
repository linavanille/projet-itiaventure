package fr.insarouen.iti.prog.aventure.elements.vivants;

/**
 * Exception levée lorsqu'une commande ne peut pas être exécutée par un vivant.
 */
public class CommandeImpossiblePourLeVivantException extends VivantException {

	/**
	 * Crée une exception avec un message.
	 */
	public CommandeImpossiblePourLeVivantException(String message) {
		super(message);
	}

	/**
	 * Crée une exception avec un message et une cause.
	 */
	public CommandeImpossiblePourLeVivantException(String message, Throwable cause) {
		super(message, cause);
	}
}

