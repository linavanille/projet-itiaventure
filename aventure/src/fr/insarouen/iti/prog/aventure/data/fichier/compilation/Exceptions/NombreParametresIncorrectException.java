package fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions;

/**
 * Lève une exception lorsque durant l'execution une fonction est appelée avec le mauvais nombre de paramètre
 */
public class NombreParametresIncorrectException extends Exception{
    
    /**
     * Constructeur de l'exception 
     * @param s le message
     */
    public NombreParametresIncorrectException(String s) {
        super(s);
    }


}