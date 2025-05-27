package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
import fr.insarouen.iti.prog.aventure.EtatDuJeu;
/**
 * Classe représentant les différentes énumérations possibles pour les conditions de fin
 * Est une de l'AST produit par l'analyseur syntaxique
 */
public class EnumerationCDF extends ArgumentSimple {

	/**
	 * Constructeur de EnumerationCDF
	 * @param valeur prise par cette énumération
	 */
	public EnumerationCDF(String valeur) {
		super(valeur);
	}

	/**
	 * Permet de récupérer la valeur transtypée en instance de la classe enumération Etat
	 * @return l'état (Etat) représenté par valeur
	 */
	public EtatDuJeu getValeurEnum() {
		switch (getValeur().toUpperCase()) {
			case "ECHEC":
				return EtatDuJeu.ECHEC;
			case "ENCOURS":
				return EtatDuJeu.ENCOURS;
			case "SUCCES":
				return EtatDuJeu.SUCCES;
		}
		return null;
	}
}
