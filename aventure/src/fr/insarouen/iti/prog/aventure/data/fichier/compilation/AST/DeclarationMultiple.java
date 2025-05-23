package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;

public class DeclarationMultiple implements Visitable{
	private List<DeclarationSimple> lesDS = null;
	
	public DeclarationMultiple(List<DeclarationSimple> desDS){
		this.lesDS = new ArrayList<>();
		this.lesDS.addAll(desDS);
	}
	
    public Collection<DeclarationSimple> getDeclarationsSimples() {
        return this.lesDS;
    }

	@Override
    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
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
