package fr.insarouen.iti.prog.aventure.elements.structure;

/**
 * Exception levée lorsqu'une porte est demandée dans une pièce mais qu'elle n'existe pas.
 * <p>
 * Cette exception est utilisée pour signaler une tentative d'accès à une porte qui ne fait pas partie
 * d'une pièce donnée.
 * </p>
 */
public class PorteInexistanteDansLaPieceException extends PieceException{

    /**
     * Constructeur de l'exception avec un message d'erreur.
     * 
     * @param message le message détaillant l'erreur.
     */
    public PorteInexistanteDansLaPieceException(String message){
        super(message);
    }

    /**
     * Constructeur de l'exception avec un message d'erreur et une cause.
     * 
     * @param message le message détaillant l'erreur.
     * @param cause la cause de l'exception.
     */
    public PorteInexistanteDansLaPieceException(String message, Throwable cause){
        super(message,cause);
    }
}