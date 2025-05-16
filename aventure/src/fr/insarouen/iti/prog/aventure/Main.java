package fr.insarouen.iti.prog.aventure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Scanner;

import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.data.Enregistreur;
import fr.insarouen.iti.prog.aventure.data.EnregistreurBD;
import fr.insarouen.iti.prog.aventure.data.EnregistreurSerialisation;
import fr.insarouen.iti.prog.aventure.data.Lecteur;
import fr.insarouen.iti.prog.aventure.data.LecteurBD;
import fr.insarouen.iti.prog.aventure.data.LecteurDescription;
import fr.insarouen.iti.prog.aventure.data.LecteurSerialisation;
import fr.insarouen.iti.prog.aventure.data.LecteurDescriptionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

/**
 * Classe Main
 * Permet de jouer, charger ou sauvegarder une partie, ou charger un fichier de description 
*/
public class Main {
	/**Constructeur par défaut pour le main */
	public Main(){}
	/** le nom du fichier  */
    private static String nomFichier;
	/** le lecteur pour lire la serialisation */
    private static Lecteur lecteur=null;
	/** l'enregistreur pour enregistrer la serialisation */
    private static Enregistreur enregistreur=null;
	/**le simulateur pour simuler */
    private static Simulateur simulateur=null;
	/**le monde de la partie */
    private static Monde monde=null;
	/**les conditions de fin de la partie */
    private static Collection<ConditionDeFin> conditionsDeFin=null;
	/**le scanner pour lire et ou enregistrer */
    private static Scanner scanner = new Scanner(System.in);

	private static String url = "jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8";
	private static String login = "grtt8";
	private static String password = "grtt8";
	
	/**
	 * Main 
	 * @param args les arguments du main
	 * @throws ITIAventureException si problème
	 */
    public static void main(String[] args) throws ITIAventureException, SQLException {
	while (true) {
	    System.out.println();
	    System.out.println();
	    System.out.println("0/ jouer un tour");
	    System.out.println("1/ jouer jusqu'à la fin sans possibilité d'arrêter");
	    System.out.println("2/ charger fichier de description");
	    System.out.println("3/ sauvegarder une partie");
	    System.out.println("4/ charger une sauvegarde");
		System.out.println("5/ enregistrer la partie dans la BD");
		System.out.println("6/ charger la partie depuis la BD");
	    System.out.println("7/ charger fichier description factory");
		System.out.println("8/ quitter");
	    try {
		switch (scanner.nextInt()) {
		case 0:
		    if (simulateur == null) {
			System.out.println("Vous n'avez pas de partie en cours");
		    } else {
			simulateur.executerUnTour();
		    }
		    break;
		case 1:
		    if (simulateur == null) {
			System.out.println("Vous n'avez pas de partie en cours");
		    } else {
			simulateur.executerJusquALaFin();
		    }
		    break;
		case 2:
		    chargerFichierDescription();
			break;
		case 3:
		    sauvegarderFichierSerialisation();
		    break;
		case 4:
		    chargerFichierSerialisation();
		    break;
		case 5:
		    enregistrerBD();
		    break;
		case 6:
		    chargerBD();
		    break;	
		case 7:
			chargerFichierDescriptionFactory();
			break;
		case 8:
			System.out.println("A bientot !");
		    scanner.close();
		    System.exit(1);
		    break;
		default:
		    System.err.println("Choisissez une valeur entre 1 et 8 compris.");
		}
	    } catch (java.util.InputMismatchException e) {
		System.out.println("Saisie incorrecte");
		scanner.nextLine();
	    }
	}
    }
	/**
	 * Méthode permettant de charger un fichier de description 
	 */
    public static void chargerFichierDescription() {
	System.out.println("Chargement d'un fichier textuel de description");
	System.out.print("Veuillez saisir le nom du fichier : ");
	nomFichier = scanner.next();
	try {
	    lecteur = new LecteurDescription(new FileReader(nomFichier));
	    monde = lecteur.getMonde();
	    conditionsDeFin = lecteur.getConditionsDeFin();
	    simulateur = new Simulateur(monde, conditionsDeFin);
	} catch (Throwable e) {
	    lecteur = null;
	    System.err.println("La lecture de votre fichier a rencontré un problème");
	    System.err.println(String.format("---> %s ",e.getMessage()));
	}
    }

	public static void chargerFichierDescriptionFactory() {
	System.out.println("Chargement d'un fichier textuel de description");
	System.out.print("Veuillez saisir le nom du fichier : ");
	nomFichier = scanner.next();
	try {
	    lecteur = new LecteurDescriptionFactory(new FileReader(nomFichier));
	    monde = lecteur.getMonde();
		System.out.println(monde.getNom());
	    conditionsDeFin = lecteur.getConditionsDeFin();
	    simulateur = new Simulateur(monde, conditionsDeFin);
	} catch (Throwable e) {
	    lecteur = null;
		e.printStackTrace();
	    System.err.println("La lecture de votre fichier a rencontré un problème");
	    System.err.println(String.format("---> %s ",e.getMessage()));
	}
    }

	/**
	 * Méthode permettant de sauvegarder une partie en cours par serialisation 
	 */
    public static void sauvegarderFichierSerialisation() {
	System.out.println("Sauvegarde de la partie en cours");
	if (monde == null) {
	    System.out.println("Vous n'avez pas de partie en cours");
	} else {
	    System.out.print("Veuillez saisir le nom du fichier : ");
	    nomFichier = scanner.next();
	    try {
		enregistreur = new EnregistreurSerialisation(new ObjectOutputStream(new FileOutputStream(nomFichier)));
		enregistreur.enregistrer(monde,conditionsDeFin);
	    } catch (Throwable e) {
		enregistreur = null;
		System.err.println("La sauvegarde a rencontré un problème");
		System.err.println(String.format("---> %s ",e.getMessage()));
	    }
	}
    }

	/**
	 * Méthode permettant de charger une partie en cours par serialisation 
	 */
    public static void chargerFichierSerialisation() {
	System.out.println("Chargement d'une sauvegarde");
	System.out.print("Veuillez saisir le nom du fichier : ");
	nomFichier = scanner.next();
	try {
	    lecteur = new LecteurSerialisation(new ObjectInputStream(new FileInputStream(nomFichier)));
	    monde = lecteur.getMonde();
	    conditionsDeFin = lecteur.getConditionsDeFin();
	    simulateur = new Simulateur(monde, conditionsDeFin);
	} catch (Exception e) {
	    lecteur = null;
	    System.err.println("La lecture de votre fichier a rencontré un problème");
	    System.err.println(e.getMessage());
	}
    }

	public static void enregistrerBD() throws SQLException{
		Connection connection = DriverManager.getConnection(url, login, password);

		try {
            enregistreur = new EnregistreurBD(connection, monde);
            enregistreur.enregistrer(monde, conditionsDeFin);
        } catch (Throwable e){
            enregistreur = null;
            e.printStackTrace();
        }   
	}

	public static void chargerBD() throws SQLException{
		Connection connection = DriverManager.getConnection(url, login, password);

		try {
			lecteur = new LecteurBD(connection);
			monde = lecteur.getMonde();
			conditionsDeFin = lecteur.getConditionsDeFin();
			simulateur = new Simulateur(monde, conditionsDeFin);
		} catch (Throwable e) {
			e.printStackTrace();
			lecteur = null;
		}

		connection.close();
	}
			
}
