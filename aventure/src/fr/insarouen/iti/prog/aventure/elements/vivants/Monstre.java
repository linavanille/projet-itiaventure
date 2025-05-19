package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.Etat;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;

/**
 * La classe {@code Monstre} représente un vivant particulier capable de se déplacer automatiquement 
 * entre les pièces du monde en interagissant avec les portes et les objets.
 */
public class Monstre extends Vivant {

    /**
     * Construit un nouveau {@code Monstre} avec un nom, un monde, un nombre de points de vie, 
     * un nombre de points de force et une pièce de départ.
     *
     * @param nom le nom du monstre.
     * @param monde le monde auquel appartient le monstre.
     * @param pointsVie le nombre de points de vie initial du monstre.
     * @param pointsForce le nombre de points de force initial du monstre.
     * @param piece la pièce où le monstre est initialement situé.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException si un autre élément porte déjà ce nom dans le monde.
     */
    public Monstre(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde, pointsVie, pointsForce, piece, objets);
    }

    /**
     * Exécute les actions du monstre :
     * <ul>
     *   <li> Diminue ses points de vie de 1 s'il est encore en vie. </li>
     *   <li> Cherche une porte non verrouillée, l'ouvre si nécessaire, et la franchit. </li>
     *   <li> Ramasse tous les objets de la nouvelle pièce. </li>
     *   <li> Dépose tous les objets qu'il possède. </li>
     * </ul>
     *
     * @throws ITIAventureException si une erreur survient lors de l'activation d'une porte ou de la manipulation d'objets.
     */
    public void executer() throws ITIAventureException{
        if (this.getPointVie() >0){
            this.setPointVie(this.getPointVie()-1);
        }

        List<Porte> portes = this.getPiece().getPortes().stream().filter(p->p.getEtat()!=Etat.VERROUILLE).collect(Collectors.toList());

        if (!(portes.isEmpty())) {
            Porte porte = portes.get(0);
            if (porte.getEtat() == Etat.FERME) {
                porte.activer();
            }
            this.franchir(porte);

            List<Objet> objetsmonstre = new ArrayList<>(this.getObjets());
            List<Objet> objetspiece = new ArrayList<>(this.getPiece().getObjets());
            
            while (!(objetspiece.isEmpty())){
                this.prendre(objetspiece.get(0));
                objetspiece.remove(0); 
            }

            while (!(objetsmonstre.isEmpty())){
                this.deposer(objetsmonstre.get(0));
                objetsmonstre.remove(0);
            }
		}
    }
}