package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;
/**Classe de la feuille de l'AST représentant un argument simple*/
public abstract class ArgumentSimple{

	//La valeur de l'argument
	protected String valeur; 
	
	/** 
	 * Constructeur de ArgumentSimple
	 * @param valeur le valeur récupérée par l'analyseur syntaxique
	*/
	public ArgumentSimple(String valeur){
		this.valeur = valeur;
	}
	
	/**
	 * Getteur pour le champ valeur
	 * @return la valeur stockée dans l'ArgumentSimple
	 */
	public String getValeur(){
		return valeur;
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
