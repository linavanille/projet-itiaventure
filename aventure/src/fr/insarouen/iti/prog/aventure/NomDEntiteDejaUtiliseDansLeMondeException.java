package fr.insarouen.iti.prog.aventure;

/**
 * Exception spécifiant qu'une entité a déjà été utilisée dans le monde.
 * Cette exception hérite de {@link MondeException} et est utilisée pour gérer les conflits de nom d'entité.
 */
public class NomDEntiteDejaUtiliseDansLeMondeException extends MondeException {

    /**
     * Constructeur de l'exception NomDEntiteDejaUtiliseDansLeMondeException.
     * @param message Le message d'erreur détaillant la cause de l'exception.
     */
    public NomDEntiteDejaUtiliseDansLeMondeException(String message){
        super(message);
    }  

    /**
     * Constructeur de l'exception NomDEntiteDejaUtiliseDansLeMondeException avec une cause.
     * @param message Le message d'erreur détaillant la cause de l'exception.
     * @param cause La cause sous-jacente de l'exception (une autre exception).
     */
    public NomDEntiteDejaUtiliseDansLeMondeException(String message,Throwable cause){
        super(message,cause);
    }
}