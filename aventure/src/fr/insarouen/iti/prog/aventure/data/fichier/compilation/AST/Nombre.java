package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;

/**
 * Classe représentant les nombres étant des feuilles de l'AST
 */
public class Nombre extends ArgumentSimple {

	/**
	 * Constructeur de Nombre
	 * @param valeur en chaine de caractère du nombre
	 */
	public Nombre(String valeur) {
		super(valeur);
	}

	/**
	 * Permet de récupérer la valeur parsée en int
	 * @return la valeur parsée en int
	 */
	public int getValeurEntier() {
		return Integer.parseInt(super.getValeur());
	}
}

