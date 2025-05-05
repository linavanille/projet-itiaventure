package fr.insarouen.iti.prog.aventure.elements.structure;

/**
 * Exception lancée lorsqu'un objet est absent d'une pièce du monde du jeu.
 * <p>
 * Cette exception est utilisée pour signaler qu'un objet auquel on tente d'accéder
 * ou d'interagir n'est pas présent dans la pièce où l'on s'attendait à le trouver.
 * Elle hérite de {@link PieceException}, une exception générale pour les erreurs liées aux pièces.
 * </p>
 */
public class ObjetAbsentDeLaPieceException extends PieceException{
    
    /**
     * Constructeur qui crée une exception avec un message d'erreur spécifique.
     * 
     * @param message le message détaillant l'erreur.
     */
    public ObjetAbsentDeLaPieceException(String message){
        super(message);
    }

    /**
     * Constructeur qui crée une exception avec un message d'erreur spécifique et une cause sous-jacente.
     * 
     * @param message le message détaillant l'erreur.
     * @param cause la cause de l'exception, une autre exception qui a déclenché celle-ci.
     */
    public ObjetAbsentDeLaPieceException(String message, Throwable cause){
        super(message,cause);
    }
}