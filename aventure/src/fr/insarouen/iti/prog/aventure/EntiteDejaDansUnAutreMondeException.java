package fr.insarouen.iti.prog.aventure;

/**
 * Exception levée lorsqu'une entité est déjà présente dans un autre monde.
 * <p>
 * Cette exception est utilisée pour indiquer qu'une tentative d'ajout d'une entité dans un monde
 * a échoué car l'entité appartient déjà à un autre monde. Elle hérite de {@link MondeException}.
 * </p>
 */
public class EntiteDejaDansUnAutreMondeException extends MondeException {
    
    /**
     * Constructeur de l'exception avec un message d'erreur.
     *
     * @param message Le message décrivant l'exception.
     */
    public EntiteDejaDansUnAutreMondeException(String message){
        super(message);
    }

    /**
     * Constructeur de l'exception avec un message d'erreur et une cause.
     *
     * @param message Le message décrivant l'exception.
     * @param cause   La cause de l'exception.
     */
    public EntiteDejaDansUnAutreMondeException(String message, Throwable cause){
        super(message,cause);
    }
}
