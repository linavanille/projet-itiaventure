package fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;

/**
 * Exception levée lorsque durant l'interpétation d'une expression cette 
 * dernière utilise une variable qui est inconnue. 
 * @author delestre
 */
public class IdentifiantInconnuException extends CompilationException {
    private Identifiant idAyantProvoqueException;
    
    /**
     * Constructeur de l'exception à partifir de l'identifiant de la 
     * variable inconnue.
     * @param id l'identifiant
     */
    public IdentifiantInconnuException(Identifiant id) {
        super("");
        idAyantProvoqueException = id;
    }
    
    /**
     * Permt d'obtenir l'identifiant qui a provoqué l'exception.
     * @return l'identifiant
     */
    public Identifiant identifiantAyantProvoqueException() {
        return idAyantProvoqueException;
    }
    
    /**
     * Permet d'obtenir une représentation humaine de l'exception.
     * @return la représentation humaine
     */
    public String toString() {
        return "l'indentifiant "+identifiantAyantProvoqueException()+" est inconnu";
    }
}