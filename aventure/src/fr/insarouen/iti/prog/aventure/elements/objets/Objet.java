package fr.insarouen.iti.prog.aventure.elements.objets;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.*;

/**
 * Classe abstraite représentant un objet dans le monde de l'aventure.
 * <p>
 * Un objet est une entité qui peut exister dans le monde et avoir des comportements associés, comme être
 * déplacé ou non. Cette classe étend la classe {@link Entite} et est utilisée comme base pour différents types
 * d'objets dans le jeu (par exemple, des clés, des portes, des objets interactifs, etc.).
 * </p>
 */
public abstract class Objet extends Entite {

    /**
     * Constructeur de la classe {@code Objet}.
     * <p>
     * Ce constructeur permet de créer un objet avec un nom et un monde. Le nom de l'objet doit être unique dans le monde.
     * </p>
     * 
     * @param nom le nom de l'objet à créer.
     * @param monde le monde dans lequel l'objet existe.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de l'objet est déjà utilisé dans le monde.
     */
    public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    /**
     * Méthode abstraite indiquant si l'objet peut être déplacé.
     * <p>
     * Les objets qui peuvent être déplacés dans le monde auront un comportement particulier (par exemple, être
     * pris par un joueur), tandis que ceux qui ne peuvent pas l'être resteront statiques dans le monde.
     * </p>
     * 
     * @return {@code true} si l'objet est déplaçable, sinon {@code false}.
     */
    public abstract boolean estDeplacable();

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet.
     * <p>
     * Cette méthode fournit une description de l'objet, incluant son nom et son état de déplaçabilité.
     * </p>
     * 
     * @return une chaîne de caractères représentant l'objet.
     */
    public String toString() {
        return String.format("Nom : %s %s déplaçable", super.getNom(), this.estDeplacable() ? "est" : "n'est pas");
    }
}