package fr.insarouen.iti.prog.aventure.elements.structure ;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Classe abstraite représentant un élément structurel dans le monde de l'aventure.
 * <p>
 * Cette classe hérite de la classe {@link Entite} et représente les éléments de type structurel
 * dans le monde du jeu, tels que les pièces, les portes, etc. Les éléments structurels peuvent
 * être considérés comme des objets qui font partie intégrante de la structure du monde.
 * </p>
 */
public abstract class ElementStructurel extends Entite {

    /**
     * Constructeur pour créer un élément structurel avec un nom unique dans un monde donné.
     * <p>
     * Ce constructeur initialise un élément structurel avec un nom spécifique dans un monde donné.
     * Si un objet avec ce nom existe déjà dans le monde, une exception {@link NomDEntiteDejaUtiliseDansLeMondeException}
     * sera lancée.
     * </p>
     * 
     * @param nom le nom de l'élément structurel.
     * @param monde le monde dans lequel l'élément structurel est créé.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si un objet avec le même nom existe déjà dans le monde.
     */
    public ElementStructurel (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    }
}


