package fr.insarouen.iti.prog.aventure.elements.objets;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.*;

/**
 * Classe abstraite représentant un objet dans le monde du jeu.
 * Un objet est une entité pouvant exister dans un monde et être potentiellement déplacé.
 */
public abstract class Objet extends Entite {

	/**
	 * Constructeur d'objet.
	 *
	 * @param nom Nom de l'objet.
	 * @param monde Monde dans lequel l'objet est placé.
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé.
	 */
	public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

	/**
	 * Méthode abstraite qui indique si l'objet peut être déplacé.
	 *
	 * @return true si l'objet est déplaçable, false sinon.
	 */
	public abstract boolean estDeplacable();

	/**
	 * Affiche les informations sur l'objet.
	 *
	 * @return une chaîne indiquant le nom de l'objet et s'il est déplaçable.
	 */
	public String toString() {
		return String.format("Nom : %s %s déplaçable", super.getNom(), this.estDeplacable() ? "est" : "n'est pas");
	}
}

