package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;

/**
 * Classe représentant un identifiant à gauche comme à droite de l'affectation
 * Est un noeud de l'AST produit par l'analyseur syntaxique
 */
public class Identifiant extends ArgumentSimple {
	
	/**
	 * Constructeur de Identifiant
	 * @param valeur de l'Identifiant
	 */
	public Identifiant(String valeur) {
		super(valeur);
	}

	/**
	 * Permet d'obtenir la valeur de l'Identifiant
	 * @return la valeur de l'Identifiant
	 */
	public String getNom(){
		return this.valeur;
	}
}
