package fr.insarouen.iti.prog.aventure.data;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFinVivantDansPiece;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.EtatDuJeu;
import fr.insarouen.iti.prog.aventure.ITIAventureFactory;
import fr.insarouen.iti.prog.aventure.ITISpaceOperaFactory;

import fr.insarouen.iti.prog.aventure.spaceopera.Galaxie;
import fr.insarouen.iti.prog.aventure.spaceopera.Badge;
import fr.insarouen.iti.prog.aventure.spaceopera.LecteurBadge;
import fr.insarouen.iti.prog.aventure.spaceopera.Alien;
import fr.insarouen.iti.prog.aventure.spaceopera.Teleporteur;
import fr.insarouen.iti.prog.aventure.spaceopera.VaisseauSpatial;

import java.util.Scanner;
import java.io.Reader;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;

import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Classe {@code LecteurDescription} permettant de construire un {@link Monde}
 * ainsi que ses {@link ConditionDeFin} à partir d'une description textuelle.
 * <p>
 * Cette classe lit un flux via un {@link Reader} et instancie dynamiquement les éléments du monde
 * (pièces, portes, joueurs, etc.) en fonction de mots-clés définis.
 * </p>
 */
public class LecteurDescriptionFactoryGlobal implements Lecteur{

    /**
     * Le monde construit à partir de la description.
     */
    private Monde monde;

    /**
     * La liste des conditions de fin associées au monde.
     */
    private Collection<ConditionDeFin> conditions = new ArrayList<>();
    
    /**
     * Construit un {@code LecteurDescription} à partir d'un flux texte.
     * 
     * @param reader le flux contenant la description du monde.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si un nom d'entité est utilisé plusieurs fois.
     * @throws IOException si une erreur d'entrée/sortie survient.
     */
    public LecteurDescriptionFactoryGlobal(Reader reader) throws NomDEntiteDejaUtiliseDansLeMondeException, IOException{
        Scanner scanner= new Scanner(reader);
        ITIAventureFactory factory = null;
        switch(scanner.next()){
            case "ITIAventure":
                factory = new ITIAventureFactory();
                break;
            case "ITISpaceOpera":
                factory = new ITISpaceOperaFactory();
                break;
        }

        while (scanner.hasNextLine()){
            String premier_mot=scanner.next();
            switch (premier_mot){
                case "Monde":
                    this.monde = factory.creationMonde(scanner.next());

                    break;
                case "Piece":
                    factory.creationPiece(scanner.next(), this.monde);
                    break;
                case "PorteSerrure":
                    factory.creationPorteSerrure(scanner.next(), this.monde, (Piece)this.monde.getEntite(scanner.next()), (Piece)this.monde.getEntite(scanner.next()));
                    break;
                case "Porte":
                    factory.creationPorte(scanner.next(), this.monde, (Piece)this.monde.getEntite(scanner.next()), (Piece)this.monde.getEntite(scanner.next()));
                    break;
                case "Cle":
                    factory.creationCle(scanner.next(), this.monde, scanner.next());
                    break;
                case "JoueurHumain":
                    this.creerJoueurHumain(scanner.next(), this.monde, scanner.nextInt(), scanner.nextInt(), (Piece)this.monde.getEntite(scanner.next()));
                    break;
                case "ConditionDeFinVivantDansPiece":
                    this.creerConditionDeFinVivantDansPiece(scanner, this.monde);
                    break;
                default :
                    System.out.println(String.format("Mot cle inconnu : %s", premier_mot));
            }
        }
        scanner.close();
    }

    /**
     * Retourne le monde construit.
     *
     * @return le monde.
     */
    @Override
    public Monde getMonde(){
        return this.monde;
    }
    
    /**
     * Retourne les conditions de fin associées au monde.
     *
     * @return la collection de conditions de fin.
     */
    @Override
    public Collection<ConditionDeFin> getConditionsDeFin(){
        return this.conditions;
    }

    /**
     * Crée un joueur humain dans une pièce.
     *
     * @param nom le nom du joueur.
     * @param monde le monde auquel appartient le joueur.
     * @param pointsVie les points de vie du joueur.
     * @param pointsForce les points de force du joueur.
     * @param piece la pièce de départ du joueur.
     * @param objets objets éventuellement portés par le joueur.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé.
     */
    private void creerJoueurHumain(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        new JoueurHumain(nom, monde, pointsVie, pointsForce, piece, objets);
    }

    /**
     * Crée une condition de fin où un vivant doit se trouver dans une pièce.
     *
     * @param s le scanner utilisé pour lire les informations.
     * @param monde le monde associé.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé.
     */
    private void creerConditionDeFinVivantDansPiece(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String etat_str = s.next();
        EtatDuJeu etat;
        if (etat_str.equals("SUCCES")) {
            etat = EtatDuJeu.SUCCES;
        } else {
            etat = EtatDuJeu.ECHEC;
        }
        Vivant vivant = (Vivant) monde.getEntite(s.next().replaceAll("\"", ""));
        Piece piece = (Piece) monde.getEntite(s.next().replaceAll("\"", ""));
        this.conditions.add(new ConditionDeFinVivantDansPiece(etat, vivant, piece));
    }
}   
