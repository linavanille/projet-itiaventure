package fr.insarouen.iti.prog.aventure.data;

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
import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visitable;
import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visiteur;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.interpreteur.Interpreteur;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.TableDesSymboles;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;

import java.io.Reader;
import java.io.IOException;

import java.util.Collection;

import fr.insarouen.iti.prog.aventure.data.Lecteur;

public class LecteurCompilation implements Lecteur{

    private Reader reader;
    private Monde monde;    
    private Collection<ConditionDeFin> conditions;

    @Override
    public Monde getMonde() {
        return this.monde;
    }

    @Override
    public Collection<ConditionDeFin> getConditionsDeFin() {
        return this.conditions;
    }

    public LecteurCompilation(Reader fluxTexte) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(fluxTexte);
            DeclarationMultiple declarations = analyseur.declarationMultiple();
            TableDesSymboles tds = new TableDesSymboles();
            Interpreteur interpreteur = new Interpreteur(tds);
            declarations.accepter(interpreteur);
            this.monde = interpreteur.getMonde();
            this.conditions = interpreteur.getConditionsDeFin();

        } 
        catch (ParseException e) {
            e.printStackTrace();
        } 
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

