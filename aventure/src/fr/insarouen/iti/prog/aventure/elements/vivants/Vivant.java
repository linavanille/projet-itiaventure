package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.Executable;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;

import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;

/**
 * Classe représentant un vivant dans le jeu. Un vivant est une entité
 * qui peut posséder des objets, se déplacer entre des pièces, et interagir avec d'autres entités.
 * 
 * <p>Cette classe sert de base aux entités vivantes telles que les joueurs ou les monstres.</p>
 *
 * @see Entite
 * @see Executable
 */
public abstract class Vivant extends Entite implements Executable{
    /**les PV du vivant */
    private int pointsVie;
    /**les PF du vivant */
    private int pointsForce;
    /**la piece dans laquelle le vivant se trouve */
    private Piece piece;
    /** les objets que le vivant possède */
    private Map<String,Objet> tabObjets = new HashMap<>();

     /**
     * Constructeur de la classe Vivant.
     * @param nom Le nom du vivant.
     * @param monde Le monde dans lequel il existe.
     * @param pointsVie Les points de vie.
     * @param pointsForce Les points de force.
     * @param piece La piece actuelle du vivant.
     * @param objets Les objets possedes par le vivant.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si on essaie de créer une entié avec un nom déjà pris
     */
    public Vivant(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet ... objets ) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
        this.pointsVie = pointsVie; 
        this.pointsForce = pointsForce;
        for (Objet o : objets){
            this.tabObjets.put(o.getNom(),o);
        }
        this.setPiece(piece);
        
    }

    /**
     * Permet d'accéder en lecture au nombre de pointsVie d'un vivant
     * @return le nombre de pointsVie du vivant
     */
    public int getPointVie(){
        return this.pointsVie;
    }

    /**
     * Permet d'accéder en écriture au nombre de pointsVie d'un vivant
     * @param i le futur nombre de pointsVie du vivant
     */
    public void setPointVie(int i){
        this.pointsVie = i;
    }

    /**
     * Permet d'accéder en lecture au nombre de pointsForce d'un vivant
     * @return le nombre de pointsForce du vivant
     */
    public int getPointForce(){
        return this.pointsForce;
    }

    /**
     * Permet d'accéder en écriture au nombre de pointsForce d'un vivant
     * @param i le futur nombre de pointsForce du vivant
     */
    public void setPointForce(int i){
        this.pointsForce = i;
    }

    /**
     * Permet de vérifier si un vivant est mort
     * @return boolean si le vivant est mort
     */
    public boolean estMort(){
        return this.getPointVie()<=0;
    }



    /**
     * Permet au vivant de prendre un objet dans sa piece.
     * @param objet L'objet a prendre.
     * @throws ObjetAbsentDeLaPieceException si on essaie de prendre un objet qui n'est pas dans la pièce où l'on se trouve
     * @throws ObjetNonDeplacableException si on essaie de prendre un objet qui n'est pas déplaçable
     */
    public void prendre(Objet objet) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException{
        this.prendre(objet.getNom());
    }


    /**
     * Permet au vivant de prendre un objet par son nom.
     * @param nom Le nom de l'objet a prendre.
     * @throws ObjetAbsentDeLaPieceException si on essaie de prendre un objet qui n'est pas dans la pièce où l'on se trouve
     * @throws ObjetNonDeplacableException si on essaie de prendre un objet qui n'est pas déplaçable
     */
    public void prendre(String nom) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException{
        this.tabObjets.put(nom,this.piece.retirer(nom));
    }

     /**
     * Permet au vivant de deposer un objet dans sa piece.
     * @param nom Le nom de l'objet a deposer.
     * @throws ObjetNonPossedeParLeVivantException si on essaie de deposer un objet que l'on ne possède pas
     */
    public void deposer(String nom) throws ObjetNonPossedeParLeVivantException{
        
        if (!this.possede(nom)){
            throw new ObjetNonPossedeParLeVivantException(String.format("Objet %s non posede par le vivant %s",nom,this.getNom()));
        }
        else{
            this.piece.deposer(this.getObjet(nom));
            /*Objet [] nvtab = new Objet[this.tabObjets.length-1]; 
            int j = 0;
            for (int i=0; i < this.tabObjets.length ; i++ ){
                if (!nom.equals(this.tabObjets[i].getNom())){
                    nvtab[j] = this.tabObjets[i];
                    j++;
                }
            } 
            this.tabObjets = nvtab;*/
            this.tabObjets.remove(nom);
        }
    }

    /**
     * Permet au vivant de deposer un objet dans sa piece.
     * @param objet L'objet a deposer.
     * @throws ObjetNonPossedeParLeVivantException si on essaie de deposer un objet que l'on ne possède pas
     */
    public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException{
        this.deposer(objet.getNom());
    }
    
    /**
     * Retourne la liste des objets possedes par le vivant.
     * @return Un tableau d'objets.
     */

    public Collection<Objet> getObjets(){
        //return this.tabObjets.values().toArray(new Objet[0]);
        return Collections.unmodifiableCollection(this.tabObjets.values());
    }

    /**
     * Retourne un objet possede par le vivant en fonction de son nom.
     * @param nomObjet Le nom de l'objet recherche.
     * @return L'objet correspondant ou null s'il n'est pas trouve.
     */
    public Objet getObjet(String nomObjet){
        return this.tabObjets.get(nomObjet);
    }
    
    /**
     * Verifie si le vivant possede un objet.
     * @param objet L'objet a verifier.
     * @return true si le vivant possede l'objet, sinon false.
     */
    public boolean possede(Objet objet){
        return this.possede(objet.getNom());
    }

    /**
     * Verifie si le vivant possede un objet.
     * @param nom Le nom de l'objet a verifier.
     * @return true si le vivant possede l'objet, sinon false.
     */
    public boolean possede(String nom){
        return this.getObjet(nom) != null;
    }

    /**
     * Permet d'acceder en lecture à la pièce d'un vivant
     * @return la pièce dans laquelle se trouve le vivant
     */
    public Piece getPiece(){
        return this.piece;
    }
    
    /**
     * Permet d'acceder en écriture à la pièce d'un vivant
     * @param piece la pièce dans laquelle va le vivant
     */
    public void setPiece(Piece piece){
    
        if (piece!=null){
            piece.entrer(this);
        }
        else {
            Piece pieceCourante=this.piece;
            if (pieceCourante!=null && pieceCourante.contientVivant(this)){
            try {
                pieceCourante.sortir(this);
            } 
            catch (VivantAbsentDeLaPieceException ex){
                throw new Error("Ne devrait pas arriver",ex);
            }
          }
        }
        this.piece=piece;
    }

    /**
     * Permet à un vivant de franchir une porte 
     * @param porte La porte qui doit être franchie
     * @throws PorteInexistanteDansLaPieceException si un vivant essaie de franchir une porte qui n'est pas dans la pièce où il se trouve
     * @throws PorteFermeException si un vivant essaie de frnachir une porte fermée
     */
    public void franchir(Porte porte) throws PorteFermeException,PorteInexistanteDansLaPieceException{
        if (!(this.getPiece().aLaPorte(porte))){
            throw new PorteInexistanteDansLaPieceException(String.format("La porte %s n'existe pas dans la pièce %s",porte.getNom(),this.getPiece().getNom()));
        }
        if (porte.getEtat().equals(Etat.FERME)){
            throw new PorteFermeException(String.format("La porte  %s est fermée",porte.getNom()));
        }
        Piece PieceAFranchir = porte.getPieceAutreCote(this.getPiece());
        PieceAFranchir.entrer(this);
    }

    /**
     * Permet à un vivant de franchir une porte à partir de son nom
     * @param nom Le nom de la porte qui doit être franchie
     * @throws PorteInexistanteDansLaPieceException si un vivant essaie de franchir une porte qui n'est pas dans la pièce où il se trouve
     * @throws PorteFermeException si un vivant essaie de frnachir une porte fermée
     */
    public void franchir(String nom) throws PorteFermeException,PorteInexistanteDansLaPieceException{
        this.franchir(this.getPiece().getPorte(nom));
    }
    
    /**
     * Retourne une representation textuelle du vivant (Redéfinition de la méthode toString de la super classe Object).
     * @return Une chaine de caracteres representant le vivant et ses objets.
     */
    public String toString(){
        
        StringBuilder resultat = new StringBuilder("Nom du joueur : ");
        resultat.append(this.getNom());
        resultat.append(String.format("\nPointsDeVie : %s \nPointsDeForce : %s\n", this.getPointVie(), this.getPointForce()));
        resultat.append(this.getPiece());
        resultat.append(String.format("\nObjets de %s : ",this.getNom()));
        Objet[] listeObjets=this.tabObjets.values().toArray(new Objet[0]);
		for (int i=0;i<this.tabObjets.size();i++){
            System.out.println(listeObjets[i]);
			resultat.append(listeObjets[i].getNom());
            resultat.append("\n");
		}

        resultat.append(String.format("Les portes de la pièece  : ",this.getPiece().getPortes()));
        return resultat.toString();
    }
}
    

