package fr.insarouen.iti.prog.aventure.elements.vivants;

/**
 * Exception levée lorsqu'un vivant tente d'utiliser un objet qu'il ne possède pas.
 */
public class ObjetNonPossedeParLeVivantException extends VivantException {

	/**
	 * Crée une exception avec un message.
	 */
	public ObjetNonPossedeParLeVivantException(String message) {
		super(message);
	}

	/**
	 * Crée une exception avec un message et une cause.
	 */
	public ObjetNonPossedeParLeVivantException(String message, Throwable cause) {
		super(message, cause);
	}
}

