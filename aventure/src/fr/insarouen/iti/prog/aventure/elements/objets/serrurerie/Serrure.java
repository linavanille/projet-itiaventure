package fr.insarouen.iti.prog.aventure.elements.objets.serrurerie;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;

/**
 * Représente une serrure dans le monde de l'aventure.
 * Elle peut être activée avec une clé spécifique.
 */
public class Serrure extends Objet {

	private String nom_cle;
	private Etat etat = Etat.VERROUILLE;

	/**
	 * Constructeur qui crée une serrure avec un nom automatique.
	 *
	 * @param monde Monde dans lequel la serrure est créée.
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé.
	 */
	public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(monde.genererNom("Serrure"), monde);
	}

	/**
	 * Constructeur qui crée une serrure avec un nom donné.
	 *
	 * @param nom Nom de la serrure.
	 * @param monde Monde dans lequel elle est créée.
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé.
	 */
	public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

	/**
	 * Une serrure n'est pas déplaçable.
	 *
	 * @return false.
	 */
	public boolean estDeplacable() {
		return false;
	}

	/**
	 * Retourne l'état actuel de la serrure.
	 *
	 * @return l'état (VERROUILLE ou DEVERROUILLE).
	 */
	public Etat getEtat() {
		return this.etat;
	}

	/**
	 * Active la serrure sans objet.
	 * Cela échoue toujours.
	 *
	 * @throws ActivationImpossibleException car la serrure nécessite une clé.
	 */
	public void activer() throws ActivationImpossibleException {
		throw new ActivationImpossibleException("La serrure n'est pas activable sans clé");
	}

	/**
	 * Active la serrure avec une clé.
	 * Change son état si la clé est valide.
	 *
	 * @param objet L'objet utilisé (clé).
	 * @throws ActivationImpossibleAvecObjetException si la clé ne correspond pas.
	 */
	public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException {
		if (!this.activableAvec(objet)) {
			throw new ActivationImpossibleAvecObjetException(String.format(
				"On ne peut pas activer la serrure avec %s. Il vous faut la bonne clé.", objet.getNom()));
		}
		switch (this.etat) {
			case DEVERROUILLE:
				this.etat = Etat.VERROUILLE;
				break;
			case VERROUILLE:
				this.etat = Etat.DEVERROUILLE;
				break;
		}
	}

	/**
	 * Vérifie si un objet donné peut activer cette serrure.
	 *
	 * @param objet L'objet à tester.
	 * @return true si c'est une clé et que le nom correspond.
	 */
	public boolean activableAvec(Objet objet) {
		return objet.getClass().equals(Cle.class) && objet.getNom().equals(nom_cle);
	}

	/**
	 * Crée une clé qui correspond à cette serrure.
	 * Ne fait rien si une clé existe déjà.
	 *
	 * @return La clé associée, ou null si elle existe déjà.
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de la clé est déjà utilisé.
	 */
	public Cle creerCle() throws NomDEntiteDejaUtiliseDansLeMondeException {
		if (nom_cle == null) {
			nom_cle = this.getMonde().genererNom("Cle");
			return new Cle(nom_cle, this.getMonde());
		}
		return null;
	}
}
