package fr.insarouen.iti.prog.aventure.conditions;

import fr.insarouen.iti.prog.aventure.EtatDuJeu;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import java.util.Map;
import java.util.HashMap;

/**
* Classe conditon de fin VivantDansPieceEtPossedeObjets : permet de terminer le jeu si un certain vivant 
* est dans une certaine pièce et possède tous les objets qu'il doit posséder
*/
public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin {
    /**condition de fin si un vivant doit être dans une piece */
    private ConditionDeFinVivantDansPiece conditionPiece;
    /**condition de fin si un vivant doit avoir des objets */
    private ConditionDeFinVivantPossedeObjets conditionObjets;

    /**
     * Constructeur de la classe ConditionDeFinVivantDansPieceEtPossedeObjets. 
     * Récupère l'etat de la classe mère qui est ConditionDeFin
     * 
     * @param etat L'etat du jeu si la condition de fin est verifiée
     * @param vivant Le vivant qui doit être dans une certaine pièce pour terminer le jeu
     * @param objets La liste des bjets que le vivant doit posséder pour terminer la partie
     * @param piece La pièce dans laquelle il doit être
     */
    public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etat, Vivant vivant, Piece piece, Objet... objets) {
        super(etat);
        this.conditionPiece = new ConditionDeFinVivantDansPiece(etat, vivant, piece) ;
        this.conditionObjets = new ConditionDeFinVivantPossedeObjets(etat, vivant, objets);
    }

    /**
     * Concrétisation de la méthode abstraite permettant d'obtenir l'etat de la partie après la vérification de la condition 
     * @return  l'etat du jeu : si la condition est vérifiée ça appellera getEtatSiConditionVerifiee, sinon ce sera en cours
     */
    @Override
    public EtatDuJeu verifierCondition() {
        EtatDuJeu etatPiece = conditionPiece.verifierCondition();
        EtatDuJeu etatObjets = conditionObjets.verifierCondition();

        if (etatPiece == this.getEtatSiConditionVerifiee() && etatObjets == this.getEtatSiConditionVerifiee()) {
            return this.getEtatSiConditionVerifiee();
        }
        return EtatDuJeu.ENCOURS;
    }
}