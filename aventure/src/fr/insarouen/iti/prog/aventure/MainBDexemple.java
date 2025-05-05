package fr.insarouen.iti.prog.aventure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

/* Cette classe est un main qui sert d'exemple d'interaction entre java et une base de donnée postgresql */
public class MainBDexemple{

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8", "grtt8", "grtt8");
        PreparedStatement pst = connection.prepareStatement("SELECT Nom, Annee_naissance FROM zoo WHERE Annee_naissance >= 2000");
        ResultSet laTable = pst.executeQuery();
        System.out.println("Voici les années de naissance des animaux nés dans les années 2000:");
        while (laTable.next()){
            String nom = laTable.getString("nom");
            Integer annee_naissance = laTable.getInt("annee_naissance");

        System.out.println(String.format("nom: %s | année de naissance: %d", nom, annee_naissance));
        };
        pst.close();
        connection.close();

    }
}