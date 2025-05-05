package fr.insarouen.iti.prog.aventure.elements;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Exception levée lorsqu'une activation d'un élément échoue dans le jeu.
 * <p>
 * Cette exception est utilisée pour signaler les erreurs qui se produisent lorsqu'un élément
 * du jeu (par exemple, une porte ou un objet) ne peut pas être activé, soit en raison d'une
 * condition spécifique, soit en raison d'une opération invalide.
 * </p>
 */
public class ActivationException extends ITIAventureException{

    /**
     * Constructeur de l'exception avec un message d'erreur.
     * 
     * @param message le message d'erreur expliquant la cause de l'exception.
     */
    public ActivationException(String message){
        super(message);
    }

    /**
     * Constructeur de l'exception avec un message d'erreur et une cause.
     * 
     * @param message le message d'erreur expliquant la cause de l'exception.
     * @param cause la cause de l'exception (une autre exception qui a conduit à celle-ci).
     */
    public ActivationException(String message, Throwable cause){
        super(message,cause);
    }
}