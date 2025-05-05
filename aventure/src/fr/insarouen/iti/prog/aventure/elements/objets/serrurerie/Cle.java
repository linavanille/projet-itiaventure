package fr.insarouen.iti.prog.aventure.elements.objets.serrurerie;

import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.Monde;

import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;

/**
 * Classe {@code Cle} représentant un objet clé dans le monde de l'aventure.
 * <p>
 * Cette classe hérite de {@link Objet} et représente un objet qui peut être utilisé pour
 * interagir avec des serrures dans le monde du jeu.
 * </p>
 */
public class Cle extends Objet{

    /**
     * Constructeur de la classe {@code Cle}.
     * <p>
     * Le constructeur permet de créer une clé dans un monde donné. 
     * Si le nom de l'entité est déjà utilisé dans ce monde, une exception sera levée.
     * </p>
     * 
     * @param nom le nom de la clé.
     * @param monde le monde dans lequel la clé est créée.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de l'entité est déjà utilisé dans le monde.
     */
    protected Cle(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }

    /**
     * Indique si l'objet clé est déplaçable.
     * <p>
     * Les clés sont déplaçables, ce qui signifie qu'elles peuvent être prises et transportées par des entités.
     * </p>
     * 
     * @return {@code true} car une clé est déplaçable.
     */
    public boolean estDeplacable(){
        return true;
    }
}