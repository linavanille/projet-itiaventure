package fr.insarouen.iti.prog.aventure.data;

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

/**
 * Classe {@code EnregistreurBD} permettant d'enregistrer un {@link }
 *  {@link ConditionDeFin} via la .
 * <p>
 *  
 * </p>
 */
public class EnregistreurBD{

    PreparedStatement insertPst;
    String url;
    String login;
    String password;
    Connection connection;
    Monde monde;
    String nomMonde;

    public EnregistreurBD() throws SQLException{
        this.url = "jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8";
        this.login = "grtt8";
        this.password = "grtt8";
        this.connection = DriverManager.getConnection(url, login, password);
        this.monde = monde;
        this.nomMonde = monde.getNom();
    }

    public void enregistreurMonde() throws SQLException{
        String insertSQL = "INSERT INTO MONDE" + "(nomMonde)" + "VALUES (?)";
        this.insertPst = connection.prepareStatement(insertSQL);
        insertPst.setString(1, this.nomMonde);
    }

    public void enregistreurPorte(Porte porte) throws SQLException{
        String insertSQL = "INSERT INTO PORTE" + "(nomPorte, etat, nomMonde)" + "VALUES (?, ?, ?)";
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
            case VERROUILLE:
                etatString = "VERROUILLE";
                break;
            case DEVERROUILLE:
                etatString = "DEVERROUILLE";
                break;
        }
        insertPst.setString(1, nomPorte);
        insertPst.setString(2, etat_string);
        insertPst.setString(3, this.nomMonde);
    }

    public void enregistreurPiece(Piece piece) throws SQLException{
        String insertSQL = "INSERT INTO PIECE" + "(nomPiece, nomMonde)" + "VALUES (?,?)"; 
        this.insertPst = connection.prepareStatement(insertSQL);
        String nomPiece = piece.getNom();
        insertPst.setString(1, nomPiece);
        insertPst.setString(2, this.nomMonde);
    }

    public void enregistreurPiedDeBiche(PiedDeBiche pied) throws SQLException{

    }

    public void enregistreurJoueurHumain(){

    }

}
