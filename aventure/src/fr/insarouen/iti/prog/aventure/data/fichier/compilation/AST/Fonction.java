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

public class Fonction extends ArgumentSimple{
	private String lIdentifiant; 
	private SuiteArguments lesArguments; 
	
	public Fonction(String lIdentifiant, SuiteArguments lesArguments){
		super(lIdentifiant);
		this.lIdentifiant = lIdentifiant; 
		this.lesArguments = lesArguments; 
	}
	
	public String getIdentifiant(){
        return lIdentifiant;
    }
    
    public SuiteArguments getArguments(){
    	return lesArguments;
    }

    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    } 

	@Override
	public String toString() {
		return String.format("\n \t \t Classe : %s \n \t \t Arguments : %s",this.lIdentifiant, this.lesArguments);
	}
}
