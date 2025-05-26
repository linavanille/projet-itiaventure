package fr.insarouen.iti.prog.aventure.elements;

/**
 * Exception levée lorsqu'une activation d'un élément échoue pour des raisons générales.
 *
 * Cette exception est utilisée pour signaler que l'activation d'un élément du jeu (comme une porte, un objet,
 * ou un mécanisme) a échoué pour des raisons générales qui ne sont pas spécifiquement liées à un objet particulier.
 *
 */
public class ActivationImpossibleException extends ActivationException {

	/**
	 * Constructeur de l'exception avec un message d'erreur.
	 *
	 * @param message le message d'erreur expliquant la cause de l'exception.
	 */
	public ActivationImpossibleException(String message) {
		super(message);
	}

	/**
	 * Constructeur de l'exception avec un message d'erreur et une cause.
	 *
	 * @param message le message d'erreur expliquant la cause de l'exception.
	 * @param cause la cause de l'exception (une autre exception qui a conduit à celle-ci).
	 */
	public ActivationImpossibleException(String message, Throwable cause) {
		super(message, cause);
	}
}

