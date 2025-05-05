package fr.insarouen.iti.prog.aventure.elements.vivants;

/**
 * Exception levée lorsqu'un {@code Vivant} tente d'utiliser ou de manipuler
 * un objet qu'il ne possède pas.
 *
 * <p>Par exemple, si un joueur tente d'ouvrir une porte avec une clé
 * qu'il n'a pas, cette exception sera déclenchée.</p>
 */
public class ObjetNonPossedeParLeVivantException extends VivantException{
    
    /**
     * Construit une nouvelle exception avec un message détaillant l'erreur.
     *
     * @param message le message décrivant la cause de l'exception.
     */
    public ObjetNonPossedeParLeVivantException(String message) {
        super(message);
    }

    /**
     * Construit une nouvelle exception avec un message détaillant l'erreur et la cause sous-jacente.
     *
     * @param message le message décrivant la cause de l'exception.
     * @param cause la cause initiale de l'exception (peut être {@code null}).
     */
    public ObjetNonPossedeParLeVivantException(String message, Throwable cause){
        super(message,cause);
    }
}
