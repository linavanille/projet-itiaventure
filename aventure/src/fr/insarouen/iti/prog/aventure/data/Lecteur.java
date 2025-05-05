package fr.insarouen.iti.prog.aventure.data;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import java.util.Collection;

/**
 * Interface {@code Lecteur} permettant de lire un {@link Monde}
 * et ses {@link ConditionDeFin} associées.
 * <p>
 * Un lecteur récupère un monde et ses conditions de fin à partir d'une source de données.
 * </p>
 */
public interface Lecteur {

    /**
     * Retourne le {@link Monde} lu depuis la source.
     *
     * @return le monde récupéré.
     */
    Monde getMonde();

    /**
     * Retourne la collection de {@link ConditionDeFin} associée au monde lu.
     *
     * @return la collection des conditions de fin.
     */
    Collection<ConditionDeFin> getConditionsDeFin();
}