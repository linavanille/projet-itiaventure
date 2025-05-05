package fr.insarouen.iti.prog.aventure;

/**
 * Enumération représentant les différents états possibles du jeu.
 * <p>
 * Cette énumération définit les trois états principaux d'un jeu : 
 * - {@link #ECHEC} : L'état indiquant que le jeu est terminé et que le joueur a échoué.
 * - {@link #ENCOURS} : L'état indiquant que le jeu est en cours.
 * - {@link #SUCCES} : L'état indiquant que le jeu est terminé et que le joueur a réussi.
 * </p>
 */
public enum EtatDuJeu {

    /**
     * L'état du jeu est "Échec", signifiant que le joueur a échoué.
     */
    ECHEC,

    /**
     * L'état du jeu est "En cours", signifiant que le jeu n'est pas encore terminé.
     */
    ENCOURS,

    /**
     * L'état du jeu est "Succès", signifiant que le joueur a réussi.
     */
    SUCCES;
}


