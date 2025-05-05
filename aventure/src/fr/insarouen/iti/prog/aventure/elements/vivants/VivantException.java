package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Exception générique liée aux actions ou comportements d'un {@code Vivant}.
 *
 * <p>Cette exception sert de classe de base pour toutes les erreurs spécifiques
 * aux entités vivantes du jeu, telles que les joueurs ou les monstres.</p>
 *
 * @see ITIAventureException
 */
public class VivantException extends ITIAventureException{

    /**
     * Construit une nouvelle {@code VivantException} avec un message d'erreur spécifié.
     *
     * @param message le message détaillant l'erreur.
     */
    public VivantException(String message){
        super(message);
    }

    /**
     * Construit une nouvelle {@code VivantException} avec un message d'erreur et une cause sous-jacente.
     *
     * @param message le message détaillant l'erreur.
     * @param cause la cause initiale de l'exception (peut être {@code null}).
     */
    public VivantException(String message, Throwable cause){
        super(message,cause);
    }
}
