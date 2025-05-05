package fr.insarouen.iti.prog.aventure.elements.structure;

/**
 * Exception levée lorsqu'un vivant est absent d'une pièce.
 * <p>
 * Cette exception est utilisée pour signaler une tentative d'accès à un vivant qui n'est pas présent
 * dans la pièce, par exemple lorsqu'on essaie de retirer un vivant d'une pièce alors qu'il n'y est pas.
 * </p>
 */
public class VivantAbsentDeLaPieceException extends PieceException{
    
    /**
     * Constructeur de l'exception avec un message d'erreur.
     * 
     * @param message le message détaillant l'erreur.
     */
    public VivantAbsentDeLaPieceException(String message){
        super(message);
    }

    /**
     * Constructeur de l'exception avec un message d'erreur et une cause.
     * 
     * @param message le message détaillant l'erreur.
     * @param cause la cause de l'exception.
     */
    public VivantAbsentDeLaPieceException(String message, Throwable cause){
        super(message,cause);
    }
}