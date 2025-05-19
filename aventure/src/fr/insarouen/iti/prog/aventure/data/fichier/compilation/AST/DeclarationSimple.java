package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;
import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visiteur;

public class DeclarationSimple implements Visitable{
	private Fonction laFonction; 
	private Identifiant lIdentifiant; 
	
	public DeclarationSimple(Identifiant lIdentifiant, Fonction laFonction){
		this.laFonction = laFonction;
		this.lIdentifiant = lIdentifiant; 
	}
	
	
	public Fonction getFonction(){
        return laFonction;
    }
    
    public Identifiant getIdentifiant(){
    	return lIdentifiant;
    }
	
	@Override
    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    } 
}
