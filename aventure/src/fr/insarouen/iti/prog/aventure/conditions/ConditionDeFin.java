package fr.insarouen.iti.prog.aventure.conditions;

import java.io.Serializable;
import fr.insarouen.iti.prog.aventure.EtatDuJeu;

/**
* Classe conditon de fin qui permettra de gérer les différentes fins de jeu possibles
*/
public abstract class ConditionDeFin implements Serializable{

    /**Attribut etat du jeu si condition verifiée */
    private EtatDuJeu etat;

    /**
     * Constructeur de la classe ConditionDeFin
     * 
     * @param etat L'etat du jeu si la condition de fin est verifiée
     */
    public ConditionDeFin(EtatDuJeu etat){
        this.etat = etat;
    }

    /**
     * Methode permettant d'obtenir l'etat de la partie si la condition de fin est vérifiée .
     * @return  l'etat du jeu si la condition est vérifiée
     */
    public EtatDuJeu getEtatSiConditionVerifiee(){
        return this.etat;
    }

    /**
     * Méthode abstraite  permettant d'obtenir l'etat de la partie après vérification de la condition 
     * @return  l'etat du jeu : si la condition est vérifiée ça appellera getEtatSiConditionVerifiee, sinon ce sera en cours
     */
    public abstract EtatDuJeu verifierCondition();
}


