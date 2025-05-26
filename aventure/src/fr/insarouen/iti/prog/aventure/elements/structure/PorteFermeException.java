package fr.insarouen.iti.prog.aventure.elements.structure;

public class PorteFermeException extends ElementStructurelException {

	/**
	 * Exception si on tente une action sur une porte fermée.
	 */
	public PorteFermeException(String message) {
		super(message);
	}

	public PorteFermeException(String message, Throwable cause) {
		super(message, cause);
	}
}

