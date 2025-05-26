package fr.insarouen.iti.prog.aventure.elements.structure;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Classe abstraite représentant un élément structurel du monde de l'aventure.
 * Ce type d'entité regroupe les pièces, portes, etc.
 */
public abstract class ElementStructurel extends Entite {

	/**
	 * Crée un élément structurel avec un nom dans un monde donné.
	 *
	 * @param nom Nom de l'élément.
	 * @param monde Monde dans lequel il est créé.
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà pris.
	 */
	public ElementStructurel(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}
}

