package fr.insarouen.iti.prog.aventure;

import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.sql.*;

public class MainBD{

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8", "grtt8", "grtt8");
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM zoo");
        ResultSet laTable = pst.executeQuery();
        while (laTable.next()){
            //Integer animal = laTable.getInt("animal");
            String nom = laTable.getString("nom");/*
            Integer annee_naissance = laTable.getInt("annee_naissance");
            String espece = laTable.getString("espece");
            String gardien = laTable.getString("gardien");
            String prenom = laTable.getString("prenom");
            Float salaire = laTable.getFloat("salaire");
            String classe = laTable.getString("classe");
            String origine = laTable.getString("origine");
            Integer emplacement = laTable.getInt("emplacement");
            Integer surface = laTable.getInt("surface");
            Integer type_empl = laTable.getInt("type_empl");
            String libelle_empl = laTable.getString("libelle_empl");

        System.out.println(String.format("%d | %s | %d | %s | %s | %s | %f | %s | %s | %d | %d | %d | %s"),
        animal, nom, annee_naissance, espece, gardien, prenom, salaire, classe, origine, emplacement, surface,
        type_empl, libelle_empl);*/

        System.out.println(String.format("%s",nom));
        };
        pst.close();
        connection.close();

    }
}