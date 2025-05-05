package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.elements.vivants.VivantException;

/**
 * Exception levée lorsqu'une commande ne peut pas être exécutée par un vivant.
 * 
 * Cette exception est utilisée pour signaler que l'action demandée
 * n'est pas réalisable par le vivant dans son état actuel ou selon les règles du jeu.
 */
public class CommandeImpossiblePourLeVivantException extends VivantException{
    
    /**
     * Construit une nouvelle exception CommandeImpossiblePourLeVivantException avec un message d'erreur spécifique.
     * 
     * @param message le message détaillant la raison de l'exception.
     */
    public CommandeImpossiblePourLeVivantException(String message){
        super(message);
    }

     /**
     * Construit une nouvelle exception CommandeImpossiblePourLeVivantException avec un message d'erreur et une cause spécifique.
     * 
     * @param message le message détaillant la raison de l'exception.
     * @param cause la cause de l'exception (peut être null).
     */
    public CommandeImpossiblePourLeVivantException(String message, Throwable cause){
        super(message,cause);
    }
}
