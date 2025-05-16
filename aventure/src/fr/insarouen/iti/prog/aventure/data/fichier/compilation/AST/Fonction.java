package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

//Stocker types, arguments, identifiants

import java.util.ArrayList;
import java.util.Collection;
import java.util.List; 
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.SuiteArguments;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;

public class Fonction{
	private String lIdentifiant; 
	private SuiteArguments lesArguments; 
	
	public Fonction(String lIdentifiant, SuiteArguments lesArguments){
		this.lIdentifiant = lIdentifiant; 
		this.lesArguments = lesArguments; 
	}
	
	public String getIdentifiant(){
        return lIdentifiant;
    }
    
    public SuiteArguments getArguments(){
    	return lesArguments;
    }

	@Override
    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    } 
}
