package fr.insarouen.iti.prog.aventure.elements;

/**
 * Énumération représentant les différents états d'une porte 
 * Les états incluent la possibilité d'être fermée, ouverte, verrouillée ou déverrouillée.
 */
public enum Etat {

    /**
     * fermé et ne permet pas le passage.
     */
    FERME,

    /**
     * ouvert et permet le passage.
     */
    OUVERT,

    /**
     * verrouillé et nécessite une action pour être déverrouillé.
     */
    VERROUILLE,

    /**
     * déverrouillé et peut maintenant être ouvert.
     */
    DEVERROUILLE;
}
