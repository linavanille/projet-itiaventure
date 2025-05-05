package fr.insarouen.iti.prog.aventure.conditions;

import fr.insarouen.iti.prog.aventure.EtatDuJeu;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;

/**
* Classe conditon de fin VivantDansPiece : permet de terminer le jeu si un certain vivant 
* est dans une certaine pièce
*/
public class ConditionDeFinVivantDansPiece extends ConditionDeFin {
    /**Attribut viavnt qui doit etre dans une pièce */
    private Vivant vivant;
    /**pièce das lauqelle il doit être  */
    private Piece piece;

    /**
     * Constructeur de la classe ConditionDeFinVivantDansPiece. 
     * Récupère l'etat de la classe mère qui est ConditionDeFin
     * 
     * @param etat L'etat du jeu si la condition de fin est verifiée
     * @param vivant Le vivant qui doit être dans une certaine pièce pour terminer le jeu
     * @param piece La pièce dans laquelle il doit être
     */
    public ConditionDeFinVivantDansPiece (EtatDuJeu etat, Vivant vivant, Piece piece) {
        super(etat);
        this.vivant = vivant;
        this.piece = piece;
    }

    /**
     * Concrétisation de la méthode abstraite permettant d'obtenir l'etat de la partie après la vérification de la condition 
     * @return  l'etat du jeu : si la condition est vérifiée ça appellera getEtatSiConditionVerifiee, sinon ce sera en cours
     */
    @Override
    public EtatDuJeu verifierCondition(){
        if (this.piece.contientVivant(vivant)){
            return this.getEtatSiConditionVerifiee();
        }
        return EtatDuJeu.ENCOURS; 
    }
}