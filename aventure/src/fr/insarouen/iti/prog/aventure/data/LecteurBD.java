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
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe permettant de lire un monde de jeu à partir d'une base de données.
 * Elle implémente l'interface Lecteur et instancie les différents éléments du monde.
 */
public class LecteurBD implements Lecteur {

    private Connection connection;
    private Monde monde;

    /**
     * Construit un LecteurBD avec une connexion à la base de données.
     * Initialise le monde et ses éléments à partir de la base.
     *
     * @param connection Connexion JDBC à la base de données
     * @throws SQLException En cas d'erreur SQL
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Si un nom d'entité est déjà utilisé
     * @throws EntiteDejaDansUnAutreMondeException Si une entité est dans un autre monde
     * @throws ActivationImpossibleException Si une activation échoue
     */
    public LecteurBD(Connection connection) throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException, ActivationImpossibleException {
        this.connection = connection;

        this.lecteurMonde();
        this.lecteurPiedDeBiche();
        this.lecteurPiece();
        this.lecteurJoueurHumain();
        this.lecteurPorte();
    }

    /**
     * Retourne le monde lu depuis la base.
     *
     * @return Le monde
     */
    @Override
    public Monde getMonde() {
        return this.monde;
    }

    /**
     * Retourne les conditions de fin (non implémenté ici).
     *
     * @return null
     */
    @Override
    public Collection<ConditionDeFin> getConditionsDeFin() {
        return null;
    }

    /**
     * Lit le monde depuis la base de données.
     *
     * @throws SQLException En cas d'erreur SQL
     */
    public void lecteurMonde() throws SQLException {
        String requete = "SELECT * FROM Monde";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();
        while (laTable.next()) {
            String nomMonde = laTable.getString("nomMonde");
            this.monde = new Monde(nomMonde);
        }
    }

    /**
     * Lit les objets PiedDeBiche depuis la base et les ajoute au monde.
     *
     * @throws SQLException En cas d'erreur SQL
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Si le nom est déjà utilisé
     * @throws EntiteDejaDansUnAutreMondeException Si l'objet appartient déjà à un autre monde
     */
    public void lecteurPiedDeBiche() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        String requete = "SELECT nomPDB FROM PiedDeBiche";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomPDB = laTable.getString("nomPDB");
            String estDeplacableString = laTable.getString("estDeplacable");
            Boolean estDeplacable = false;
            if (estDeplacableString == "true"){
                estDeplacable = true;
            }
            PiedDeBiche pdb = new PiedDeBiche(nomPDB, this.getMonde());
        }
    }

    /**
     * Lit les pièces du monde depuis la base et les initialise.
     *
     * @throws SQLException En cas d'erreur SQL
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Si le nom est déjà utilisé
     */
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
                piece.deposer((Objet)this.getMonde().getEntite(nomPDB));
            }
        }
    }

    /**
     * Lit les données du joueur humain depuis la base.
     *
     * @throws SQLException En cas d'erreur SQL
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Si le nom est déjà utilisé
     */
    public void lecteurJoueurHumain() throws SQLException, NomDEntiteDejaUtiliseDansLeMondeException {
        String requete = "SELECT nomJoueur, pointVie, pointForce, nomPiece FROM JoueurHumain";
        PreparedStatement pst = this.connection.prepareStatement(requete);
        ResultSet laTable = pst.executeQuery();

        while (laTable.next()) {
            String nomJoueur = laTable.getString("nomJoueur");
            Integer pointVie = laTable.getInt("pointVie");
            Integer pointForce = laTable.getInt("pointForce");
            String nomPiece = laTable.getString("nomPiece");
            Collection<PiedDeBiche> inventaire = new ArrayList<>();

            String requetePDB = "SELECT nomPDB FROM PossedePDB WHERE nomJoueur = ?";
            PreparedStatement pstPDB = this.connection.prepareStatement(requetePDB);
            pstPDB.setString(1, nomJoueur);
            ResultSet lesPDB = pstPDB.executeQuery();

            while (lesPDB.next()) {
                inventaire.add((PiedDeBiche)this.getMonde().getEntite(lesPDB.getString("nomPDB")));
            }

            JoueurHumain jh = new JoueurHumain(nomJoueur, this.getMonde(), pointVie, pointForce, (Piece)this.getMonde().getEntite(nomPiece), inventaire.toArray(new Objet[0]));
        }
    }

    /**
     * Lit les portes depuis la base et les ajoute au monde, avec leur état.
     *
     * @throws SQLException En cas d'erreur SQL
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Si le nom est déjà utilisé
     * @throws ActivationImpossibleException Si le changement d'état échoue
     */
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
            switch(etat){
                case "FERME":
                    porte.setEtat(Etat.FERME);
                    break;
                case "OUVERT":
                    porte.setEtat(Etat.OUVERT);
                    break;
                case "VERROUILLE":
                    porte.setEtat(Etat.VERROUILLE);
                    break;
            }
        }
    }
}