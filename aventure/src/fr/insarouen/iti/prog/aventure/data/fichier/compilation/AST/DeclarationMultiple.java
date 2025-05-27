package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe représentant un ensemble d'affection constituant un fichier de configuration
 */
public class DeclarationMultiple implements Visitable{

    //La liste des déclarations (l'ordre est important)
	private Collection<DeclarationSimple> lesDS = null;
	
    /**
     * Constructeur de DeclarationMultiple
     * @param desDS une collection de déclarations simples
     */
	public DeclarationMultiple(Collection<DeclarationSimple> desDS){
		this.lesDS = new ArrayList<>();
		this.lesDS.addAll(desDS);
	}
	
    /**
     * Permet de récupérer la liste des déclarations simples
     * @return la collection des déclarations simples
     */
    public Collection<DeclarationSimple> getDeclarationsSimples() {
        return this.lesDS;
    }

    @Override
    public String toString(){
        String retour = "Declarations multiples :";
        for (DeclarationSimple ds : this.lesDS.toArray(new DeclarationSimple[0])){
            retour = String.format("%s \n %s",retour,ds);
        }
        return retour;
    } 
}
