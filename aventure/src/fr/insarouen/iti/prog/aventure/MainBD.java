package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.data.Enregistreur;
import fr.insarouen.iti.prog.aventure.data.EnregistreurSerialisation;
import fr.insarouen.iti.prog.aventure.data.EnregistreurBD;
import fr.insarouen.iti.prog.aventure.data.Lecteur;
import fr.insarouen.iti.prog.aventure.data.LecteurDescription;
import fr.insarouen.iti.prog.aventure.data.LecteurBD;
import fr.insarouen.iti.prog.aventure.data.LecteurSerialisation;
import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;

import java.util.Scanner;
import java.util.Collection;

import java.io.ObjectOutputStream;

import java.lang.ClassNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

/* Cette classe est un main qui permet l'interaction entre le jeu itiaventure et les données de la base de donnée postgresql grtt8 */
public class MainBD{

    private static Enregistreur enregistreur = null;
    private static Collection<ConditionDeFin>  conditionsDeFin = null;

    public static void sauvegarderFichierSerialisation() {

        String url = "jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8";
        String login = "grtt8";
        String password = "grtt8";

        // utilisation d'un gestionnaire de contexte pour la connexion à la base de données grtt8
        // fermeture automatique: pas besoin des lignes pst.close(); et connection.close();
        try (Connection connection = DriverManager.getConnection(url, login, password)){
            Monde monde = new Monde("monde1");
            enregistreur = new EnregistreurBD(connection, monde);
            enregistreur.enregistrer(monde, conditionsDeFin);
        } catch (Throwable e){
            enregistreur = null;
            System.err.println("Une erreur SQL est survenu. " + e.getMessage() + " Sauvegarde des données impossible.");
        }
    }

    public static void main(String[] args) throws ITIAventureException{
        sauvegarderFichierSerialisation();
    }

}