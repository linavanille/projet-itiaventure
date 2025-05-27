package fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;

/**
 * Exception levée lorsque le mauvais type de paramètre est passé en argument d'une fonction
 */
public class TypeDeParametreIncorrectException extends CompilationException{
    private Identifiant idAyantProvoqueException;
    
    /**
     * Constructeur de l'exception 
     * @param s message
     */
    public TypeDeParametreIncorrectException(String s) {
        super(s);
    }


}