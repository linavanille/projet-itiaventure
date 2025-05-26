package fr.insarouen.iti.prog.aventure.elements;

/**
 * Exception levée lorsqu'une activation d'un élément échoue parce qu'un objet invalide a été utilisé.
 * Cette exception est utilisée pour signaler que l'activation d'un élément du jeu a échoué car l'objet utilisé pour l'activation n'est pas  valide.
 * 
 */
public class ActivationImpossibleAvecObjetException extends ActivationImpossibleException {

	/**
	 * Constructeur de l'exception avec un message d'erreur.
	 *
	 * @param message le message d'erreur expliquant la cause de l'exception.
	 */
	public ActivationImpossibleAvecObjetException(String message) {
		super(message);
	}

	/**
	 * Constructeur de l'exception avec un message d'erreur et une cause.
	 *
	 * @param message le message d'erreur expliquant la cause de l'exception.
	 * @param cause la cause de l'exception.
	 */
	public ActivationImpossibleAvecObjetException(String message, Throwable cause) {
		super(message, cause);
	}
}

