package fr.insarouen.iti.prog.aventure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.Analyseurs.ParseException;
import java.io.IOException;

import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.data.Enregistreur;
import fr.insarouen.iti.prog.aventure.data.EnregistreurBD;
import fr.insarouen.iti.prog.aventure.data.EnregistreurSerialisation;
import fr.insarouen.iti.prog.aventure.data.Lecteur;
import fr.insarouen.iti.prog.aventure.data.LecteurBD;
import fr.insarouen.iti.prog.aventure.data.LecteurDescription;
import fr.insarouen.iti.prog.aventure.data.LecteurSerialisation;
import fr.insarouen.iti.prog.aventure.data.LecteurDescriptionFactory;
import fr.insarouen.iti.prog.aventure.data.LecteurDescriptionFactoryGlobal;
import fr.insarouen.iti.prog.aventure.data.LecteurCompilation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

/**
 * Classe main permettant d'interagir avec le jeu Aventure.
 * Elle propose un menu pour :
 * <ul>
 *   <li>jouer un tour ou toute une partie</li>
 *   <li>charger un fichier de description (plusieurs types)</li>
 *   <li>charger ou sauvegarder une partie via sérialisation</li>
 *   <li>enregistrer/charger une partie depuis une base de données</li>
 * </ul>
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

	/** URL de connexion à la base de données PostgreSQL. */
	private static String url = "jdbc:postgresql://iti-pg.insa-rouen.fr:5432/grtt8";
	
	/** Nom d'utilisateur de la base de données. */
	private static String login = "grtt8";

	/** Mot de passe de la base de données. */
	private static String password = "grtt8";
	
	/**
	 * Méthode principale du programme.
	 * Affiche un menu permettant de lancer diverses actions sur le jeu.
	 *
	 * @param args Les arguments passés au programme.
	 * @throws ITIAventureException Si une erreur liée au jeu se produit.
	 * @throws SQLException Si une erreur SQL survient.
	 */
    public static void main(String[] args) throws ITIAventureException, SQLException {
	while (true) {
	    System.out.println();
	    System.out.println();
	    System.out.println("0/ Jouer un tour"); 
	    System.out.println("1/ Jouer une partie complète"); 
	    System.out.println("2/ Charger un fichier de description"); 
		System.out.println("3/ Charger un fichier de description factory"); 
		System.out.println("4/ Charger un fichier de description factory global (itiaventure ou spaceopera)"); 
		System.out.println("5/ Charger un fichier de description via compilation"); 
		System.out.println("6/ Charger une sauvegarde");
		System.out.println("7/ Charger la partie en cours depuis la base de données");
		System.out.println("8/ Sauvegarder une partie");
		System.out.println("9/ Enregistrer la partie en cours dans la base de données");
		System.out.println("10/ Quitter le jeu");

	    try {
		switch (scanner.nextInt()) {
		case 0:
		    if (simulateur == null) {
			System.out.println("Impossible de jouer un tour. Vous n'avez pas sélectionné de fichier de jeu.");
		    } else {
			simulateur.executerUnTour();
		    }
		    break;
		case 1:
		    if (simulateur == null) {
			System.out.println("Impossible de jouer une partie. Vous n'avez pas sélectionné de fichier de jeu.");
		    } else {
			simulateur.executerJusquALaFin();
		    }
		    break;
		case 2:
		    chargerFichierDescription();
			break;
		case 3:
			chargerFichierDescriptionFactory();
		    break;
		case 4:
			chargerFichierDescriptionFactoryGlobal();
		    break;
		case 5:
			chargerFichierCompilation();
		    break;
		case 6:
			chargerFichierSerialisation();
		    break;	
		case 7:
			chargerBD();
			break;
		case 8:
			sauvegarderFichierSerialisation();
			break;
		case 9:
			enregistrerBD();
			break;
		case 10:
			System.out.println("Merci d'avoir joué !");
		    scanner.close();
		    System.exit(0);
		    break;
		default:
		    System.err.println("Choisissez une option (valeur entre 0 et 10)");
		}
	    } catch (java.util.InputMismatchException e) {
		System.out.println("Veuillez saisir une option correcte (valeur entre 0 et 10)");
		scanner.nextLine();
	    }
	}
    }

	/**
	 * Charge un fichier de description textuel standard et initialise une partie.
	 */
    public static void chargerFichierDescription() {
	System.out.println("Chargement d'un fichier textuel de description");
	System.out.print("Veuillez saisir le nom du fichier à charger: ");
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

	/**
	 * Charge un fichier de description via la factory de base et initialise une partie.
	 */
	public static void chargerFichierDescriptionFactory() {
	System.out.println("Chargement d'un fichier textuel de description");
	System.out.print("Veuillez saisir le nom du fichier à charger: ");
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
	 * Charge un fichier de description via une factory globale prenant en charge différents types (ex : itiaventure, spaceopera).
	 */
	public static void chargerFichierDescriptionFactoryGlobal() {
	System.out.println("Chargement d'un fichier textuel de description");
	System.out.print("Veuillez saisir le nom du fichier à charger: ");
	nomFichier = scanner.next();
	try {
	    lecteur = new LecteurDescriptionFactoryGlobal(new FileReader(nomFichier));
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
	 * Sauvegarde la partie en cours dans un fichier via la sérialisation.
	 */
    public static void sauvegarderFichierSerialisation() {
	System.out.println("Sauvegarde de la partie en cours");
	if (monde == null) {
	    System.out.println("Vous n'avez pas de partie en cours");
	} else {
	    System.out.print("Veuillez saisir le nom du fichier pour la sauvegarde: ");
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
	 * Charge une partie précédemment sauvegardée via la sérialisation.
	 */
    public static void chargerFichierSerialisation() {
	System.out.println("Chargement d'une sauvegarde");
	System.out.print("Veuillez saisir le nom du fichier à charger: ");
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

	/**
	 * Enregistre la partie en cours dans la base de données.
	 *
	 * @throws SQLException Si une erreur survient lors de l'enregistrement dans la base.
	 */
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

	/**
	 * Charge une partie enregistrée dans la base de données.
	 *
	 * @throws SQLException Si une erreur survient lors du chargement depuis la base.
	 */
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

	/**
	 * Charge un fichier de description via compilation et initialise une partie.
	 */
	public static void chargerFichierCompilation() {
    	System.out.println("Chargement d'un fichier textuel de description");
		System.out.print("Veuillez saisir le nom du fichier à charger: ");
		nomFichier = scanner.next();
		try (FileReader fluxTexte = new FileReader(nomFichier)) {
            LecteurCompilation lecteur = new LecteurCompilation(fluxTexte);
			monde = lecteur.getMonde();
			conditionsDeFin = lecteur.getConditionsDeFin();
			simulateur = new Simulateur(monde, conditionsDeFin);            
        } 
		catch (FileNotFoundException e) {
        	System.err.println("Fichier non trouvé : " + nomFichier);            
        } 
		catch (IOException e) {  
        	System.err.println("Erreur d'entrée ou sortie : " + e.getMessage());                      
        }
	}	

}
