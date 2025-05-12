package fr.insarouen.iti.prog.aventure.data;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LecteurBD {

    private Connection connection;
    private Monde monde;

    public LecteurBD() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        String url = "jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8";
        String login = "grtt8";
        String password = "grtt8";

        this.connection = DriverManager.getConnection(url, login, password);

        this.lecteurMonde();
        this.lecteurPiece();
        this.lecteurJoueurHumain();
        this.lecteurPiedDeBiche();
    }

    public void lecteurMonde() throws SQLException {
        String requete = "SELECT * FROM Monde";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomMonde = laTable.getString("nomMonde");
            this.monde = new Monde(nomMonde);
        }
    }

    public void lecteurPiece() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException {
        String requete = "SELECT nomPiece FROM Piece";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomPiece = laTable.getString("nompiece");
            Piece piece = new Piece(nomPiece, this.monde);
        }
    }

    public void lecteurJoueurHumain() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException {
        String requete = "SELECT nomJoueur, pointVie, pointForce, nomPiece FROM JoueurHumain";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomJoueur = laTable.getString("nomJoueur");
            Integer pointVie = laTable.getInt("pointVie");
            Integer pointForce = laTable.getInt("pointForce");
            String nomPiece = laTable.getString("nomPiece");
            JoueurHumain jh = new JoueurHumain(nomJoueur, this.monde, pointVie, pointForce, (Piece)this.monde.getEntite(nomPiece));
        }
    }

    public void lecteurPiedDeBiche() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        String requete = "SELECT nomPiece FROM Piece";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomPDB = laTable.getString("nomPDB");
            Boolean estDeplacable = laTable.getBoolean("estDeplacable");
            PiedDeBiche pdb = new PiedDeBiche(nomPDB, this.monde);
        }
    }

    public void lecteurPorte() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException {
        String requete = "SELECT nomPorte, etat FROM porte";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomPorte = laTable.getString("nomPorte");
            String etat = laTable.getString("etat");
        }
    }
}