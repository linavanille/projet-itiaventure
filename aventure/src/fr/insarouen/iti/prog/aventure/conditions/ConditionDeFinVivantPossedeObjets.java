package fr.insarouen.iti.prog.aventure.conditions;

import fr.insarouen.iti.prog.aventure.EtatDuJeu;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import java.util.Map;
import java.util.HashMap;

/**
* Classe conditon de fin VivantPossedeObjets : permet de terminer le jeu si un certain vivant 
* possede tous les objets d'une certaine liste
*/
public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin {
    /**Le vivant de qui la partie dépend */
    private Vivant vivant;
    /** Les objets qu'il doit avoir pour finir la partie */
    private Map<String,Objet> objets = new HashMap<>();

    /**
     * Constructeur de la classe ConditionDeFinVivantPossedeObjets. 
     * Récupère l'etat de la classe mère qui est ConditionDeFin
     * 
     * @param etat L'etat du jeu si la condition de fin est verifiée
     * @param vivant Le vivant qui doit être dans une certaine pièce pour terminer le jeu
     * @param objets La liste des bjets que le vivant doit posséder pour terminer la partie
     */
    public ConditionDeFinVivantPossedeObjets (EtatDuJeu etat, Vivant vivant, Objet... objets) {
        super(etat);
        this.vivant = vivant;
        for (Objet o : this.objets.values()){
            this.objets.put(o.getNom(),o);
        }
    }

    /**
     * Concrétisation de la méthode abstraite permettant d'obtenir l'etat de la partie après la vérification de la condition 
     * @return  l'etat du jeu : si la condition est vérifiée ça appellera getEtatSiConditionVerifiee, sinon ce sera en cours
     */
    public EtatDuJeu verifierCondition(){
        for (Objet objet : this.objets.values()){
            if (!(this.vivant.possede(objet))){
                return EtatDuJeu.ENCOURS;
            }
        }
    return this.getEtatSiConditionVerifiee();
    }
}