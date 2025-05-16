/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe associant un type à chaque variable déclarée (suite à au moins une affectation).
 * @author delestre
 */
public class TableDesSymboles {
    private Map<Variable,Object> lesSymboles;

    /**
     * Constructeur d'une table de symboles vide
     */
    public TableDesSymboles() {
        lesSymboles = new HashMap<>();
    }
    
    /**
     * Permet d'obtenir le type d'une variable.
     * @param id l'identifiant de la variable
     * @return le type
     * @throws IdentifiantInconnuException
     */
    public Object getObject(Variable id) {
        if (lesSymboles.containsKey(id))
            return lesSymboles.get(id);
    }   
    
    /**
     * Permet de fixer ou modifier le type d'une variable.
     * @param id l'identifiant de la variable
     * @param t le type
     */
    public void setObject(Variable id, Object o) {
        lesSymboles.put(id,o);
    }

    /**
     * Permet d'obtenir une représentation humaine de la table de symboles.
     * @return la représentation humaine
     */
    @Override
    public String toString() {
        return "TableDesSymboles{" + "lesSymboles=" + lesSymboles + '}';
    }
}
