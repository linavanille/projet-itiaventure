package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
/**
 * Classe représentant des chaines de caractères comme feuille de l'AST
 */
public class ChaineDeCaracteres extends ArgumentSimple {

	/**
	 * Permet de construire la chained de Caractères
	 * @param valeur contenu de la chaine de caractère (image du TOKEN)
	 */
	public ChaineDeCaracteres(String valeur) {
		super(valeur);
	}
}
