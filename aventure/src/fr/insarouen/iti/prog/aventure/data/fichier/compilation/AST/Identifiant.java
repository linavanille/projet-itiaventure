package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;

public class Identifiant extends ArgumentSimple {
	
	public Identifiant(String valeur) {
		super(valeur);
	}

	public String getNom(){
		return this.valeur;
	}
}
