package fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions;

/**
 * Lève une Exception lorsque durant l'execution un deuxième est créé => impossible
 */
public class MondeUniqueException extends CompilationException{
    
    /**
     * Constructeur de l'exception
     * @param s le message 
     */
    public MondeUniqueException(String s) {
        super(s);
    }


}