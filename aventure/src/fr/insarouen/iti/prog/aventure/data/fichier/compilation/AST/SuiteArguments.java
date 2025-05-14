package fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST;

import java.util.ArrayList;
import java.util.List;
import fr.insarouen.iti.prog.aventire.data.fichier.compilation.automate.ArgumentSimple;

public class SuiteArguments {
    private List<ArgumentSimple> lesArgs;

    public SuiteArguments(List<ArgumentSimple> desArgs){
        this.lesArgs = new ArrayList<>();
        this.lesArgs.addAll(desArgs);
    }

    public getArguments(){
        return this.lesArgs;
    }
}