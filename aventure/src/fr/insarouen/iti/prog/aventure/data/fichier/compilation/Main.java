package fr.insarouen.iti.prog.aventure.data.fichier.compilation;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.Analyseurs.AnalyseurSyntaxique;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ChaineDeCaracteres;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationMultiple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.EnumerationCDF;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Nombre;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.SuiteArguments;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.Analyseurs.ParseException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;

public class Main{
	
	private static boolean parametresOK(String[] args) {
        if (args.length != 1)
            return false;
        File f = new File(args[0]);
        return f.exists();
    }

    private static void traiterFichier(String nomFichierConfiguration) {
    	try (BufferedReader fluxTexte = new BufferedReader(new FileReader(nomFichierConfiguration))) {
            String ligne = fluxTexte.readLine();
            int numeroLigne = 1;

            while (ligne != null) {
            	if (!ligne.trim().isEmpty()) {
		        	try {
		        		System.out.println("\n=== Ligne " + numeroLigne + " ===");
		        		System.out.println("Texte : " + ligne);
		        		
		        		AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new StringReader(ligne));
		        		DeclarationMultiple declarations = analyseur.declarationMultiple();
		        		System.out.println("AST : " + declarations);

		        	} catch (ParseException e) {
		        		System.err.println("Erreur de syntaxe à la ligne " + numeroLigne);
		        		e.printStackTrace();
		        	} catch (Throwable t) {
		        		System.err.println("Erreur inconnue à la ligne " + numeroLigne);
		        		t.printStackTrace();
		        	}
            	}
            	ligne = fluxTexte.readLine();
				numeroLigne++;
			}
        } catch (FileNotFoundException e) {
        	System.err.println("Fichier non trouvé : " + nomFichierConfiguration);            
        } catch (IOException e) {  
        	System.err.println("Erreur d'entrée ou sortie : " + e.getMessage());                      
        }
    }

    private static void afficherAide() {
        System.out.println("Utilisation : java fr.insarouen.iti.prog.aventure.data.fichier.compilation.Main <fichier.cfg>");
    }

    public static void main(String[] args) throws ParseException {
		if (parametresOK(args))
			traiterFichier(args[0]);
		else
			afficherAide();
	}
}

