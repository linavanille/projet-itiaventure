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
 * {@link ConditionDeFin} via la .
 * <p>
 * 
 * </p>
 */

public class EnregistreurBD implements Enregistreur {

    PreparedStatement insertPst;
    Connection connection;
    Monde monde;

    public EnregistreurBD(Connection connection, Monde monde) throws SQLException {
        this.connection = connection;
        this.monde = monde;
        this.viderBD();
    }

    public void viderBD() throws SQLException {
        String insertSQL = "TRUNCATE TABLE possedePDB, contientPDB, JoueurHumain, PiedDeBiche, Porte, Piece, Monde";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.executeUpdate();
    }

    public void enregistreurMonde() throws SQLException {
        String insertSQL = "INSERT INTO Monde (nomMonde) VALUES (?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, this.monde.getNom());
        insertPst.executeUpdate();
    }

    public void enregistreurPorte(Porte porte) throws SQLException {
        String insertSQL = "INSERT INTO Porte (nomPorte, etat,  piece1, piece2, nomMonde) VALUES (?, ?, ?, ?, ?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        Etat etat = porte.getEtat();
        String etatString = "";
        switch (etat) {
            case Etat.FERME:
                etatString = "FERME";
                break;
            case Etat.OUVERT:
                etatString = "OUVERT";
                break;
            case Etat.VERROUILLE:
                etatString = "VERROUILLE";
                break;
        }
        Piece[] lesPieces = porte.getPieces().toArray(new Piece[0]);

        Piece piece1 = lesPieces[0];
        Piece piece2 = lesPieces[1];
        insertPst.setString(1, porte.getNom());
        insertPst.setString(2, etatString);
        insertPst.setString(3, piece1.getNom());
        insertPst.setString(4, piece2.getNom());
        insertPst.setString(5, this.monde.getNom());
        insertPst.executeUpdate();
    }

    public void enregistreurPiece(Piece piece) throws SQLException {
        String insertSQL = "INSERT INTO Piece (nomPiece, nomMonde) VALUES (?,?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, piece.getNom());
        insertPst.setString(2, this.monde.getNom());
        insertPst.executeUpdate();
        for (Entite e : piece.getObjets()) {
            if (e instanceof PiedDeBiche) {
                this.enregistreurContientPDB(piece, (PiedDeBiche) e);
            }
        }
    }

    public void enregistreurPiedDeBiche(PiedDeBiche pied) throws SQLException {
        String insertSQL = "INSERT INTO PiedDeBiche (nomPDB, estDeplacable) VALUES (?,?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        String estDeplacableString;
        if (pied.estDeplacable()) {
            estDeplacableString = "true";
        } else {
            estDeplacableString = "false";
        }

        insertPst.setString(1, pied.getNom());
        insertPst.setString(2, estDeplacableString);
        insertPst.executeUpdate();
    }

    public void enregistreurJoueurHumain(JoueurHumain joueur) throws SQLException {
        String insertSQL = "INSERT INTO JoueurHumain (nomJoueur, pointVie, pointForce, nomPiece, nomMonde) VALUES (?,?,?,?,?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, joueur.getNom());
        insertPst.setInt(2, joueur.getPointVie());
        insertPst.setInt(3, joueur.getPointForce());
        insertPst.setString(4, joueur.getPiece().getNom());
        insertPst.setString(5, this.monde.getNom());
        insertPst.executeUpdate();
        for (Entite e : joueur.getObjets()) {
            if (e instanceof PiedDeBiche) {
                this.enregistreurPossedePDB(joueur, (PiedDeBiche) e);
            }
        }
    }

    public void enregistreurContientPDB(Piece piece, PiedDeBiche pied) throws SQLException {
        String insertSQL = "INSERT INTO ContientPDB (nomPDB, nomPiece) VALUES (?,?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, pied.getNom());
        insertPst.setString(2, piece.getNom());
        insertPst.executeUpdate();
    }

    public void enregistreurPossedePDB(JoueurHumain joueur, PiedDeBiche pied) throws SQLException {
        String insertSQL = "INSERT INTO PossedePDB (nomPDB, nomPiece) VALUES (?,?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, pied.getNom());
        insertPst.setString(2, joueur.getNom());
        insertPst.executeUpdate();
    }

    @Override
    public void enregistrer(Monde monde, Collection<ConditionDeFin> conditions) throws Throwable {
        Collection<Porte> portes = new ArrayList<>();
        Collection<Piece> pieces = new ArrayList<>();
        Collection<PiedDeBiche> pdbs = new ArrayList<>();
        Collection<JoueurHumain> jhs = new ArrayList<>();

        this.enregistreurMonde();
        for (Entite e : this.monde.getEntites()) {
            if (e instanceof Porte) {
                portes.add((Porte) e);
            } 
            else {
                if (e instanceof Piece) {
                    pieces.add((Piece) e);
                } 
                else {
                    if (e instanceof PiedDeBiche) {
                        pdbs.add((PiedDeBiche) e);
                    } 
                    else {
                        if (e instanceof JoueurHumain) {
                            jhs.add((JoueurHumain) e);
                        }
                    }
                }
            }
        }

        for (Piece p : pieces) {
            this.enregistreurPiece(p);
            for (Objet o : p.getObjets()) {
                if (o instanceof PiedDeBiche) {
                    this.enregistreurContientPDB(p, (PiedDeBiche) o);
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
                    this.enregistreurPossedePDB(jh, (PiedDeBiche) o);
                }
            }
        }
    }
}