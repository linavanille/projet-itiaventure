package fr.insarouen.iti.prog.aventure.elements.structure ;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;

import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;

/**
 * Représente une pièce dans le monde du jeu.
 * <p>
 * Une pièce est un élément structurel dans le jeu, elle peut contenir des objets, des vivants (personnages ou créatures),
 * et des portes reliant d'autres pièces. Les objets et vivants peuvent être ajoutés ou retirés de la pièce.
 * </p>
 */
public class Piece extends ElementStructurel {

    /** Contient les objets présents dans la pièce, indexés par leur nom.
    */
    protected Map <String,Objet> tabObjets= new HashMap<>();

    /** Contient les vivants présents dans la pièce, indexés par leur nom.
    */
    protected Map <String,Vivant> tabVivants = new HashMap<>();
    
    /** Contient les portes de la pièce, indexées par leur nom.
    */
    protected Map<String,Porte> tabPortes = new HashMap<>();

    /**
     * Constructeur pour créer une nouvelle pièce.
     * 
     * @param nom le nom de la pièce.
     * @param monde le monde auquel appartient la pièce.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom de la pièce est déjà utilisé dans le monde.
     */
    public Piece (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    }

    /**
     * Vérifie si un objet est présent dans la pièce.
     * 
     * @param objet l'objet à vérifier.
     * @return {@code true} si l'objet est présent, {@code false} sinon.
     */
    public boolean contientObjet(Objet objet){
        return this.contientObjet(objet.getNom());
    }

    /**
     * Vérifie si un objet, identifié par son nom, est présent dans la pièce.
     * 
     * @param nomObjet le nom de l'objet.
     * @return {@code true} si l'objet est présent, {@code false} sinon.
     */
    public boolean contientObjet(String nomObjet){
       return this.tabObjets.containsKey(nomObjet);
    }

    /**
     * Dépose un objet dans la pièce.
     * 
     * @param objet l'objet à déposer.
     */
    public void deposer(Objet objet) {
        this.tabObjets.put(objet.getNom(), objet);
    }

    /**
     * Retire un objet de la pièce.
     * 
     * @param objet l'objet à retirer.
     * @return l'objet retiré.
     * @throws ObjetAbsentDeLaPieceException si l'objet n'est pas présent dans la pièce.
     * @throws ObjetNonDeplacableException si l'objet n'est pas déplaçable.
     */
    public Objet retirer(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        return this.retirer(objet.getNom());
    }

    /**
     * Retire un objet de la pièce, identifié par son nom.
     * 
     * @param nom le nom de l'objet à retirer.
     * @return l'objet retiré.
     * @throws ObjetAbsentDeLaPieceException si l'objet n'est pas présent dans la pièce.
     * @throws ObjetNonDeplacableException si l'objet n'est pas déplaçable.
     */
    public Objet retirer(String nom) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        Objet rep = getObjet(nom);
        
        if (rep == null) {
            throw new ObjetAbsentDeLaPieceException(String.format("Objet %s absent de la piece %s",nom,this.getNom()));
        }
        
        if (!rep.estDeplacable()) {
            throw new ObjetNonDeplacableException(String.format("L'objet %s n'est pas déplaçable",nom));
        }
        this.tabObjets.remove(nom);
        return rep;
    }

    /**
     * Obtient un objet de la pièce par son nom.
     * 
     * @param nom le nom de l'objet.
     * @return l'objet correspondant au nom donné, ou {@code null} si aucun objet n'a ce nom.
     */
    public Objet getObjet(String nom){
        return this.tabObjets.get(nom);
    }

    /**
     * Retourne une collection non modifiable des objets présents dans la pièce.
     * 
     * @return une collection d'objets dans la pièce.
     */
    public Collection<Objet> getObjets(){
        return Collections.unmodifiableCollection(this.tabObjets.values());
    }

    /**
     * Retourne une collection non modifiable des vivants présents dans la pièce.
     * 
     * @return une collection de vivants dans la pièce.
     */
    public Collection<Vivant> getVivants(){
        return Collections.unmodifiableCollection(this.tabVivants.values());
    }

    /**
     * Vérifie si un vivant est présent dans la pièce.
     * 
     * @param vivant le vivant à vérifier.
     * @return {@code true} si le vivant est présent, {@code false} sinon.
     */
    public boolean contientVivant (Vivant vivant){
        return this.contientVivant(vivant.getNom());
    }

    /**
     * Vérifie si un vivant, identifié par son nom, est présent dans la pièce.
     * 
     * @param nomVivant le nom du vivant.
     * @return {@code true} si le vivant est présent, {@code false} sinon.
     */
    public boolean contientVivant (String nomVivant){
        return this.tabVivants.containsKey(nomVivant);
    }

    /**
     * Fait entrer un vivant dans la pièce.
     * 
     * @param vivant le vivant à faire entrer.
     */
    public void entrer ( Vivant vivant) {
        if (!this.contientVivant(vivant)){
            if (vivant.getPiece()!=null){
                try {
                    vivant.getPiece().sortir(vivant);
                }
                catch( VivantAbsentDeLaPieceException ex){
                    throw new Error("Ne devrait pas arriver",ex);
                }
            }
            this.tabVivants.put(vivant.getNom(), vivant);
            vivant.setPiece(this);
        }
    }

    /**
     * Fait sortir un vivant de la pièce.
     * 
     * @param vivant le vivant à faire sortir.
     * @return le vivant qui a été retiré de la pièce.
     * @throws VivantAbsentDeLaPieceException si le vivant n'est pas présent dans la pièce.
     */
    public Vivant sortir (Vivant vivant) throws VivantAbsentDeLaPieceException{
        return this.sortir(vivant.getNom());
    }
    
    /**
     * Fait sortir un vivant de la pièce, identifié par son nom.
     * 
     * @param nom le nom du vivant à faire sortir.
     * @return le vivant qui a été retiré de la pièce.
     * @throws VivantAbsentDeLaPieceException si le vivant n'est pas présent dans la pièce.
     */
    public Vivant sortir (String nom) throws VivantAbsentDeLaPieceException{
        Vivant v= this.tabVivants.remove(nom);
        if (v==null){
            throw new VivantAbsentDeLaPieceException(String.format("Le vivant %s n'est pas dans la piece %s",nom,this.getNom()));
        }
        v.setPiece(null);
        return v;
    }   

    /**
     * Ajoute une porte à la pièce.
     * 
     * @param porte la porte à ajouter.
     */
    protected void addPorte(Porte porte){
        this.tabPortes.put(porte.getNom(),porte);
    }

    /**
     * Vérifie si la pièce possède une porte spécifique.
     * 
     * @param porte la porte à vérifier.
     * @return {@code true} si la porte est présente, {@code false} sinon.
     */
    public boolean aLaPorte(Porte porte){
        return this.tabPortes.containsValue(porte);
    }

    /**
     * Vérifie si la pièce possède une porte identifiée par son nom.
     * 
     * @param nom le nom de la porte.
     * @return {@code true} si la porte est présente, {@code false} sinon.
     */
    public boolean aLaPorte(String nom){
        return this.tabPortes.containsKey(nom);
    }

    /**
     * Retourne une collection non modifiable des portes de la pièce.
     * 
     * @return une collection de portes dans la pièce.
     */
    public Collection<Porte> getPortes(){
        return Collections.unmodifiableCollection(this.tabPortes.values());
    }

    /**
     * Obtient une porte par son nom.
     * 
     * @param nom le nom de la porte.
     * @return la porte correspondant au nom donné, ou {@code null} si aucune porte n'a ce nom.
     */
    public Porte getPorte(String nom){
        return this.tabPortes.get(nom);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la pièce.
     * 
     * @return une chaîne représentant les objets et vivants présents dans la pièce.
     */
    public String toString(){
        
        StringBuilder resultat = new StringBuilder("Nom de la pièce : ");
        resultat.append(super.getNom());
        resultat.append(String.format("\nObjets de la pièce %s: ", this.getNom()));
        if (!(this.tabObjets == null)){
            Objet[] listeObjets=this.getObjets().toArray(new Objet[0]);
            for (int i=0;i<this.tabObjets.size();i++){
                if (!(listeObjets[i]== null)){
                resultat.append(listeObjets[i].getNom());
                resultat.append("\n");
                }
            }
        } 

        if (!(tabVivants == null)){
            resultat.append(String.format("\nVivants de la pièce %s: ",this.getNom()));

            Vivant [] listeVivants=this.getVivants().toArray(new Vivant[0]);
            for (int i=0;i<this.tabVivants.size();i++){
                resultat.append(listeVivants[i].getNom());
                resultat.append("\n");
            }
        }
        
        return resultat.toString();
    }
}
