package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class DeclarationMultiple{
	private List<DeclarationSimple> lesDS;
	
	public DeclarationMultiple(List<DeclarationSimple> desDS){
		lesDS = new ArrayList<>();
		lesDS.addAll(desDS);
	}
	
    public Collection<DeclarationSimple> getDeclarationsSimples() {
        return lesDS;
    }    
}
