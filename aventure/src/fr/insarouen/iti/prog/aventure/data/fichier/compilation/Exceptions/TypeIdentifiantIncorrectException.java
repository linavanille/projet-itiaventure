package fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions;

/**
 * Lève une exception lorsque durant l'execution le type d'un Identifiant n'est pas celui attendu
 */
public class TypeIdentifiantIncorrectException extends CompilationException{
    
    /**
     * Constructeur de l'exception
     * @param s le message 
     */
    public TypeIdentifiantIncorrectException(String s) {
        super(s);
    }


}