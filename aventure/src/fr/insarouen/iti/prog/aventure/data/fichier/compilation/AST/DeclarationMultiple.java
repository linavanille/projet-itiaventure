package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visitable;

public class DeclarationMultiple implements Visitable{
	private List<DeclarationSimple> lesDS;
	
	public DeclarationMultiple(List<DeclarationSimple> desDS){
		lesDS = new ArrayList<>();
		lesDS.addAll(desDS);
	}
	
    public Collection<DeclarationSimple> getDeclarationsSimples() {
        return lesDS.values();
    }

	@Override
    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    }  
}
