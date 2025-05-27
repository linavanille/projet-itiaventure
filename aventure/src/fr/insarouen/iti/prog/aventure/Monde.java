package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.Executable;

import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.Collections;
import java.io.Serializable;

/**
 * Représente un monde dans lequel des entités peuvent être ajoutées et manipulées.
 * Un monde est caractérisé par un nom et contient une collection d'entités.
 */
public class Monde implements Serializable {

    /**
     * Le nom du monde.
     */
    protected String nom;

    /**
     * La collection d'entités qui appartient à ce monde.
     */
    protected Map<String,Entite> tabEntite = new HashMap<>();
    
    /**
     * Constructeur de la classe Monde.
     * @param nom Le nom du monde.
     */
    public Monde(String nom){
        this.nom = nom; 
    }

    /**
     * Récupère le nom du monde.
     * @return Le nom du monde.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Récupère une entité par son nom.
     * @param nom Le nom de l'entité à récupérer.
     * @return L'entité correspondant au nom, ou null si aucune entité n'a ce nom.
     */
    public Entite  getEntite(String nom){
       return this.tabEntite.get(nom);
    }

    /**
     * Récupère toutes les entités du monde.
     * @return Une collection des entités dans le monde, qui ne peut pas être modifiée.
     */
    public Collection<Entite> getEntites(){
        return Collections.unmodifiableCollection(this.tabEntite.values());
    }

    /**
     * Récupère toutes les entités exécutables dans le monde.
     * @return Une collection des entités qui sont des instances de {@link Executable}.
     */
    public Collection<Executable> getExecutables(){
        return this.getEntites().stream().filter(entite -> entite instanceof Executable).map(entite -> (Executable) entite).toList();
    }
    
    /**
     * Ajoute une entité au monde.
     * Vérifie si l'entité est déjà présente dans ce monde ou dans un autre monde.
     * @param entite L'entité à ajouter.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Si l'entité a déjà le même nom dans ce monde.
     * @throws EntiteDejaDansUnAutreMondeException Si l'entité appartient déjà à un autre monde.
     */
    public void ajouter (Entite entite) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException{
        if (this.getEntite(entite.getNom())!=null){
            throw new NomDEntiteDejaUtiliseDansLeMondeException(String.format("L'entité %s est deja utilisée dans le monde %s ",entite.getNom(),this.tabEntite));
        }

        if (entite.getMonde() != this){
            throw new EntiteDejaDansUnAutreMondeException(String.format("L'entite %s existe deja dans un autre monde",entite.getNom()));
        }
        this.tabEntite.put(entite.getNom(),entite);
	}

    /**
     * Génère un nom unique pour une nouvelle entité, basé sur un préfixe.
     * Le nom est garanti d'être unique dans le monde.
     * @param prefixe Le préfixe pour le nom de l'entité.
     * @return Un nom unique pour une entité.
     */
    public String genererNom(String prefixe){
        int nbre_alea=(int)(100*Math.random());
        String new_name=prefixe+nbre_alea;
        while (this.getEntite(new_name)!=null){
            int nv_nbr = (int)(10000*Math.random());
            if (nv_nbr != nbre_alea){
            nbre_alea=(int)(100*Math.random());
                nbre_alea = nv_nbr;
            }
            new_name=prefixe+nbre_alea;
        }
        return new_name;
    }

    /**
     * Retourne une représentation textuelle du monde et de ses entités.
     * @return Une chaîne de caractères représentant le monde.
     */
    public String toString(){
        
        StringBuilder resultat = new StringBuilder("Nom du Monde : ");
        resultat.append(this.getNom());
        resultat.append("\nEntites :");
        Entite [] listeEntites = this.tabEntite.values().toArray(new Entite[0]);
		for (int i=0;i<this.tabEntite.size();i++){
			resultat.append(listeEntites[i].getNom());
            resultat.append("\n");
		}
        return resultat.toString();
    }
}