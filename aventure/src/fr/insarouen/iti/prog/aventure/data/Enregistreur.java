package fr.insarouen.iti.prog.aventure.data;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import java.util.Collection;

/**
 * Interface représentant un enregistreur de monde et de ses conditions de fin.
 * <p>
 * Cette interface définit une méthode permettant d'enregistrer l'état
 * d'un {@link Monde} ainsi que ses {@link ConditionDeFin} associées.
 * </p>
 */
public interface Enregistreur {

    /**
     * Enregistre l'état actuel du monde ainsi que ses conditions de fin.
     *
     * @param monde le monde à enregistrer.
     * @param conditions la collection des conditions de fin associées au monde.
     * @throws Throwable si une erreur survient lors de l'enregistrement.
     */
    void enregistrer(Monde monde, Collection<ConditionDeFin> conditions) throws Throwable;
}