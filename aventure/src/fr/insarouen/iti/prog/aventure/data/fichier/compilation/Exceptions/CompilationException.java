package fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions;

/**
 * Classe d'exception pour la partie compilation
 */
public abstract class CompilationException extends Exception{
    
    /**
     * Constructeur de l'exception
     * @param s le message 
     */
    public CompilationException(String s) {
        super(s);
    }


}