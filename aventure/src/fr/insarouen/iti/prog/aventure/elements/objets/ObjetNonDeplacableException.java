package fr.insarouen.iti.prog.aventure.elements.objets;

/**
 * Exception lancée lorsqu'un objet ne peut pas être déplacé.
 * <p>
 * Cette exception est utilisée pour indiquer qu'une tentative de déplacement d'un objet
 * qui n'est pas déplaçable a échoué. Elle hérite de {@link ObjetException}, et permet de
 * spécifier un message d'erreur et une cause sous-jacente.
 * </p>
 */
public class ObjetNonDeplacableException extends ObjetException{
    
    /**
     * Constructeur pour créer une exception avec un message.
     * <p>
     * Ce constructeur permet de créer une exception en spécifiant un message d'erreur
     * expliquant pourquoi l'objet ne peut pas être déplacé.
     * </p>
     * 
     * @param message le message d'erreur expliquant pourquoi l'objet ne peut pas être déplacé.
     */
    public ObjetNonDeplacableException (String message){
        super(message);
    }

    /**
     * Constructeur pour créer une exception avec un message et une cause.
     * <p>
     * Ce constructeur permet de créer une exception avec un message d'erreur et une
     * cause sous-jacente (une autre exception). Cela permet de chaîner des exceptions
     * pour fournir plus de détails sur l'origine de l'erreur.
     * </p>
     * 
     * @param message le message d'erreur expliquant pourquoi l'objet ne peut pas être déplacé.
     * @param cause la cause sous-jacente de l'exception (peut être une autre exception).
     */
    public ObjetNonDeplacableException (String message, Throwable cause){
        super(message,cause);
    }
}
