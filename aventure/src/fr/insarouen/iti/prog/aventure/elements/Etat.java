package fr.insarouen.iti.prog.aventure.elements;

/**
 * Énumération représentant les différents états d'une porte ou d'un élément activable dans le jeu.
 * <p>
 * Cette énumération définit les états possibles qu'un élément, comme une porte, peut avoir dans le jeu.
 * Les états incluent la possibilité d'être fermée, ouverte, verrouillée ou déverrouillée.
 * </p>
 */
public enum Etat {

    /**
     * L'état "FERME" indique que la porte ou l'élément est fermé et ne permet pas le passage.
     */
    FERME,

    /**
     * L'état "OUVERT" indique que la porte ou l'élément est ouvert et permet le passage.
     */
    OUVERT,

    /**
     * L'état "VERROUILLE" indique que la porte ou l'élément est verrouillé et nécessite une action pour être déverrouillé.
     */
    VERROUILLE,

    /**
     * L'état "DEVERROUILLE" indique que la porte ou l'élément a été déverrouillé et peut maintenant être ouvert.
     */
    DEVERROUILLE;
}
