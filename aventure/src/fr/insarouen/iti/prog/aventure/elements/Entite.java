package fr.insarouen.iti.prog.aventure.elements;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.*;
import java.io.Serializable;

/**
 * Classe abstraite représentant une entité dans le monde du jeu.
 * <p>
 * Une entité est un objet qui a un nom unique dans un monde donné. Elle est associée à un monde spécifique
 * et possède des comportements fondamentaux tels que l'égalité, le calcul de son code de hachage et une
 * représentation sous forme de chaîne de caractères.
 * </p>
 */
public abstract class Entite implements Serializable{

    /** le nom de l'entité
    */
    private String nom;

    /** le monde
    */
    private Monde monde;

    /**
     * Constructeur d'une entité.
     * 
     * @param nom le nom de l'entité.
     * @param monde le monde dans lequel l'entité est située.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de l'entité est déjà utilisé dans le monde.
     */
    public Entite (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.nom = nom;
        this.monde = monde;
        try {
            monde.ajouter(this);
        }
        catch (EntiteDejaDansUnAutreMondeException ex){
            throw new Error ("Ne devrait pas y arriver",ex);
        }
    }

    /**
     * Retourne le nom de l'entité.
     * 
     * @return le nom de l'entité.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Retourne le monde dans lequel l'entité existe.
     * 
     * @return le monde de l'entité.
     */
    public Monde getMonde(){
        return this.monde;
    }

    /**
     * Vérifie si cette entité est égale à une autre.
     * Deux entités sont considérées égales si elles ont le même nom et appartiennent au même monde.
     * 
     * @param o l'objet à comparer.
     * @return true si les entités sont égales, false sinon.
     */
    public boolean equals(Object o){
        if (o==null || this.getClass() != o.getClass()){
            return false;
        }
        Entite e = (Entite)o;
        return this.getNom().equals(e.getNom()) && this.getMonde().equals(e.getMonde());
        
    }

    /**
     * Calcule le code de hachage de l'entité.
     * Le code de hachage est calculé à partir du nom et du monde de l'entité.
     * 
     * @return le code de hachage de l'entité.
     */
    public int hashCode() {
        return 13 * this.getNom().hashCode() + 17 * this.getMonde().hashCode();
    }

    /**
     * Retourne une représentation sous forme de chaîne de l'entité.
     * 
     * @return une chaîne représentant l'entité, incluant son monde et son nom.
     */
    public String toString(){
        return String.format("Monde : %s \nNom : %s",this.getMonde(),this.getNom());
    }

}
