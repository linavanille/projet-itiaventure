package fr.insarouen.iti.prog.aventure.data;

import java.util.Map;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.io.ObjectOutputStream;
import fr.insarouen.iti.prog.aventure.Monde;
import java.util.Collection;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;

/**
 * Classe {@code EnregistreurBD} permettant d'enregistrer un {@link }
 *  {@link ConditionDeFin} via la .
 * <p>
 *  
 * </p>
 */

public class EnregistreurBD implements Enregistreur{

    PreparedStatement insertPst;
    Connection connection;
    Monde monde;

    public EnregistreurBD(Connection connection, Monde monde)throws SQLException{
        this.connection = connection;
        this.monde = monde;

        Collection<Porte> portes = null;
        Collection<Piece> pieces = null;
        Collection<PiedDeBiche> pdbs = null;
        Collection<JoueurHumain> jhs = null;

        this.enregistreurMonde();
        for (Entite e : this.monde.getEntites()) {
            if (e instanceof Porte) {
                portes.add((Porte)e);
            }
            else { if (e instanceof Piece) {
                pieces.add((Piece)e);
            }
            else { if (e instanceof PiedDeBiche) {
                pdbs.add((PiedDeBiche)e);
            }
            else { if (e instanceof JoueurHumain) {
                jhs.add((JoueurHumain)e);
            }}}
            }
        }

        for (Piece p : pieces) {
            this.enregistreurPiece(p);
            for (Objet o : p.getObjets()) {
                if (o instanceof PiedDeBiche) {
                    this.enregistreurContientPDB(p, (PiedDeBiche)o);
                }
            }
        }
        for (Porte p : portes) {
            this.enregistreurPorte(p);
        }
        for (PiedDeBiche pdb : pdbs) {
            this.enregistreurPiedDeBiche(pdb);
        }
        for (JoueurHumain jh : jhs) {
            this.enregistreurJoueurHumain(jh);
            for (Objet o : jh.getObjets()) {
                if (o instanceof PiedDeBiche) {
                    this.enregistreurPossedePDB(jh, (PiedDeBiche)o);
                }
            }
        }
    }

    public void enregistreurMonde() throws SQLException{
        String insertSQL = "INSERT INTO Monde (nomMonde) VALUES (?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, this.monde.getNom());
    }

    public void enregistreurPorte(Porte porte) throws SQLException{
        String insertSQL = "INSERT INTO Porte (nomPorte, etat,  piece1, piece2, nomMonde) VALUES (?, ?, ?, ?, ?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        Etat etat = porte.getEtat();
        String etatString = "";
        switch(etat){
            case FERME:
                etatString = "FERME";
                break;
            case OUVERT:
                etatString = "OUVERT";
                break;
        }
        Piece[] tab = null;
        Piece[] lesPieces = porte.getPieces().toArray(tab);

        Piece piece1 = lesPieces[0];
        Piece piece2 = lesPieces[1];
        insertPst.setString(1, porte.getNom());
        insertPst.setString(2, etatString);
        insertPst.setString(3, piece1.getNom());
        insertPst.setString(4, piece2.getNom());
        insertPst.setString(5, this.monde.getNom());
    }

    public void enregistreurPiece(Piece piece) throws SQLException{
        String insertSQL = "INSERT INTO Piece (nomPiece, nomMonde) VALUES (?,?)"; 
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, piece.getNom());
        insertPst.setString(2, this.monde.getNom());
        for (Entite e : piece.getObjets()) {
            this.enregistreurContientPDB(piece, (PiedDeBiche)e);
        }
    }

    public void enregistreurPiedDeBiche(PiedDeBiche pied) throws SQLException{
        String insertSQL = "INSERT INTO PiedDeBiche (nomPDB, estDeplacable) VALUES (?,?)"; 
        boolean estDeplacable = pied.estDeplacable();
        this.insertPst = connection.prepareStatement(insertSQL);
        String nomPDB = pied.getNom();
        String estDeplacableString;
        if (estDeplacable){
            estDeplacableString = "true";
        }
        else{
            estDeplacableString = "false";
        }

        insertPst.setString(1, nomPDB);
        insertPst.setString(2, estDeplacableString);
    }

    public void enregistreurJoueurHumain(JoueurHumain joueur) throws SQLException{
        String insertSQL = "INSERT INTO JoueurHumain (nomJoueur, pointVie, pointForce, nomPiece, nomMonde) VALUES (?,?,?,?,?)"; 
        Piece piece = joueur.getPiece();
        String nomPiece = piece.getNom();
        int PV = joueur.getPointVie();
        int PF = joueur.getPointForce();
        String nomJoueur = joueur.getNom();
        insertPst.setString(1, nomJoueur);
        insertPst.setInt(2, PV);
        insertPst.setInt(3, PF);
        insertPst.setString(4, nomPiece);
        insertPst.setString(5, this.monde.getNom());
        for (Entite e : joueur.getObjets()) {
            this.enregistreurPossedePDB(joueur, (PiedDeBiche)e);
        }
    }

    public void enregistreurContientPDB(Piece piece, PiedDeBiche pied) throws SQLException{
        String insertSQL = "INSERT INTO ContientPDB (nomPDB, nomPiece) VALUES (?,?)"; 
        String nomPiece = piece.getNom();
        String nomPDB = pied.getNom();
        insertPst.setString(1, nomPDB);
        insertPst.setString(2, nomPiece);
    }

    public void enregistreurPossedePDB(JoueurHumain joueur, PiedDeBiche pied) throws SQLException{
        String insertSQL = "INSERT INTO PossedePDB (nomPDB, nomPiece) VALUES (?,?)"; 
        String nomJoueur = joueur.getNom();
        String nomPDB = pied.getNom();
        insertPst.setString(1, nomPDB);
        insertPst.setString(2, nomJoueur);
    }

    @Override
    public void enregistrer(Monde monde, Collection<ConditionDeFin> conditions) throws Throwable{
    }
}