package fr.insarouen.iti.prog.aventure.data.fichier.compilation.automate;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;

public class Nombre extends ArgumentSimple {

	public Nombre(String valeur) {
		super(valeur);
	}

	public int getValeurEntier() {
		return Integer.parseInt(super.getValeur());
	}
}

