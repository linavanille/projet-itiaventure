package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;

public class DeclarationSimple{
	private Fonction laFonction; 
	private Identifiant lIdentifiant; 
	
	public DeclarationSimple(Fonction laFonction, Identifiant lIdentifiant){
		this.laFonction = laFonction;
		this.lIdentifiant = lIdentifiant; 
	}
	
	
	public Fonction getFonction(){
        return laFonction;
    }
    
    public Identifiant getIdentifiant(){
    	return lIdentifiant;
    }
	
}
