package fr.insarouen.iti.prog.aventure.elements.objets;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Exception spécifique aux erreurs liées aux objets dans le jeu.
 * <p>
 * Cette classe permet de lancer des exceptions spécifiques lorsque des erreurs surviennent lors de la gestion des objets
 * dans le monde du jeu. Elle hérite de {@link ITIAventureException}, une exception générique pour le jeu.
 * </p>
 */
public class ObjetException extends ITIAventureException{

    /**
     * Constructeur pour créer une exception avec un message.
     * <p>
     * Ce constructeur permet de créer une exception en précisant un message d'erreur.
     * </p>
     * 
     * @param message le message d'erreur associé à l'exception.
     */
    public ObjetException(String message){
        super(message);
    }

    /**
     * Constructeur pour créer une exception avec un message et une cause.
     * <p>
     * Ce constructeur permet de créer une exception avec un message d'erreur et une cause sous-jacente.
     * Cela permet de chaîner les exceptions pour fournir plus de détails sur l'origine de l'erreur.
     * </p>
     * 
     * @param message le message d'erreur associé à l'exception.
     * @param cause la cause sous-jacente de l'exception (peut être une autre exception).
     */
    public ObjetException(String message, Throwable cause){
        super(message,cause);
    }
}
