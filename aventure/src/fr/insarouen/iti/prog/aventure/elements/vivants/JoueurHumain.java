package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.ActivationException;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.Etat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Collection;

/**
 * Représente un joueur humain dans le monde de l'aventure.
 * 
 * Le joueur humain peut exécuter des commandes textuelles pour interagir avec le monde,
 * comme prendre ou poser des objets, franchir des portes, et ouvrir des portes avec ou sans objet.
 * 
 * Cette classe utilise la réflexion pour lier dynamiquement les commandes saisies par l'utilisateur
 * à des méthodes internes.
 */
public class JoueurHumain extends Vivant {

    /** l'ordre donné au vivant */
    private String ordre;

    /**
     * Crée un joueur humain avec un nom, un monde d'appartenance, des points de vie, de force, une pièce de départ et éventuellement des objets.
     * 
     * @param nom le nom du joueur.
     * @param monde le monde auquel appartient le joueur.
     * @param pointsVie les points de vie du joueur.
     * @param pointsForce les points de force du joueur.
     * @param piece la pièce initiale du joueur.
     * @param objets les objets initialement possédés par le joueur.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si le nom est déjà utilisé dans le monde.
     */
    public JoueurHumain(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
	super(nom, monde, pointsVie, pointsForce, piece, objets);
    }

    /**
     * Retourne l'ordre actuel du joueur.
     * 
     * @return l'ordre sous forme de chaîne.
     */
    public String getOrdre() {
	    return this.ordre;
    }

    /**
     * Définit l'ordre que doit exécuter le joueur.
     * 
     * @param ordre l'ordre sous forme de chaîne.
     */
    public void setOrdre(String ordre) {
	    this.ordre = ordre;
    }
    
    /**
     * Permet au joueur de prendre un objet dans la pièce courante.
     * 
     * @param nomObjet le nom de l'objet à prendre.
     * @throws ObjetNonDeplacableException si l'objet ne peut pas être déplacé.
     * @throws ObjetAbsentDeLaPieceException si l'objet n'est pas présent dans la pièce.
     */
    private void commandePrendre(String nomObjet) throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
	    this.prendre(nomObjet);
    }

    /**
     * Permet au joueur de poser un objet qu'il possède.
     * 
     * @param nomObjet le nom de l'objet à poser.
     * @throws ObjetNonPossedeParLeVivantException si le joueur ne possède pas cet objet.
     */
    private void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException {
	    this.deposer(nomObjet);
    }

    /**
     * Permet au joueur de franchir une porte de la pièce courante.
     * 
     * @param nomPorte le nom de la porte à franchir.
     * @throws PorteFermeException si la porte est fermée.
     * @throws PorteInexistanteDansLaPieceException si la porte n'existe pas dans la pièce.
     */
    private void commandeFranchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
	    this.franchir(nomPorte);
    }

    /**
     * Permet au joueur d'ouvrir une porte sans objet.
     * 
     * @param nomPorte le nom de la porte à ouvrir.
     * @throws ActivationException si la porte ne peut être activée.
     * @throws PorteInexistanteDansLaPieceException si la porte n'existe pas dans la pièce.
     */
    private void commandeOuvrirPorte(String nomPorte) throws ActivationException, PorteInexistanteDansLaPieceException {
        if (!this.getPiece().aLaPorte(nomPorte))
            throw new PorteInexistanteDansLaPieceException(String.format("%s ne possède pas %s", this.getPiece().getNom(), nomPorte));
        this.getPiece().getPorte(nomPorte).activer();
    }

    /**
     * Permet au joueur d'ouvrir une porte en utilisant un objet.
     * 
     * @param nomPorte le nom de la porte à ouvrir.
     * @param nomObjet le nom de l'objet utilisé pour ouvrir la porte.
     * @throws ActivationException si la porte ne peut être activée.
     * @throws PorteInexistanteDansLaPieceException si la porte n'existe pas dans la pièce.
     * @throws ObjetNonPossedeParLeVivantException si le joueur ne possède pas l'objet.
     */
    private void commandeOuvrirPorte(String nomPorte, String nomObjet) throws ActivationException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException {
        if (!this.possede(nomObjet))
            throw new ObjetNonPossedeParLeVivantException(String.format("%s ne possède pas %s", this.getNom(), nomObjet));
        if (!this.getPiece().aLaPorte(nomPorte))
            throw new PorteInexistanteDansLaPieceException(String.format("%s ne possède pas %s", this.getPiece().getNom(), nomPorte));
	    this.getPiece().getPorte(nomPorte).activerAvec(this.getObjet(nomObjet));
    }

    /**
     * Récupère dynamiquement une méthode correspondant à une commande donnée et ses arguments.
     * 
     * @param commande la commande à exécuter.
     * @param args la liste des arguments de la commande.
     * @return la méthode correspondant à la commande.
     * @throws NoSuchMethodException si la méthode n'existe pas.
     */
    private Method getMethod(String commande, List<String> args) throws NoSuchMethodException{
        Class<?>[] argTypes = new Class<?>[args.size()];
        Arrays.fill(argTypes, String.class);
        return this.getClass().getDeclaredMethod(String.format("commande%s", commande), argTypes);
    }

    /**
     * Exécute l'ordre donné par le joueur.
     * L'ordre est analysé pour extraire la commande et ses arguments,
     * puis exécuté via réflexion.
     * 
     * @throws ITIAventureException si une erreur liée à l'aventure se produit.
     */
    @Override
    public void executer()  throws ITIAventureException  {
        Scanner stdin = new Scanner(this.ordre);

        String commande = stdin.next();
        List<String> args = new ArrayList<String>();
        while(stdin.hasNext()){
            args.add(stdin.next());
        }

        try{
            Method methode = this.getMethod(commande, args);
            methode.invoke(this, args.toArray());
        } catch(InvocationTargetException e1) {
            throw (ITIAventureException)e1.getTargetException();
        } catch(Exception e) {
            throw new CommandeImpossiblePourLeVivantException(String.format("La commande %s ne peut pas être utilisée", commande));
        } finally {
            stdin.close();
        }
    }

    /**
     * Retourne une représentation textuelle du joueur humain.
     * Affiche son nom, sa pièce actuelle, les objets présents dans la pièce,
     * les objets possédés, et les portes de la pièce avec leur état.
     * 
     * @return une chaîne représentant le joueur humain.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Le joueur ");
        sb.append(this.getNom());
        sb.append(" se trouve dans ");
        sb.append(this.getPiece().getNom());
        sb.append(String.format("\nObjets disponibles dans la pièce: %s ",this.getPiece().getObjets()));
        sb.append(String.format("\nObjets que le joueur %s possède:%s ",this.getNom(),this.getObjets()));
        Collection<Objet> objetsPiece = this.getPiece().getObjets();
        if (objetsPiece.size() > 0) {
            sb.append(" contenant ");
            for (Objet objet : objetsPiece) {
                sb.append(objet.getNom());
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        Collection<Porte> portes = this.getPiece().getPortes();
        if (portes.size() > 0) {
            sb.append("Portes/Trappes de la pièce:");
            for (Porte porte : portes) {
                sb.append(porte.getNom());
                Etat etat = porte.getEtat();
                sb.append("[");
                sb.append(etat);
                sb.append("]");
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
