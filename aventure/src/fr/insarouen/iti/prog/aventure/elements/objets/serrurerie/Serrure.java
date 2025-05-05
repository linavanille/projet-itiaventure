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
 * Classe {@code Serrure} représentant un objet serrure dans le monde de l'aventure.
 * <p>
 * La serrure peut être verrouillée ou déverrouillée, et peut être activée avec une clé spécifique.
 * Cette classe hérite de {@link Objet} et est utilisée pour restreindre l'accès à certaines zones du jeu.
 * </p>
 */
public class Serrure extends Objet{

    /**
     * Le nom de la clé associée à cette serrure.
     */
    private String nom_cle;

    /**
     * L'état actuel de la serrure, qui peut être soit {@link Etat#VERROUILLE} soit {@link Etat#DEVERROUILLE}.
     */
    private Etat etat=Etat.VERROUILLE;

    /**
     * Constructeur de la classe {@code Serrure}.
     * <p>
     * Crée une serrure avec un nom généré automatiquement pour cette serrure.
     * </p>
     * 
     * @param monde le monde dans lequel la serrure est créée.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom généré est déjà utilisé dans le monde.
     */
    public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(monde.genererNom("Serrure"),monde);
    }

    /**
     * Constructeur de la classe {@code Serrure} avec un nom spécifié.
     * <p>
     * Crée une serrure avec un nom donné dans le monde spécifié.
     * </p>
     * 
     * @param nom le nom de la serrure.
     * @param monde le monde dans lequel la serrure est créée.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé dans le monde.
     */
    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }

    /**
     * Indique si l'objet serrure est déplaçable.
     * <p>
     * Une serrure n'est pas déplaçable, elle reste dans le monde de jeu.
     * </p>
     * 
     * @return {@code false} car la serrure n'est pas déplaçable.
     */
    public boolean estDeplacable(){
        return false;
    }

    /**
     * Retourne l'état actuel de la serrure.
     * 
     * @return l'état actuel de la serrure, soit {@link Etat#VERROUILLE} ou {@link Etat#DEVERROUILLE}.
     */
    public Etat getEtat(){
        return this.etat;
    }

    /**
     * Tente d'activer la serrure. 
     * <p>
     * Cette opération échoue toujours car la serrure ne peut pas être activée sans une clé appropriée.
     * </p>
     * 
     * @throws ActivationImpossibleException si l'activation est tentée sans une clé valide.
     */
    public void activer() throws ActivationImpossibleException{
        throw new ActivationImpossibleException(String.format("La serreure n'est pas activable"));
    }

    /**
     * Tente d'activer la serrure avec un objet donné (typiquement une clé).
     * <p>
     * Si l'objet est une clé valide, l'état de la serrure change de verrouillé à déverrouillé, ou vice versa.
     * </p>
     * 
     * @param objet l'objet utilisé pour activer la serrure, typiquement une clé.
     * @throws ActivationImpossibleAvecObjetException si l'objet utilisé n'est pas une clé valide pour cette serrure.
     */
    public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException{
        
        if (!this.activableAvec(objet)){
            throw new ActivationImpossibleAvecObjetException(String.format("On ne peut pas activer la serrure avec %s . Il vous faut une clé",objet.getNom()));
        }
        switch (this.etat){
            case DEVERROUILLE:
                this.etat=Etat.VERROUILLE;
                break;
            case VERROUILLE:
                this.etat=Etat.DEVERROUILLE;
                break;
            }
    }

    /**
     * Vérifie si l'objet donné est une clé valide pour cette serrure.
     * <p>
     * Une serrure ne peut être activée qu'avec une clé spécifique.
     * </p>
     * 
     * @param objet l'objet à vérifier.
     * @return {@code true} si l'objet est une clé valide pour cette serrure, sinon {@code false}.
     */
    public boolean activableAvec(Objet objet){
        return objet.getClass().equals(Cle.class) && objet.getNom().equals(nom_cle);
    }

    /**
     * Crée une clé associée à cette serrure.
     * <p>
     * Si la clé n'a pas encore été générée, cette méthode génère un nom pour la clé et la crée.
     * </p>
     * 
     * @return la clé associée à la serrure.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de la clé est déjà utilisé dans le monde.
     */
    public Cle creerCle() throws NomDEntiteDejaUtiliseDansLeMondeException{
        if (nom_cle==null){
            nom_cle=this.getMonde().genererNom("Cle");
            return new Cle(nom_cle,this.getMonde());
        }
        return null;
    }
}