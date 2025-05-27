package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

//Stocker types, arguments, identifiants

import java.util.ArrayList;
import java.util.Collection;
import java.util.List; 
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.SuiteArguments;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;
/**
 * Classe représentant une fonction
 * Est un neoud de l'AST produit par l'analyseur syntaxique
 */
public class Fonction extends ArgumentSimple{
	
	//l'identifiant de la fonction (liste finie)
	private String lIdentifiant;

	//les arguments passés à la fonction
	private SuiteArguments lesArguments; 
	
	/**
	 * Constructeur de Fonction
	 * @param lIdentifiant de la fonction qui sera représentée
	 * @param lesArguments la suite d'arguments en paramètre de la fonction
	 */
	public Fonction(String lIdentifiant, SuiteArguments lesArguments){
		super(lIdentifiant);
		this.lIdentifiant = lIdentifiant; 
		this.lesArguments = lesArguments; 
	}
	
	/**
	 * Permet de récupérer l'identifiant de la fonction
	 * @return le nom de la fonction
	 */
	public String getIdentifiant(){
        return lIdentifiant;
    }
    
	/**
	 * Permet de récupérer les arguments passés à la fonction
	 * @return la suite d'arguments donné à la fonction
	 */
    public SuiteArguments getArguments(){
    	return lesArguments;
    }

	@Override
	public String toString() {
		return String.format("\n \t \t Classe : %s \n \t \t Arguments : %s",this.lIdentifiant, this.lesArguments);
	}
}
