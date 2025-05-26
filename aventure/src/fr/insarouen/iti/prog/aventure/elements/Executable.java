package fr.insarouen.iti.prog.aventure.elements;

import fr.insarouen.iti.prog.aventure.ITIAventureException;

/**
 * Interface représentant un objet ou une entité qui peut être exécutée dans le jeu.
 * L'implémentation de cette interface permet aux objets de réagir à des événements d'exécution ou à des commandes données par le joueur ou le système.
 */
public interface Executable {

    /**
     * Exécute une action ou une commande sur l'objet ou l'entité.
     * Cette méthode est appelée pour effectuer une action sur l'entité ou l'objet.
     * Les actions spécifiques dépendent de l'implémentation de cette méthode
     * dans les classes qui implémentent cette interface.
     *
     * @throws ITIAventureException Si une erreur se produit pendant l'exécution de l'action.
     */
    void executer() throws ITIAventureException ;
}
