package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;

public class DeclarationMultiple implements Visitable{
	private List<DeclarationSimple> lesDS;
	
	public DeclarationMultiple(List<DeclarationSimple> desDS){
		lesDS = new ArrayList<>();
		lesDS.addAll(desDS);
	}
	
    public Collection<DeclarationSimple> getDeclarationsSimples() {
        return lesDS;
    }

	@Override
    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    }  
}
