package fr.insarouen.iti.prog.aventure.elements.structure;

import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;

/**
 * Classe représentant une pièce dans le monde.
 * Une pièce peut contenir des objets, des vivants (joueurs ou créatures),
 * et des portes qui permettent de relier d'autres pièces.
 */
public class Piece extends ElementStructurel {

	protected Map<String, Objet> tabObjets = new HashMap<>();
	protected Map<String, Vivant> tabVivants = new HashMap<>();
	protected Map<String, Porte> tabPortes = new HashMap<>();

	/**
	 * Crée une pièce avec un nom et un monde donné.
	 */
	public Piece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

	/**
	 * Crée une pièce et y ajoute une liste d’objets.
	 */
	public Piece(String nom, Monde monde, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
		for (Objet o : objets) {
			this.tabObjets.put(o.getNom(), o);
		}
	}

	/**
	 * Vérifie si un objet est présent dans la pièce.
	 */
	public boolean contientObjet(Objet objet) {
		return this.contientObjet(objet.getNom());
	}

	/**
	 * Vérifie si un objet est présent par son nom.
	 */
	public boolean contientObjet(String nomObjet) {
		return this.tabObjets.containsKey(nomObjet);
	}

	/**
	 * Dépose un objet dans la pièce.
	 */
	public void deposer(Objet objet) {
		this.tabObjets.put(objet.getNom(), objet);
	}

	/**
	 * Retire un objet s'il est présent et déplaçable.
	 */
	public Objet retirer(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
		return this.retirer(objet.getNom());
	}

	/**
	 * Retire un objet par son nom.
	 */
	public Objet retirer(String nom) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
		Objet rep = getObjet(nom);
		if (rep == null) {
			throw new ObjetAbsentDeLaPieceException(String.format("Objet %s absent de la pièce %s", nom, this.getNom()));
		}
		if (!rep.estDeplacable()) {
			throw new ObjetNonDeplacableException(String.format("L'objet %s n'est pas déplaçable", nom));
		}
		this.tabObjets.remove(nom);
		return rep;
	}

	/**
	 * Récupère un objet par son nom.
	 */
	public Objet getObjet(String nom) {
		return this.tabObjets.get(nom);
	}

	/**
	 * Renvoie tous les objets de la pièce (non modifiable).
	 */
	public Collection<Objet> getObjets() {
		return Collections.unmodifiableCollection(this.tabObjets.values());
	}

	/**
	 * Renvoie tous les vivants de la pièce (non modifiable).
	 */
	public Collection<Vivant> getVivants() {
		return Collections.unmodifiableCollection(this.tabVivants.values());
	}

	/**
	 * Vérifie si un vivant est présent.
	 */
	public boolean contientVivant(Vivant vivant) {
		return this.contientVivant(vivant.getNom());
	}

	/**
	 * Vérifie si un vivant est présent par nom.
	 */
	public boolean contientVivant(String nomVivant) {
		return this.tabVivants.containsKey(nomVivant);
	}

	/**
	 * Fait entrer un vivant dans la pièce (en le retirant de l’ancienne pièce si nécessaire).
	 */
	public void entrer(Vivant vivant) {
		if (!this.contientVivant(vivant)) {
			if (vivant.getPiece() != null) {
				try {
					vivant.getPiece().sortir(vivant);
				} catch (VivantAbsentDeLaPieceException ex) {
					throw new Error("Ne devrait pas arriver", ex);
				}
			}
			this.tabVivants.put(vivant.getNom(), vivant);
			vivant.setPiece(this);
		}
	}

	/**
	 * Fait sortir un vivant de la pièce.
	 */
	public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException {
		return this.sortir(vivant.getNom());
	}

	/**
	 * Fait sortir un vivant par son nom.
	 */
	public Vivant sortir(String nom) throws VivantAbsentDeLaPieceException {
		Vivant v = this.tabVivants.remove(nom);
		if (v == null) {
			throw new VivantAbsentDeLaPieceException(String.format("Le vivant %s n'est pas dans la pièce %s", nom, this.getNom()));
		}
		v.setPiece(null);
		return v;
	}

	/**
	 * Ajoute une porte à la pièce.
	 */
	protected void addPorte(Porte porte) {
		this.tabPortes.put(porte.getNom(), porte);
	}

	/**
	 * Vérifie si une porte est présente.
	 */
	public boolean aLaPorte(Porte porte) {
		return this.tabPortes.containsValue(porte);
	}

	/**
	 * Vérifie si une porte est présente par son nom.
	 */
	public boolean aLaPorte(String nom) {
		return this.tabPortes.containsKey(nom);
	}

	/**
	 * Retourne les portes présentes dans la pièce.
	 */
	public Collection<Porte> getPortes() {
		return Collections.unmodifiableCollection(this.tabPortes.values());
	}

	/**
	 * Retourne une porte par son nom.
	 */
	public Porte getPorte(String nom) {
		return this.tabPortes.get(nom);
	}

	/**
	 * Donne une représentation textuelle de la pièce (nom, objets, vivants).
	 */
	public String toString() {
		StringBuilder resultat = new StringBuilder("Nom de la pièce : ");
		resultat.append(super.getNom());
		resultat.append(String.format("\nObjets de la pièce %s : ", this.getNom()));
		for (Objet objet : this.getObjets()) {
			resultat.append(objet.getNom()).append("\n");
		}
		resultat.append(String.format("\nVivants de la pièce %s : ", this.getNom()));
		for (Vivant vivant : this.getVivants()) {
			resultat.append(vivant.getNom()).append("\n");
		}
		return resultat.toString();
	}
}

