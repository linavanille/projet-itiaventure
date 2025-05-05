package fr.insarouen.iti.prog.aventure.elements.structure;

/**
 * Exception levée pour toute erreur liée à une pièce dans le monde du jeu.
 * <p>
 * Cette exception est utilisée pour signaler des problèmes lors des interactions avec les pièces, comme l'ajout ou le retrait
 * d'objets ou de vivants, ou la gestion des portes et autres aspects de la pièce.
 * </p>
 */
public class PieceException extends ElementStructurelException{
    
    /**
     * Constructeur pour créer une nouvelle exception avec un message.
     * 
     * @param message le message décrivant l'exception.
     */
    public PieceException(String message){
        super(message);
    }

    /**
     * Constructeur pour créer une nouvelle exception avec un message et une cause.
     * 
     * @param message le message décrivant l'exception.
     * @param cause la cause de l'exception (une autre exception qui a conduit à cette erreur).
     */
    public PieceException(String message,Throwable cause){
        super(message,cause);
    }
}