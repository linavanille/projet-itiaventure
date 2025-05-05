package fr.insarouen.iti.prog.aventure.elements;

import fr.insarouen.iti.prog.aventure.elements.objets.Objet;

/**
 * Interface représentant les éléments qui peuvent être activés dans le jeu.
 * <p>
 * Les éléments implémentant cette interface peuvent être activés de deux manières :
 * 1. Par une activation simple (via la méthode {@link #activer()}).
 * 2. Par une activation avec un objet spécifique (via la méthode {@link #activerAvec(Objet)}).
 * </p>
 */
public interface Activable {

    /**
     * Active l'élément de manière standard (sans objet).
     * <p>
     * Cette méthode est utilisée pour effectuer une activation sans l'utilisation d'un objet.
     * Par exemple, une porte pourrait être ouverte ou fermée.
     * </p>
     * 
     * @throws ActivationException si l'activation échoue pour une raison quelconque.
     */
    void activer() throws ActivationException;

    /**
     * Active l'élément avec un objet spécifique.
     * <p>
     * Cette méthode est utilisée pour activer l'élément en utilisant un objet. Par exemple,
     * une serrure pourrait être déverrouillée avec une clé ou un pied-de-biche.
     * </p>
     * 
     * @param objet l'objet utilisé pour l'activation.
     * @throws ActivationException si l'activation échoue, par exemple si l'objet n'est pas compatible.
     */
    void activerAvec(Objet objet) throws ActivationException;

    /**
     * Vérifie si l'élément peut être activé avec un objet donné.
     * <p>
     * Cette méthode permet de savoir si un objet particulier peut être utilisé pour activer l'élément.
     * </p>
     * 
     * @param objet l'objet à tester.
     * @return {@code true} si l'élément peut être activé avec l'objet, {@code false} sinon.
     */
    boolean activableAvec(Objet objet);   
}