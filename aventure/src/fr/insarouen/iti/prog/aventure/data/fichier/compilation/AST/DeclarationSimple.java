package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;
/**
 * Classe représentant une affection type : "Identifiant = Fonction"
 */
public class DeclarationSimple implements Visitable{

    //Contient la partie droite de l'affectation
	private Fonction laFonction; 

    //Contient la partie gauche de l'affection
	private Identifiant lIdentifiant; 
	
    /**
     * Constructeur de DeclarationSimple
     * @param lIdentifiant partie gauche de l'affection
     * @param laFonction partie droite de l'affection
     */
	public DeclarationSimple(Identifiant lIdentifiant, Fonction laFonction){
		this.laFonction = laFonction;
		this.lIdentifiant = lIdentifiant; 
	}
	
    /**
     * Permet de récupérer la partie droite de l'affection
     * @return la fonction associé à l'identifiant de cette affection
     */
	public Fonction getFonction(){
        return laFonction;
    }
    
    /** 
     * Permet de récupérer la partie gauche de l'affectation
     * @return l'identifiant associé à cette affection
     */
    public Identifiant getIdentifiant(){
    	return lIdentifiant;
    }

	@Override
    public String toString(){
        return String.format("Déclaration Simple : \n \t Identifiant : %s \n \t Fonction : %s",this.lIdentifiant, this.laFonction);
    } 
}
