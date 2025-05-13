package fr.insarouen.iti.prog.aventure.data;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;

import java.util.Collection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LecteurBD implements Lecteur {

    private Connection connection;
    private Monde monde;

    public LecteurBD(Connection connection) throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException, ActivationImpossibleException {

        this.connection = connection;

        this.lecteurMonde();
        this.lecteurPiedDeBiche();
        this.lecteurPiece();
        this.lecteurJoueurHumain();
        this.lecteurPorte();
        this.connection.close();
    }

    @Override
    public Monde getMonde() {
        return this.monde;
    }

    @Override
    public Collection<ConditionDeFin> getConditionsDeFin() {
        return null;
    }

    public void lecteurMonde() throws SQLException {
        String requete = "SELECT * FROM Monde";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomMonde = laTable.getString("nomMonde");
            this.monde = new Monde(nomMonde);
        }

        pst.close();
    }

    public void lecteurPiedDeBiche() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        String requete = "SELECT nomPiece FROM Piece";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomPDB = laTable.getString("nomPDB");
            Boolean estDeplacable = laTable.getBoolean("estDeplacable");
            PiedDeBiche pdb = new PiedDeBiche(nomPDB, this.getMonde());
        }

        pst.close();
    }

    public void lecteurPiece() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException {
        String requete = "SELECT nomPiece FROM Piece";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomPiece = laTable.getString("nompiece");
            Piece piece = new Piece(nomPiece, this.getMonde());

            String requetePDB = "SELECT nomPiece FROM ContientPDB WHERE nomPiece = ?";
            PreparedStatement pstPDB = this.connection.prepareStatement(requetePDB);
            pstPDB.setString(1, nomPiece);
            ResultSet lesPieces = pstPDB.executeQuery();

            while (lesPieces.next()) {
                String nomPDB = lesPieces.getString("nomPDB");
                piece.deposer((Objet)this.getMonde().getEntite("nomPDB"));
            }

            pstPDB.close();
        }

        pst.close();
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
            Collection<PiedDeBiche> inventaire = null;

            String requetePDB = "SELECT nomPDB FROM PossedePDB WHERE nomJoueur = ?";
            PreparedStatement pstPDB = this.connection.prepareStatement(requetePDB);
            pstPDB.setString(1, nomJoueur);
            ResultSet lesPDB = pstPDB.executeQuery();

            while (lesPDB.next()) {
                inventaire.add((PiedDeBiche)this.getMonde().getEntite(lesPDB.getString("nomPDB")));
            }

            pstPDB.close();

            JoueurHumain jh = new JoueurHumain(nomJoueur, this.getMonde(), pointVie, pointForce, (Piece)this.getMonde().getEntite(nomPiece), inventaire.toArray(new Objet[0]));
        }

        pst.close();
    }

    public void lecteurPorte() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException {
        String requete = "SELECT nomPorte, etat, piece1, piece2 FROM porte";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomPorte = laTable.getString("nomPorte");
            String etat = laTable.getString("etat");

            String piece1 = laTable.getString("piece1");
            String piece2 = laTable.getString("piece2");
            Porte porte = new Porte(nomPorte, this.getMonde(), (Piece)this.getMonde().getEntite(piece1), (Piece)this.getMonde().getEntite(piece2));
            if (etat == "OUVERT") {
                porte.activer();
            }
        }

        pst.close();
    }
}