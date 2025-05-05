package fr.insarouen.iti.prog.aventure.data;

import java.io.ObjectOutputStream;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import java.io.IOException;
import java.util.Collection;

/**
 * Classe {@code EnregistreurSerialisation} permettant d'enregistrer un {@link Monde}
 * et ses {@link ConditionDeFin} via la sérialisation.
 * <p>
 * Utilise un {@link ObjectOutputStream} pour écrire les objets dans un flux de sortie.
 * </p>
 */
public class EnregistreurSerialisation implements Enregistreur{

    /**
     * Le flux de sortie utilisé pour sérialiser les objets.
     */
    private ObjectOutputStream oos;

    /**
     * Construit un nouvel enregistreur utilisant un flux de sortie spécifié.
     *
     * @param oos le flux de sortie utilisé pour sérialiser les objets.
     * @throws IOException si une erreur d'entrée/sortie survient lors de l'initialisation.
     */
    public EnregistreurSerialisation(ObjectOutputStream oos) throws IOException{
        this.oos=oos;
    }

    /**
     * Sérialise et enregistre un {@link Monde} ainsi qu'une collection de {@link ConditionDeFin}.
     *
     * @param monde le monde à enregistrer.
     * @param conditionsDeFin la collection des conditions de fin associées au monde.
     * @throws IOException si une erreur d'entrée/sortie survient lors de la sérialisation.
     */
    @Override
    public void enregistrer(Monde monde, Collection<ConditionDeFin> conditionsDeFin ) throws IOException{
        this.oos.writeObject(monde);
        this.oos.writeObject(conditionsDeFin);
    }
}