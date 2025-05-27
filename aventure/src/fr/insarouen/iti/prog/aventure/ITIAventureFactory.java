package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.elements.vivants.Monstre;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;

public class ITIAventureFactory {

    public Monde creationMonde(String nom){
        return new Monde(nom);
    }

    public Monstre creationMonstre(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException {
        return new Monstre(nom, monde, pointsVie, pointsForce, piece);
    }

    public Piece creationPiece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException  {
        return new Piece(nom, monde);
    }

    public Porte creationPorte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Porte(nom, monde, pieceA, pieceB);
    }

    public Porte creationPorteSerrure(String nom, Monde monde, Piece pieceA, Piece pieceB ) throws NomDEntiteDejaUtiliseDansLeMondeException{
        Serrure serrure = creationSerrure(monde);
        return new Porte(nom, monde, serrure, pieceA, pieceB);
    }

    public Serrure creationSerrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Serrure(monde);
    }

    public void creationCle(String nomPorte, Monde monde, String nomPiece)throws NomDEntiteDejaUtiliseDansLeMondeException {
        Cle cle =((Porte)monde.getEntite(nomPorte)).getSerrure().creerCle();
        ((Piece)monde.getEntite(nomPiece)).deposer(cle);
    }
}

