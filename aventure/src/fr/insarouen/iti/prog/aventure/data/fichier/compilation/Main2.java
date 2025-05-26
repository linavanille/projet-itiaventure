/*package fr;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.LecteurCompilation;

import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.Analyseurs.ParseException;
import java.io.IOException;

public class Main2 {
    private static boolean parametresOK(String[] args) {
        if (args.length != 1)
            return false;
        File f = new File(args[0]);
        return f.exists();
    }

    private static void traiterFichier(String nomFichierConfiguration) {
    	try (FileReader fluxTexte = new FileReader(nomFichierConfiguration)) {
            LecteurCompilation l = new LecteurCompilation(fluxTexte);
            System.out.println(l.getConditionsDeFin());
            
        } catch (FileNotFoundException e) {
        	System.err.println("Fichier non trouvé : " + nomFichierConfiguration);            
        } catch (IOException e) {  
        	System.err.println("Erreur d'entrée ou sortie : " + e.getMessage());                      
        }
    }

    public static void main(String[] args) throws ParseException {
		if (parametresOK(args))
			traiterFichier(args[0]);
		else
			afficherAide();
	}

    private static void afficherAide() {
        System.out.println("Utilisation : java fr.insarouen.iti.prog.aventure.data.fichier.compilation.Main <fichier.cfg>");
    }
}*/