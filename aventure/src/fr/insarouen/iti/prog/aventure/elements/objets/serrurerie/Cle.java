package fr.insarouen.iti.prog.aventure.elements.objets.serrurerie;

import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Représente une clé dans le monde de l'aventure.
 * Les clés peuvent interagir avec des serrures et sont toujours déplaçables.
 */
public class Cle extends Objet {

	/**
	 * Constructeur pour créer une clé.
	 *
	 * @param nom Nom de la clé.
	 * @param monde Monde dans lequel la clé existe.
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé.
	 */
	protected Cle(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

	/**
	 * Une clé est toujours déplaçable.
	 *
	 * @return true.
	 */
	public boolean estDeplacable() {
		return true;
	}
}

