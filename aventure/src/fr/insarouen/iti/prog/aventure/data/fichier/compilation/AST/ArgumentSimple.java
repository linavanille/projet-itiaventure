package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

//Identifiant, Nombre, CDC, Etat Enum héritent de ArguementSimble (abstract) 
// Méthode get valeurs 

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;

public abstract class ArgumentSimple implements Visitable{
	protected String valeur; 
	
	public ArgumentSimple(String valeur){
		this.valeur = valeur;
	}
	
	public String getValeur(){
		return valeur;
	}

    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    } 

	@Override
	public String toString(){
		return String.format("%s : %s",this.getClass().getSimpleName(),this.valeur);
	}

	@Override
	public boolean equals(Object o){
		if (!(o instanceof ArgumentSimple)){
			return false;
		}
		ArgumentSimple temp = (ArgumentSimple)o;
		return temp.getValeur().equals(this.getValeur());
	}

	@Override
	public int hashCode(){
		return 31 * this.getValeur().hashCode();
	}
}
