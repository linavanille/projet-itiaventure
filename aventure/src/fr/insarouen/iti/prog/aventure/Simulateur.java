package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.aventure.elements.Executable;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Représente un simulateur qui gère l'exécution d'un jeu.
 * Un simulateur coordonne l'exécution des entités dans le monde et vérifie les conditions de fin du jeu.
 */
public class Simulateur{

    /**
     * Les conditions de fin du jeu qui déterminent quand le jeu doit se terminer.
     */
    private Collection <ConditionDeFin> conditionsDeFin;

    /**
     * Le monde dans lequel le jeu se déroule.
     */
    private Monde monde;

    /**
     * L'état actuel du jeu, qui peut être en cours ou terminé.
     */
    private EtatDuJeu etatdujeu = EtatDuJeu.ENCOURS;

    /**
     * Scanner pour lire les entrées de l'utilisateur.
     */
    Scanner stdin = new Scanner(System.in);

    /**
     * Constructeur du simulateur.
     * @param monde Le monde dans lequel le simulateur va se dérouler.
     * @param conditionsDeFin La collection des conditions de fin du jeu.
     */
    public Simulateur(Monde monde, Collection<ConditionDeFin> conditionsDeFin){
        this.conditionsDeFin = conditionsDeFin;
        this.monde = monde;
    }

    /**
     * Exécute un tour de jeu en traitant les actions des entités et en vérifiant les conditions de fin.
     * @return L'état du jeu après l'exécution du tour.
     */
    public EtatDuJeu executerUnTour()  {
        // Gestion des actions des joueurs humains
        for (Entite entite : monde.getEntites()) {
            if (entite instanceof JoueurHumain) {
                JoueurHumain joueur = (JoueurHumain)entite;
                System.out.println(String.format("%s\nVeuillez saisir un ordre", joueur.toString()));
                String ordre = this.stdin.nextLine();
                joueur.setOrdre(ordre);
            }
        }

        // Exécution des actions des entités exécutables
        for (Executable exec : monde.getExecutables()){
            try{
                exec.executer();
            }
            catch (ITIAventureException ex){
                System.out.println(ex.toString());
            }
        }

        // Vérification des conditions de fin du jeu
        if (this.conditionsDeFin != null) {
            for (ConditionDeFin cdf : this.conditionsDeFin){
                this.etatdujeu = cdf.verifierCondition();
                if (this.etatdujeu != EtatDuJeu.ENCOURS) {
                    System.out.println(this.etatdujeu);
                    return this.etatdujeu;
                }
            }
        }
        return this.etatdujeu;
    }

    /**
     * Exécute le jeu jusqu'à ce qu'une condition de fin soit atteinte.
     */
    public void executerJusquALaFin()  {
        while (etatdujeu.equals(EtatDuJeu.ENCOURS)){
            this.etatdujeu = this.executerUnTour();
            System.out.println(this.etatdujeu);
        }
    }

    /**
     * Récupère les conditions de fin du jeu.
     * @return Une collection non modifiable des conditions de fin.
     */
    private Collection<ConditionDeFin> getConditionsDeFin(){
        return Collections.unmodifiableCollection(this.conditionsDeFin);
    }

    public String toString() {
        return String.format("Simulateur(monde: %s,  conditionsDeFin: [%s])", this.monde,this.getConditionsDeFin());
    }
}

