package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

//Stocker types, arguments, identifiants

import java.util.ArrayList;
import java.util.Collection;
import java.util.List; 
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.SuiteArguments;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.automate.Identifiant;

public class Fonction{
	private String lIdentifiant; 
	private SuiteArguments lesArguments; 
	
	public Fonction(String lIdentifiant, SuiteArguments lesArguments){
		this.lIdentifant = lIdentifiant; 
		this.lesArguements = lesArguments; 
	}
	
	public String getIdentifiant(){
        return lIdentifiant;
    }
    
    public Arguments getArguments(){
    	return lesArguments;
    }


}
