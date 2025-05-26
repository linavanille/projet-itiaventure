package fr.insarouen.iti.prog.aventure.elements.structure;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.Activable;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Représente une porte entre deux pièces.
 * Elle peut être ouverte, fermée ou verrouillée, avec ou sans serrure.
 */
public class Porte extends ElementStructurel implements Activable {

	protected Piece pieceA, pieceB;
	protected Etat etat;
	protected Serrure serrure;
	protected HashMap<String, Piece> lesPortes = new HashMap<>();

	/**
	 * Crée une porte simple entre deux pièces.
	 */
	public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
		this.pieceA = pieceA;
		this.pieceB = pieceB;
		this.etat = Etat.FERME;
		pieceA.addPorte(this);
		pieceB.addPorte(this);
		lesPortes.put(pieceA.getNom(), pieceA);
		lesPortes.put(pieceB.getNom(), pieceB);
	}

	/**
	 * Crée une porte verrouillée avec une serrure.
	 */
	public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		this(nom, monde, pieceA, pieceB);
		this.serrure = serrure;
		this.etat = Etat.VERROUILLE;
	}

	/**
	 * Renvoie la pièce de l'autre côté.
	 */
	public Piece getPieceAutreCote(Piece piece) {
		if (piece.equals(this.pieceA)) {
			return this.pieceB;
		}
		if (piece.equals(this.pieceB)) {
			return this.pieceA;
		}
		return null;
	}

	/**
	 * Renvoie les deux pièces connectées.
	 */
	public Collection<Piece> getPieces() {
		return Collections.unmodifiableCollection(this.lesPortes.values());
	}

	/**
	 * Donne l'état de la porte.
	 */
	public Etat getEtat() {
		return this.etat;
	}

	/**
	 * Modifie l'état de la porte.
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	/**
	 * Renvoie la serrure associée (peut être null).
	 */
	public Serrure getSerrure() {
		return this.serrure;
	}

	/**
	 * Active la porte (ouvrir ou fermer).
	 * Si elle est verrouillée, une exception est levée.
	 */
	public void activer() throws ActivationImpossibleException {
		switch (this.etat) {
			case FERME:
				this.etat = Etat.OUVERT;
				break;
			case OUVERT:
				this.etat = Etat.FERME;
				break;
			case VERROUILLE:
				throw new ActivationImpossibleException("La porte est verrouillée !");
			default:
				throw new ActivationImpossibleException("État de la porte invalide !");
		}
	}

	/**
	 * Tente d'activer la porte avec un objet.
	 * Fonctionne si la porte a une serrure compatible ou un pied-de-biche.
	 */
	public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException {
		if (this.serrure == null) {
			throw new ActivationImpossibleAvecObjetException("La porte n'a pas de serrure !");
		}

		if (objet instanceof Cle && this.activableAvec(objet)) {
			this.serrure.activerAvec(objet);
			if (this.etat == Etat.VERROUILLE) {
				this.etat = Etat.OUVERT;
			} else {
				this.etat = Etat.VERROUILLE;
			}
		} else if (objet instanceof PiedDeBiche) {
			this.serrure = null; // On casse la serrure
			this.etat = Etat.OUVERT;
		} else {
			throw new ActivationImpossibleAvecObjetException("Impossible d'ouvrir la porte avec cet objet.");
		}
	}

	/**
	 * Vérifie si la porte peut être activée avec un objet donné.
	 */
	public boolean activableAvec(Objet objet) {
		if (objet instanceof PiedDeBiche) {
			return true;
		}
		return this.serrure != null && this.serrure.activableAvec(objet);
	}

	/**
	 * Donne une description complète de la porte.
	 */
	public String toString() {
		return String.format(
			"Porte %s [pieceA= %s,  pieceB= %s, etat= %s, serrure= %s ]",
			this.getNom(), this.pieceA, this.pieceB, this.getEtat(), this.getSerrure()
		);
	}
}

