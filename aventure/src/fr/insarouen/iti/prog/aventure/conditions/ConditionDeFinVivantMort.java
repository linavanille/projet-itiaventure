package fr.insarouen.iti.prog.aventure.conditions;

import fr.insarouen.iti.prog.aventure.EtatDuJeu;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;

/**
* Classe conditon de fin VivantMort : permet de terminer le jeu si un certain vivant est mort
*/
public class ConditionDeFinVivantMort extends ConditionDeFin {
    /**Le vivant de qui la vie la parte dépend */
    private Vivant vivant;

    /**
     * Constructeur de la classe ConditionDeFinVivantMort. 
     * Récupère l'etat de la classe mère qui est ConditionDeFin
     * 
     * @param etat L'etat du jeu si la condition de fin est verifiée
     * @param vivant Le vivant qui doit mourir pour terminer le jeu
     */
    public ConditionDeFinVivantMort (EtatDuJeu etat, Vivant vivant) {
        super(etat);
        this.vivant = vivant;
    }

    /**
     * Concrétisation de la méthode abstraite permettant d'obtenir l'etat de la partie après la vérification de la condition 
     * @return  l'etat du jeu : si la condition est vérifiée ça appellera getEtatSiConditionVerifiee, sinon ce sera en cours
     */
    public EtatDuJeu verifierCondition(){
    if (this.vivant.estMort()){
       return this.getEtatSiConditionVerifiee();
    }
    return EtatDuJeu.ENCOURS; 
    }
}