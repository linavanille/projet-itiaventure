package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.vivants.Monstre;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;

public class ITIAventureFactory{

    private Monde monde;

    public Monde creationMonde(String nom){
        this.monde = new Monde(nom);
        return this.monde;
    }

    public Monstre creationMonstre(String nom, int pointsVie, int pointsForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException {
        return new Monstre(nom, this.monde, pointsVie, pointsForce, piece);
    }

    public Piece creationPiece(String nom)  throws NomDEntiteDejaUtiliseDansLeMondeException  {
        return new Piece(nom, this.monde);
    }

    public Porte creationPorte(String nom, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Porte(nom, this.monde, pieceA, pieceB);
    }

    public Porte creationPorteSerrure(String nom, Piece pieceA, Piece pieceB ) throws NomDEntiteDejaUtiliseDansLeMondeException{
        Serrure serrure = creationSerrure();
        return new Porte(nom, this.monde, serrure, pieceA, pieceB);
    }

    public Serrure creationSerrure() throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Serrure(this.monde);
    }

    public void creationCle(String nomPorte, String nomPiece)throws NomDEntiteDejaUtiliseDansLeMondeException {
        Cle cle =((Porte)this.monde.getEntite(nomPorte)).getSerrure().creerCle();
        ((Piece)this.monde.getEntite(nomPiece)).deposer(cle);
    }
}

