package fr.insarouen.iti.prog.aventure;

/**
 * Exception spécifique liée à un problème dans le monde du jeu.
 * Cette exception hérite de {@link ITIAventureException} et est utilisée pour gérer les erreurs spécifiques au monde.
 */
public class MondeException extends ITIAventureException{

    /**
     * Constructeur de l'exception MondeException.
     * @param message Le message d'erreur détaillant la cause de l'exception.
     */
    public MondeException(String message){
        super(message);
    }

    /**
     * Constructeur de l'exception MondeException avec une cause.
     * @param message Le message d'erreur détaillant la cause de l'exception.
     * @param cause La cause sous-jacente de l'exception (une autre exception).
     */
    public MondeException(String message, Throwable cause){
        super(message,cause);
    }
}