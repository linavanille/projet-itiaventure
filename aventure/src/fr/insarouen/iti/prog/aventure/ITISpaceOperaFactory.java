package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.spaceopera.Galaxie;
import fr.insarouen.iti.prog.aventure.spaceopera.Badge;
import fr.insarouen.iti.prog.aventure.spaceopera.LecteurBadge;
import fr.insarouen.iti.prog.aventure.spaceopera.Alien;
import fr.insarouen.iti.prog.aventure.spaceopera.Teleporteur;
import fr.insarouen.iti.prog.aventure.spaceopera.VaisseauSpatial;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class ITISpaceOperaFactory {

    public Galaxie creationGalaxie(String nom){
        return new Galaxie(nom);
    }

    public Alien creationAlien(String nom, Galaxie galaxie, int pointsVie, int pointsForce, VaisseauSpatial vaisseau) throws NomDEntiteDejaUtiliseDansLeMondeException {
        return new Alien(nom, galaxie, pointsVie, pointsForce, vaisseau);
    }

    public VaisseauSpatial creationVaisseauSpatial(String nom, Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException  {
        return new VaisseauSpatial(nom, galaxie);
    }

    public Teleporteur creationTeleporteur(String nom, Galaxie galaxie, VaisseauSpatial vaisseauA, VaisseauSpatial vaisseauB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Teleporteur(nom, galaxie, vaisseauA, vaisseauB);
    }

    public Teleporteur creationTeleporteurLecteurBadge(String nom, Galaxie galaxie, VaisseauSpatial vaisseauA, VaisseauSpatial vaisseauB ) throws NomDEntiteDejaUtiliseDansLeMondeException{
        LecteurBadge lecteurBadge = creationLecteurBadge(galaxie);
        return new Teleporteur(nom, galaxie, lecteurBadge, vaisseauA, vaisseauB);
    }

    public LecteurBadge creationLecteurBadge(Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new LecteurBadge(galaxie);
    }

    public void creationBadge(String nomTeleporteur, Galaxie galaxie, String nomVaisseau) throws NomDEntiteDejaUtiliseDansLeMondeException {
        Badge badge =(Badge)(((Teleporteur)galaxie.getEntite(nomTeleporteur)).getSerrure().creerCle());
        ((VaisseauSpatial)galaxie.getEntite(nomVaisseau)).deposer(badge);
    }
}