package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

//Identifiant, Nombre, CDC, Etat Enum héritent de ArguementSimble (abstract) 
// Méthode get valeurs 

public abstract class ArgumentSimple {
	private String valeur; 
	
	public ArgumentSimple(String valeur){
		this.valeur = valeur;
	}
	
	public String getValeur(){
		return valeur;
	}
}
