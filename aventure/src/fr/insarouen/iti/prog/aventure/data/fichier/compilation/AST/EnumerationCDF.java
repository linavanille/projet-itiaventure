package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
import fr.insarouen.iti.prog.aventure.EtatDuJeu;

public class EnumerationCDF extends ArgumentSimple {

	public EnumerationCDF(String valeur) {
		super(valeur);
	}

	public EtatDuJeu getEtat() {
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

