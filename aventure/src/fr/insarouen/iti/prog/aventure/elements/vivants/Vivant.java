package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.Executable;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;

import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;

/**
 * Représente un être vivant dans le jeu.
 * Il peut se déplacer, posséder des objets et interagir avec son environnement.
 */
public abstract class Vivant extends Entite implements Executable {

	private int pointsVie;
	private int pointsForce;
	private Piece piece;
	private Map<String, Objet> tabObjets = new HashMap<>();

	/**
	 * Construit un vivant avec ses caractéristiques de base.
	 *
	 * @param nom Nom du vivant
	 * @param monde Monde dans lequel il existe
	 * @param pointsVie Points de vie initiaux
	 * @param pointsForce Points de force initiaux
	 * @param piece Pièce dans laquelle il démarre
	 * @param objets Objets qu’il possède au départ
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé dans le monde
	 */
	public Vivant(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
		this.pointsVie = pointsVie;
		this.pointsForce = pointsForce;
		for (Objet o : objets) {
			this.tabObjets.put(o.getNom(), o);
		}
		this.setPiece(piece);
	}

	/**
	 * @return Le nombre de points de vie
	 */
	public int getPointVie() {
		return this.pointsVie;
	}

	/**
	 * Modifie les points de vie.
	 */
	public void setPointVie(int i) {
		this.pointsVie = i;
	}

	/**
	 * @return Le nombre de points de force
	 */
	public int getPointForce() {
		return this.pointsForce;
	}

	/**
	 * Modifie les points de force.
	 */
	public void setPointForce(int i) {
		this.pointsForce = i;
	}

	/**
	 * Vérifie si le vivant est mort.
	 */
	public boolean estMort() {
		return this.getPointVie() <= 0;
	}

	/**
	 * Prend un objet dans la pièce.
	 */
	public void prendre(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
		this.prendre(objet.getNom());
	}

	/**
	 * Prend un objet par son nom.
	 */
	public void prendre(String nom) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
		this.tabObjets.put(nom, this.piece.retirer(nom));
	}

	/**
	 * Dépose un objet dans la pièce par son nom.
	 */
	public void deposer(String nom) throws ObjetNonPossedeParLeVivantException {
		if (!this.possede(nom)) {
			throw new ObjetNonPossedeParLeVivantException(String.format("Objet %s non possédé par %s", nom, this.getNom()));
		}
		this.piece.deposer(this.getObjet(nom));
		this.tabObjets.remove(nom);
	}

	/**
	 * Dépose un objet dans la pièce.
	 */
	public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException {
		this.deposer(objet.getNom());
	}

	/**
	 * @return Tous les objets possédés par le vivant.
	 */
	public Collection<Objet> getObjets() {
		return Collections.unmodifiableCollection(this.tabObjets.values());
	}

	/**
	 * Récupère un objet possédé par son nom.
	 */
	public Objet getObjet(String nomObjet) {
		return this.tabObjets.get(nomObjet);
	}

	/**
	 * Vérifie si le vivant possède un objet donné.
	 */
	public boolean possede(Objet objet) {
		return this.possede(objet.getNom());
	}

	/**
	 * Vérifie si le vivant possède un objet par son nom.
	 */
	public boolean possede(String nom) {
		return this.getObjet(nom) != null;
	}

	/**
	 * @return La pièce dans laquelle se trouve le vivant.
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/**
	 * Change la pièce du vivant.
	 */
	public void setPiece(Piece piece) {
		if (piece != null) {
			piece.entrer(this);
		} else {
			Piece ancienne = this.piece;
			if (ancienne != null && ancienne.contientVivant(this)) {
				try {
					ancienne.sortir(this);
				} catch (VivantAbsentDeLaPieceException ex) {
					throw new Error("Ne devrait pas arriver", ex);
				}
			}
		}
		this.piece = piece;
	}

	/**
	 * Permet au vivant de franchir une porte.
	 */
	public void franchir(Porte porte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
		if (!this.getPiece().aLaPorte(porte)) {
			throw new PorteInexistanteDansLaPieceException(
				String.format("Porte %s absente de la pièce %s", porte.getNom(), this.getPiece().getNom()));
		}
		if (porte.getEtat() == Etat.FERME) {
			throw new PorteFermeException("La porte est fermée.");
		}
		Piece destination = porte.getPieceAutreCote(this.getPiece());
		destination.entrer(this);
	}

	/**
	 * Franchit une porte à partir de son nom.
	 */
	public void franchir(String nom) throws PorteFermeException, PorteInexistanteDansLaPieceException {
		this.franchir(this.getPiece().getPorte(nom));
	}

	/**
	 * Affiche un résumé du vivant.
	 */
	public String toString() {
	StringBuilder resultat = new StringBuilder("Nom du joueur : ");
	resultat.append(this.getNom());
	resultat.append(String.format("\nPointsDeVie : %s \nPointsDeForce : %s\n", this.getPointVie(), this.getPointForce()));
	resultat.append(this.getPiece().toString());
	resultat.append(String.format("\nObjets de %s :\n", this.getNom()));

	for (Objet objet : this.getObjets()) {
		System.out.println(objet); // Affichage console comme dans l'original
		resultat.append(objet.getNom()).append("\n");
	}

	resultat.append("Les portes de la pièce : ");
	for (Porte porte : this.getPiece().getPortes()) {
		resultat.append(porte.getNom()).append(" ");
	}
	return resultat.toString();
}
}

