package fr.insarouen.iti.prog.aventure.elements.objets;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.*;

/**
 * Classe représentant un objet PiedDeBiche dans le monde du jeu.
 * Cet objet peut être utilisé pour forcer ou interagir avec certains éléments.
 */
public class PiedDeBiche extends Objet {

	/**
	 * Crée un pied-de-biche dans le monde.
	 *
	 * @param nom Nom de l'objet.
	 * @param monde Monde dans lequel il est placé.
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà pris.
	 * @throws EntiteDejaDansUnAutreMondeException si l'entité existe dans un autre monde.
	 */
	public PiedDeBiche(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
		super(nom, monde);
	}

	/**
	 * Retourne vrai car un pied-de-biche est toujours déplaçable.
	 *
	 * @return true.
	 */
	public boolean estDeplacable() {
		return true;
	}
}

