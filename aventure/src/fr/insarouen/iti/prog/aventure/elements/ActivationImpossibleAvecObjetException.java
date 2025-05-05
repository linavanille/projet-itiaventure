package fr.insarouen.iti.prog.aventure.elements;

/**
 * Exception levée lorsqu'une activation d'un élément échoue parce qu'un objet invalide a été utilisé.
 * <p>
 * Cette exception est utilisée pour signaler que l'activation d'un élément du jeu (par exemple, une porte ou
 * un objet) a échoué car l'objet utilisé pour l'activation n'est pas valide, ou ne correspond pas aux critères
 * nécessaires pour effectuer l'activation.
 * </p>
 */
public class ActivationImpossibleAvecObjetException extends ActivationImpossibleException{
    
    /**
     * Constructeur de l'exception avec un message d'erreur.
     * 
     * @param message le message d'erreur expliquant la cause de l'exception.
     */
    public ActivationImpossibleAvecObjetException(String message){
        super(message);
    }

    /**
     * Constructeur de l'exception avec un message d'erreur et une cause.
     * 
     * @param message le message d'erreur expliquant la cause de l'exception.
     * @param cause la cause de l'exception (une autre exception qui a conduit à celle-ci).
     */
    public ActivationImpossibleAvecObjetException(String message, Throwable cause){
        super(message,cause);
    }
}