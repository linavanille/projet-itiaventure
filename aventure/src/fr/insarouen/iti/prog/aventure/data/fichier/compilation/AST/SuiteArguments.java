package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visiteur;

/**
 * Classe représentant une liste d'arguments (l'ordre est important)
 * Est un noeud de l'AST produit par l'analyseur syntaxique
 */
public class SuiteArguments{

    //Liste des ArgumentSimple contenus dans la suite d'arguments
    private List<ArgumentSimple> lesArgs;

    /**
     * Cosntructeur de SuiteArguments
     * @param desArgs liste d'arguments simples
     */
    public SuiteArguments(List<ArgumentSimple> desArgs){
        this.lesArgs = new ArrayList<>();
        this.lesArgs.addAll(desArgs);
    }

    /**
     * Permet de récupérer la collection d'arguments
     * @return La liste des arguments simples
     */
    public Collection<ArgumentSimple> getArguments(){
        return this.lesArgs;
    }

    @Override
    public String toString(){
        String retour = "";
        for (ArgumentSimple ds : this.lesArgs.toArray(new ArgumentSimple[0])){
            retour = String.format("%s | %s",retour,ds);
        }
        return retour;
    }
}
