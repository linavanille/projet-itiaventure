package fr.insarouen.iti.prog.aventure.elements.objets ;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.*;

/**
 * Représente un objet de type "PiedDeBiche" dans le monde du jeu.
 * <p>
 * Cette classe hérite de la classe {@link Objet} et représente un pied-de-biche, un objet
 * qui peut être déplacé dans le jeu. Un pied-de-biche peut être utilisé pour diverses
 * interactions dans le monde de l'aventure, comme l'ouverture de portes ou le forçage
 * de mécanismes.
 * </p>
 */
public class PiedDeBiche extends Objet{

    /**
     * Constructeur pour créer un pied-de-biche avec un nom dans un monde donné.
     * <p>
     * Ce constructeur initialise un pied-de-biche avec un nom unique dans un monde spécifique.
     * Si un objet avec ce nom existe déjà dans le monde, une exception {@link NomDEntiteDejaUtiliseDansLeMondeException}
     * sera lancée.
     * </p>
     * 
     * @param nom le nom de l'objet pied-de-biche.
     * @param monde le monde dans lequel l'objet est créé.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si un objet avec le même nom existe déjà dans le monde.
     * @throws EntiteDejaDansUnAutreMondeException si l'entité est déjà présente dans un autre monde.
     */
    public PiedDeBiche (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException{
        super(nom, monde);
    }

    /**
     * Détermine si l'objet est déplaçable.
     * <p>
     * Un pied-de-biche est un objet qui peut être déplacé dans le monde du jeu.
     * </p>
     * 
     * @return {@code true} car un pied-de-biche est déplaçable.
     */
    public boolean estDeplacable(){
        return true;
    }
}
