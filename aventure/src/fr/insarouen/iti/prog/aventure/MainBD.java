package fr.insarouen.iti.prog.aventure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

/* Cette classe est un main qui permet l'interaction entre le jeu itiaventure et les données de la base de donnée postgresql grtt8 */
public class MainBD{

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
        String url = "jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8";
        String login = "grtt8";
        String password = "grtt8";

        // utilisation d'un gestionnaire de contexte pour la connexion à la base de données grtt8
        // fermeture automatique: pas besoin des lignes pst.close(); et connection.close();
        try (Connection connection = DriverManager.getConnection(url, login, password)){

            String insertSQL = "INSERT INTO ZOO" + 
                "(Animal, Nom, Annee_naissance, Espece, Gardien, Prenom, Salaire, Classe, Origine, Emplacement, Surface, Type_empl, Libelle_empl)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // utilisation d'un gestionnaire de contexte pour insérer des données dans la base de données
            try (PreparedStatement insertPst = connection.prepareStatement(insertSQL)){
                int numero_animal = 12;

                if (numero_animal != 12){
                    insertPst.setInt(1, numero_animal);                    
                    insertPst.setString(2, "Dylan");             
                    insertPst.setInt(3, 2004);                   
                    insertPst.setString(4, "Singe");               
                    insertPst.setString(5, "Gamotte");          
                    insertPst.setString(6, "Nathan");           
                    insertPst.setDouble(7, 3000);              
                    insertPst.setString(8, "mammifère");        
                    insertPst.setString(9, "Europe");             
                    insertPst.setInt(10, 80);                    
                    insertPst.setInt(11, 1300);                     
                    insertPst.setInt(12, 11);                      
                    insertPst.setString(13, "Savane");

                    // executeUpdate() est la méthode pour INSERT UPDATE et DELETE (nbLignes = 1)
                    int nbLignes = insertPst.executeUpdate();
                    System.out.println(nbLignes + " ligne a été insérée dans la table Zoo.");
                } else{
                    System.out.println("Ajout impossible à la table, cette donnée existe déjà");
                }

        }
        catch (SQLException e){
            System.err.println("Une erreur SQL est survenu. " + e.getMessage());
        }
        }
    }
}