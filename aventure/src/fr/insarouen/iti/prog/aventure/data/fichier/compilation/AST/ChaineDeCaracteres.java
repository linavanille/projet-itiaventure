package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;

public class ChaineDeCaracteres extends ArgumentSimple {

	public ChaineDeCaracteres(String valeur) {
		super(valeur);
	}

	public String getTexte() {
		return getValeur();
	}
}

