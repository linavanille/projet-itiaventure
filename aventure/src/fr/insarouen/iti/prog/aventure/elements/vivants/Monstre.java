package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.Etat;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe représentant un monstre autonome.
 * Le monstre perd 1 point de vie à chaque tour, franchit des portes ouvertes,
 * ramasse tous les objets de la pièce, puis les dépose.
 */
public class Monstre extends Vivant {

	/**
	 * Constructeur du monstre.
	 *
	 * @param nom Nom du monstre.
	 * @param monde Monde dans lequel il évolue.
	 * @param pointsVie Points de vie.
	 * @param pointsForce Points de force.
	 * @param piece Pièce initiale.
	 * @param objets Objets possédés.
	 */
	public Monstre(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets)
			throws fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde, pointsVie, pointsForce, piece, objets);
	}

	/**
	 * Comportement automatique du monstre à chaque tour :
	 * - Perd un point de vie.
	 * - Franchit la première porte ouverte ou déverrouillable.
	 * - Ramasse tous les objets de la nouvelle pièce.
	 * - Dépose tous les objets qu’il possède.
	 */
	public void executer() throws ITIAventureException {
		if (this.getPointVie() > 0) {
			this.setPointVie(this.getPointVie() - 1);
		}

		List<Porte> portes = this.getPiece().getPortes().stream()
			.filter(p -> p.getEtat() != Etat.VERROUILLE)
			.collect(Collectors.toList());

		if (!portes.isEmpty()) {
			Porte porte = portes.get(0);
			if (porte.getEtat() == Etat.FERME) {
				porte.activer();
			}
			this.franchir(porte);

			List<Objet> objetsPiece = new ArrayList<>(this.getPiece().getObjets());
			for (Objet o : objetsPiece) {
				this.prendre(o);
			}

			List<Objet> objetsMonstre = new ArrayList<>(this.getObjets());
			for (Objet o : objetsMonstre) {
				this.deposer(o);
			}
		}
	}
}

