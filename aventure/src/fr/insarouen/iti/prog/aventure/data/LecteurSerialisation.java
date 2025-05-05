package fr.insarouen.iti.prog.aventure.data;

import java.io.ObjectInputStream;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.Collection;

/**
 * Classe {@code LecteurSerialisation} permettant de lire un {@link Monde} 
 * et ses {@link ConditionDeFin} associés à partir d'un flux de données sérialisées.
 * <p>
 * Cette classe permet de restaurer un monde et ses conditions de fin à partir 
 * d'un flux d'entrée sérialisé en utilisant un {@link ObjectInputStream}.
 * </p>
 */
public class LecteurSerialisation implements Lecteur {

    /**
     * Le flux d'entrée utilisé pour lire les objets sérialisés.
     */
    private ObjectInputStream ois;

    /**
     * Le monde récupéré à partir du flux.
     */
    private Monde monde;

    /**
     * La collection des conditions de fin associées au monde.
     */
    private Collection<ConditionDeFin> conditions;

    /**
     * Constructeur de la classe {@code LecteurSerialisation}.
     * <p>
     * Ce constructeur lit un {@link Monde} et ses conditions de fin à partir du flux 
     * d'entrée sérialisé donné.
     * </p>
     * 
     * @param ois le flux d'entrée à partir duquel les objets sont lus.
     * @throws IOException si une erreur d'entrée/sortie survient.
     * @throws ClassNotFoundException si la classe d'un objet sérialisé n'est pas trouvée.
     */
    @SuppressWarnings("unchecked")
    public LecteurSerialisation(ObjectInputStream ois)throws IOException, ClassNotFoundException{
        this.monde = (Monde)ois.readObject();
        this.conditions = (Collection<ConditionDeFin>)ois.readObject();

    }

    /**
     * Retourne le monde récupéré à partir du flux de données.
     * 
     * @return le monde restauré à partir du flux.
     */
    public Monde getMonde() {
        return this.monde;
    }

    /**
     * Retourne les conditions de fin associées au monde récupéré.
     * 
     * @return la collection des conditions de fin.
     */
    public Collection<ConditionDeFin> getConditionsDeFin(){
        return this.conditions;
    }
}