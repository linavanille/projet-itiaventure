package fr.insarouen.iti.prog.aventure.data;

import java.util.Map;
import java.util.HashMap;
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
    String url;
    String login;
    String password;
    Connection connection;
    Monde monde;
    String nomMonde;

    public EnregistreurBD(Connection connection)throws SQLException{
        this.connection = connection;
        /*
        this.url = "jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8";
        this.login = "grtt8";
        this.password = "grtt8";
        this.connection = DriverManager.getConnection(url, login, password);
        */
        this.monde = monde;
        this.nomMonde = monde.getNom();
    }

    public void enregistreurMonde() throws SQLException{
        String insertSQL = "INSERT INTO Monde (nomMonde) VALUES (?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, this.nomMonde);
    }

    public void enregistreurPorte(Porte porte) throws SQLException{
        String insertSQL = "INSERT INTO Porte (nomPorte, etat,  piece1, piece2, nomMonde) VALUES (?, ?, ?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        String nomPorte = porte.getNom();
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
        HashMap<String, Piece> lesPieces = porte.getPieces();
        Piece piece1 = lesPieces.get("Piece1");
        String piece1Nom = piece1.getNom();
        Piece piece2 = lesPieces.get("Piece1");
        String piece2Nom = piece2.getNom();
        insertPst.setString(1, nomPorte);
        insertPst.setString(2, etatString);
        insertPst.setString(3, piece1Nom);
        insertPst.setString(4, piece2Nom);
        insertPst.setString(5, this.nomMonde);
    }

    public void enregistreurPiece(Piece piece) throws SQLException{
        String insertSQL = "INSERT INTO Piece (nomPiece, nomMonde) VALUES (?,?)"; 
        this.insertPst = connection.prepareStatement(insertSQL);
        String nomPiece = piece.getNom();
        insertPst.setString(1, nomPiece);
        insertPst.setString(2, this.nomMonde);
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
        insertPst.setString(5, this.nomMonde);
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