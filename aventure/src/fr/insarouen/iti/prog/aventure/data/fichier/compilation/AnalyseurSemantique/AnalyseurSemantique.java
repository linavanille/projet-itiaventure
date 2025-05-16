package fr.insarouen.iti.prog.aventure.data.fichier.compilation.analyseurSemantique;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.Visiteur;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.TableDesSymboles;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationMultiple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.Variable;

public class AnalyseurSemantique implements Visiteur {
    private TableDesSymboles tds;

    public AnalyseurSemantique(TableDesSymboles tds){
        this.tds = tds;
    }

    public void visiter(DeclarationMultiple dm) throws Throwable {
        for (DeclarationSimple ds : dm.getDeclarationsSimples()) {
            dm.accepter(this)
        }
    }

    public void visiter(DeclarationSimple ds) throws Throwable {
        ds.getIndentifiant().accepter(this);
        ds.getFonction().accepter(this);
    }

    public void visiter(Identifiant id) throws Throwable {
        Variable v = new Variable(id.getNom());
        if (this.tds.containsKey(v)) {
            //erreur
        }
    }

    public void visiter(Fonction fct) throws Throwable {

    }
}  