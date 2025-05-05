package fr.insarouen.iti.prog.aventure.elements.structure;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Exception spécifique à la gestion des erreurs liées aux éléments structurels dans le jeu.
 * <p>
 * Cette exception est lancée lorsqu'une erreur se produit spécifiquement en lien avec un
 * élément structurel dans le monde du jeu. Elle étend la classe {@link ITIAventureException},
 * qui est une exception générale utilisée dans le cadre de l'aventure.
 * </p>
 */
public class ElementStructurelException extends ITIAventureException{

    /**
     * Constructeur qui crée une exception avec un message d'erreur spécifique.
     * 
     * @param message le message détaillant l'erreur.
     */
    public ElementStructurelException(String message){
        super(message);
    }

    /**
     * Constructeur qui crée une exception avec un message d'erreur spécifique et une cause sous-jacente.
     * 
     * @param message le message détaillant l'erreur.
     * @param cause la cause de l'exception, une autre exception qui a déclenché celle-ci.
     */
    public ElementStructurelException(String message,Throwable cause){
        super(message,cause);
    }
}