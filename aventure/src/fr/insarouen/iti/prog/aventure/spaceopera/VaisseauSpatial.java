package fr.insarouen.iti.prog.aventure.spaceopera;

import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;

public class VaisseauSpatial extends Piece{

    public VaisseauSpatial(String nom, Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, galaxie);
    }

    public String toString(){
        
        StringBuilder resultat = new StringBuilder("Nom du vaisseau spatial : ");
        resultat.append(super.getNom());
        resultat.append(String.format("\nObjets du vaisseau spatial %s: ", this.getNom()));
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
            resultat.append(String.format("\nVivants du vaisseau spatial %s: ",this.getNom()));

            Vivant [] listeVivants=this.getVivants().toArray(new Vivant[0]);
            for (int i=0;i<this.tabVivants.size();i++){
                resultat.append(listeVivants[i].getNom());
                resultat.append("\n");
            }
        }
        
        return resultat.toString();
    }

}