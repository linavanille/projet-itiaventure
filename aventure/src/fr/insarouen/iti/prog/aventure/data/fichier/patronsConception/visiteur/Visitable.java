
package fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur;


public interface Visitable {
    /**
     * Permet d'acceper un visiteur.
     * @param v le visiteur
     * @throws Throwable
     */
    default public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    }
    
}
