/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.IdentifiantInconnuException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe associant un type à chaque variable déclarée (suite à au moins une affectation).
 * @author delestre
 */
public class TableDesSymboles {
    private Map<Identifiant,Object> lesObjets;

    /**
     * Constructeur d'une table de symboles vide
     */
    public TableDesSymboles() {
        lesObjets = new HashMap<>();
    }
    
    /**
     * Permet d'obtenir le type d'une variable.
     * @param id l'identifiant de la variable
     * @return le type
     * @throws IdentifiantInconnuException
     */
    public Object getObject(Identifiant id) throws IdentifiantInconnuException {
        if (lesObjets.containsKey(id)){
            return lesObjets.get(id);
        }
        throw new IdentifiantInconnuException(id);
    }   
    
    /**
     * Permet de fixer ou modifier le type d'une variable.
     * @param id l'identifiant de la variable
     * @param t le type
     */
    public void setObject(Identifiant id, Object s) {
        lesObjets.put(id,s);
    }

    /**
     * Permet d'obtenir une représentation humaine de la table de symboles.
     * @return la représentation humaine
     */
    @Override
    public String toString() {
        return this.lesObjets.entrySet().toString();
    }
}
