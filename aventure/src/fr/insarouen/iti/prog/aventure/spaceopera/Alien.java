package fr.insarouen.iti.prog.aventure.spaceopera;

import fr.insarouen.iti.prog.aventure.elements.vivants.Monstre;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class Alien extends Monstre{

    public Alien(String nom, Galaxie galaxie, int pointsVie, int pointsForce, VaisseauSpatial vaisseau) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, galaxie, pointsVie, pointsForce, vaisseau);
    }

}