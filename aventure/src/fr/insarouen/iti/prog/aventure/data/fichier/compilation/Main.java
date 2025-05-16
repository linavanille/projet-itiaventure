package fr.insarouen.iti.prog.aventure.data.fichier.compilation;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.Analyseurs.AnalyseurSyntaxique;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.*;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.automate.*;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main{
	
	private static boolean parametresOK(String[] args) {
        if (args.length != 1)
            return false;
        File f = new File(args[0]);
        return f.exists();
    }
    
    private static void traiterFichier(String nomFichierConfiguration) {
    	try{
    		BufferedReader fluxTexte = new BufferedReader(new FileReader(nomFichierConfiguration));
            String ligne = fluxTexte.readLine();
            int numeroLigne = 1;
            
            while (ligne != null) {
            	if (!ligne.trim().isEmpty()) {
		        	try {
		        		AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new java.io.StringReader(ligne));
						DeclarationMultiple declarations = analyseur.declarationMultiple();
						System.out.println(declarations);	
            		} catch (ParseException e) {
            			System.err.println(ligne);
            		}
            
            	}
            	ligne = fluxTexte.readLine();
				numeroLigne++;
			}
        } catch (FileNotFoundException e) {
        	System.err.println(nomFichierConfiguration);            
        } catch (IOException e) {  
        	System.err.println(e.getMessage());                      
        } catch (Throwable e) {
        	System.err.println(e);            
        }
    }
    
    private static void afficherAide() {
        System.out.println("Utilisation : Fichier de configuration");
    }
    
    public static void main(String[] args) throws ParseException {
		if (parametresOK(args))
			traiterFichier(args[0]);
		else
			afficherAide();
	}

}
