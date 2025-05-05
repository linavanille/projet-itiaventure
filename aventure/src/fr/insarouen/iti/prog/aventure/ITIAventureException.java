package fr.insarouen.iti.prog.aventure;

import java.lang.Exception;

/**
 * Classe de base pour toutes les exceptions liées au jeu Aventure.
 * <p>
 * Cette classe étend {@link Exception} et est utilisée comme exception générique 
 * dans le cadre des erreurs qui peuvent survenir dans le jeu Aventure. Elle peut 
 * être étendue par d'autres exceptions spécifiques au jeu pour fournir des messages 
 * d'erreur détaillés.
 * </p>
 */
public class ITIAventureException extends Exception{
    
    /**
     * Constructeur avec un message d'erreur.
     * 
     * @param message Le message d'erreur à transmettre.
     */
    public ITIAventureException(String message){
        super(message);
    }

    /**
     * Constructeur avec un message d'erreur et une cause.
     * 
     * @param message Le message d'erreur à transmettre.
     * @param cause   La cause de l'exception (peut être une autre exception).
     */
    public ITIAventureException(String message, Throwable cause){
        super(message,cause);
    }
}
