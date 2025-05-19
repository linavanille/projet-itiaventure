package fr.insarouen.iti.prog.aventure.data.fichier.compilation.execeptions;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;

/**
 * Exception levée lorsque durant l'interpétation d'une expression cette 
 * dernière utilise une variable qui est inconnue. 
 * @author delestre
 */
public class TypeDeParametreIncorrectException extends Exception{
    private Identifiant idAyantProvoqueException;
    
    /**
     * Constructeur de l'exception à partifir de l'identifiant de la 
     * variable inconnue.
     * @param id l'identifiant
     */
    public TypeDeParametreIncorrectException(String s) {
        super(s);
    }


}