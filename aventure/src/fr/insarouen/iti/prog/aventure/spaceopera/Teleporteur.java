package fr.insarouen.iti.prog.aventure.spaceopera;

import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class Teleporteur extends Porte{

    public Teleporteur(String nom, Galaxie galaxie, VaisseauSpatial vaisseauA, VaisseauSpatial vaisseauB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, galaxie, vaisseauA, vaisseauB);
    }

    public Teleporteur(String nom, Galaxie galaxie, LecteurBadge lecteurBadge, VaisseauSpatial vaisseauA, VaisseauSpatial vaisseauB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, galaxie, lecteurBadge, vaisseauA, vaisseauB);
    }

}