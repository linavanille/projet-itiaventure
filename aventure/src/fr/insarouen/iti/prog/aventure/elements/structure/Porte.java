package fr.insarouen.iti.prog.aventure.elements.structure;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.Activable;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Représente une porte entre deux pièces dans le monde de l'aventure.
 * Une porte peut être ouverte, fermée ou verrouillée. Elle peut avoir une serrure
 * qui nécessite une clé pour l'ouvrir ou un pied-de-biche pour forcer son ouverture.
 * <p>
 * La classe gère l'état de la porte (ouverte, fermée ou verrouillée) ainsi que les
 * interactions avec les objets qui peuvent l'activer, comme une clé ou un pied-de-biche.
 * </p>
 */
public class Porte extends ElementStructurel implements Activable {

    /** Représente les deux pièces que cette porte relie (pieceA et pieceB).
    */
    protected Piece pieceA, pieceB;
    HashMap <String, Piece> lesPortes = new HashMap<String, Piece>();

    /** L'état actuel de la porte, qui peut être FERME, OUVERT ou VERROUILLE.
    */
    protected Etat etat;

    /** La serrure associée à la porte, si la porte est verrouillée.
    */
    protected Serrure serrure;

    /**
     * Constructeur pour créer une porte entre deux pièces.
     * 
     * @param nom le nom de la porte.
     * @param monde le monde auquel appartient la porte.
     * @param pieceA la première pièce de la porte.
     * @param pieceB la deuxième pièce de la porte.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de la porte est déjà utilisé dans le monde.
     */
    public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB) 
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.etat = Etat.FERME;
        pieceA.addPorte(this);
        pieceB.addPorte(this);
        lesPortes.put(pieceA.getNom(), pieceA);
        lesPortes.put(pieceB.getNom(), pieceB);

    }

    /**
     * Constructeur pour créer une porte avec une serrure entre deux pièces.
     * 
     * @param nom le nom de la porte.
     * @param monde le monde auquel appartient la porte.
     * @param serrure la serrure associée à la porte.
     * @param pieceA la première pièce de la porte.
     * @param pieceB la deuxième pièce de la porte.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de la porte est déjà utilisé dans le monde.
     */
    public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB) 
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        this(nom, monde, pieceA, pieceB);
        this.serrure = serrure;
        this.etat = Etat.VERROUILLE;
    }

    /**
     * Retourne la pièce de l'autre côté de la porte en fonction de la pièce donnée.
     * 
     * @param piece la pièce pour laquelle on veut connaître l'autre côté de la porte.
     * @return la pièce de l'autre côté de la porte, ou null si la pièce ne correspond pas.
     */
    public Piece getPieceAutreCote(Piece piece) {
        if (piece.equals(this.pieceA)) {
            return this.pieceB;
        }
        if (piece.equals(this.pieceB)) {
            return this.pieceA;
        }
        return null;
    }

    public Collection<Piece> getPieces(){
        return Collections.unmodifiableCollection(this.lesPortes.values());
    }

    /**
     * Retourne l'état actuel de la porte.
     * 
     * @return l'état de la porte (ouvert, fermé ou verrouillé).
     */
    public Etat getEtat() {
        return this.etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    /**
     * Retourne la serrure associée à la porte, si elle en a une.
     * 
     * @return la serrure de la porte.
     */
    public Serrure getSerrure() {
        return this.serrure;
    }

    /**
     * Active la porte, c'est-à-dire, l'ouvre si elle est fermée, ou la ferme si elle est ouverte.
     * Si la porte est verrouillée, l'activation échouera.
     * 
     * @throws ActivationImpossibleException si la porte est verrouillée ou dans un état invalide.
     */
    public void activer() throws ActivationImpossibleException {
        switch (this.etat) {
            case FERME:
                this.etat = Etat.OUVERT;
                break;
            case OUVERT:
                this.etat = Etat.FERME;
                break;
            case VERROUILLE:
                throw new ActivationImpossibleException("La porte est verrouillée !");
            default:
                throw new ActivationImpossibleException("État de la porte invalide !");
        }
    }

    /**
     * Active la porte en utilisant un objet, comme une clé ou un pied-de-biche.
     * Si la porte est verrouillée, une clé doit être utilisée pour l'ouvrir.
     * Un pied-de-biche peut être utilisé pour forcer l'ouverture de la porte.
     * 
     * @param objet l'objet utilisé pour activer la porte.
     * @throws ActivationImpossibleAvecObjetException si l'objet ne peut pas activer la porte.
     */
    public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException {
        if (this.serrure == null) {
            throw new ActivationImpossibleAvecObjetException("La porte n'a pas de serrure !");
        }

        if (objet instanceof Cle && this.activableAvec(objet)) {
            this.serrure.activerAvec(objet);  
            if (this.etat == Etat.VERROUILLE) {
                this.etat = Etat.OUVERT;
            } else {
                this.etat = Etat.VERROUILLE;
            }
        } else if (objet instanceof PiedDeBiche) {
            this.serrure = null;  // Détruire la serrure
            this.etat = Etat.OUVERT;
        } else {
            throw new ActivationImpossibleAvecObjetException("Impossible d'ouvrir la porte avec cet objet.");
        }
    }

    /**
     * Vérifie si la porte peut être activée avec un objet spécifique (clé ou pied-de-biche).
     * 
     * @param objet l'objet à vérifier.
     * @return true si l'objet peut activer la porte, sinon false.
     */
    public boolean activableAvec(Objet objet) {
        if (objet instanceof PiedDeBiche) {
            return true;
        }
        return (this.serrure != null && this.serrure.activableAvec(objet));
    }

    /**
     * Retourne une représentation sous forme de chaîne de la porte.
     * 
     * @return une chaîne décrivant l'état de la porte, les pièces qu'elle relie et sa serrure.
     */
    @Override
    public String toString() {
        return  String.format("Porte %s [pieceA= %s,  pieceB= %s, etat= %s, serrure= %s ]",this.getNom(),this.pieceA,this.pieceB,this.getEtat(), this.getSerrure());
    }
}

