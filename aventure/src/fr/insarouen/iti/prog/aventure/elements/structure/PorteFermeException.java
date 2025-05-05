package fr.insarouen.iti.prog.aventure.elements.structure;

/**
 * Exception levée lorsqu'une tentative d'interaction avec une porte fermée échoue.
 * <p>
 * Cette exception est utilisée pour signaler que l'on a tenté d'interagir avec une porte qui est fermée, 
 * ce qui peut se produire par exemple lors d'une tentative d'ouverture d'une porte verrouillée.
 * </p>
 */
public class PorteFermeException extends ElementStructurelException{

    /**
     * Constructeur pour créer une nouvelle exception avec un message.
     * 
     * @param message le message décrivant l'exception.
     */
    public PorteFermeException(String message){
        super(message);
    }
    
    /**
     * Constructeur pour créer une nouvelle exception avec un message et une cause.
     * 
     * @param message le message décrivant l'exception.
     * @param cause la cause de l'exception (une autre exception qui a conduit à cette erreur).
     */
    public PorteFermeException(String message, Throwable cause){
        super(message,cause);
    }
}