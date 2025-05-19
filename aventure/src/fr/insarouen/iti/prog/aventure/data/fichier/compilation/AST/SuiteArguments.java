package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;

public class SuiteArguments implements Visitable{
    private List<ArgumentSimple> lesArgs;

    public SuiteArguments(List<ArgumentSimple> desArgs){
        this.lesArgs = new ArrayList<>();
        this.lesArgs.addAll(desArgs);
    }

    public Collection<ArgumentSimple> getArguments(){
        return this.lesArgs;
    }

    public void accepter(Visiteur v) throws Throwable {
        v.visiter(this);
    } 
}
