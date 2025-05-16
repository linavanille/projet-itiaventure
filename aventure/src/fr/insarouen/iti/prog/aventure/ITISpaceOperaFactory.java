package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.spaceopera.Galaxie;
import fr.insarouen.iti.prog.aventure.spaceopera.Badge;
import fr.insarouen.iti.prog.aventure.spaceopera.LecteurBadge;
import fr.insarouen.iti.prog.aventure.spaceopera.Alien;
import fr.insarouen.iti.prog.aventure.spaceopera.Teleporteur;
import fr.insarouen.iti.prog.aventure.spaceopera.VaisseauSpatial;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.ITIAventureFactory;

public class ITISpaceOperaFactory extends ITIAventureFactory {
/*
    public Galaxie creationMonde(String nom){
        return new Galaxie(nom);
    }

    public Alien creationMonstre(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException {
        return new Alien(nom, monde, pointsVie, pointsForce, piece);
    }

    public VaisseauSpatial creationPiece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException  {
        return new VaisseauSpatial(nom, monde);
    }

    public Teleporteur creationPorte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Teleporteur(nom, monde, pieceA, pieceB);
    }

    public LecteurBadge creationPorteSerrure(String nom, Monde monde, Piece pieceA, Piece pieceB ) throws NomDEntiteDejaUtiliseDansLeMondeException{
        Badge badge = creationSerrure(monde);
        return new Lecteur(nom, monde, badge, pieceA, pieceB);
    }

    public Bagde creationSerrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Badge(monde);
    }

    public void creationCle(String nomPorte, Monde monde, String nomPiece)throws NomDEntiteDejaUtiliseDansLeMondeException {
        Badge badge =((Porte)monde.getEntite(nomPorte)).getSerrure().creerCle();
        ((Piece)monde.getEntite(nomPiece)).deposer(badge);
    }

*/



    public Galaxie creationMonde(String nom){
        return new Galaxie(nom);
    }

    public Alien creationMonstre(String nom, Galaxie galaxie, int pointsVie, int pointsForce, VaisseauSpatial vaisseau) throws NomDEntiteDejaUtiliseDansLeMondeException {
        return new Alien(nom, galaxie, pointsVie, pointsForce, vaisseau);
    }

    public VaisseauSpatial creationPiece(String nom, Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException  {
        return new VaisseauSpatial(nom, galaxie);
    }

    public Teleporteur creationPorte(String nom, Galaxie galaxie, VaisseauSpatial vaisseauA, VaisseauSpatial vaisseauB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Teleporteur(nom, galaxie, vaisseauA, vaisseauB);
    }

    public Teleporteur creationPorteSerrure(String nom, Galaxie galaxie, VaisseauSpatial vaisseauA, VaisseauSpatial vaisseauB ) throws NomDEntiteDejaUtiliseDansLeMondeException{
        LecteurBadge lecteurBadge = creationSerrure(galaxie);
        return new Teleporteur(nom, galaxie, lecteurBadge, vaisseauA, vaisseauB);
    }

    public LecteurBadge creationSerrure(Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new LecteurBadge(galaxie);
    }

    public void creationCle(String nomTeleporteur, Galaxie galaxie, String nomVaisseau) throws NomDEntiteDejaUtiliseDansLeMondeException {
        Badge badge =(Badge)(((Teleporteur)galaxie.getEntite(nomTeleporteur)).getSerrure().creerCle());
        ((VaisseauSpatial)galaxie.getEntite(nomVaisseau)).deposer(badge);
    }
}