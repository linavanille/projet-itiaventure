package fr.insarouen.iti.prog.aventure.spaceopera;

import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;

public class VaisseauSpatial extends Piece{

    public VaisseauSpatial(String nom, Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, galaxie);
    }

}