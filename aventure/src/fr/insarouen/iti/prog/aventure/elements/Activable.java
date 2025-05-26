package fr.insarouen.iti.prog.aventure.elements;

import fr.insarouen.iti.prog.aventure.elements.objets.Objet;

/**
 * Interface représentant les éléments qui peuvent être activés dans le jeu.
 * Les éléments implémentant cette interface peuvent être activés de deux manières :
 * 1. Par une activation simple activer().
 * 2. Par une activation avec un objet spécifique activerAvec(Objet)).
 */
public interface Activable {

    /**
     * Active l'élément de manière standard (sans objet).
     *
     * @throws ActivationException si l'activation.
     */
    void activer() throws ActivationException;

    /**
     * Active l'élément avec un objet spécifique.
     * 
     * @param objet l'objet utilisé pour l'activation.
     * @throws ActivationException si l'activation échoue, par exemple si l'objet n'est pas compatible.
     */
    void activerAvec(Objet objet) throws ActivationException;

    /**
     * Vérifie si l'élément peut être activé avec un objet donné.
     * 
     * @param objet l'objet à tester.
     * @return true si l'élément peut être activé avec l'objet, false sinon.
     */
    boolean activableAvec(Objet objet);   
}
