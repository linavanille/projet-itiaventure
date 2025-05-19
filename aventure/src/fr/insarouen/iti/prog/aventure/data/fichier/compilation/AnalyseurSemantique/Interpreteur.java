package fr.insarouen.iti.prog.aventure.data.fichier.compilation.Interpreteur;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.Visiteur;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.TableDesSymboles;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationMultiple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.TableDesSymboles;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exception.TypeDeParametreIncorrectException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exception.IdentifiantInconnuException;

import java.util.Stack;
import java.util.Arrays;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.aventure.elements.vivants.Monstre;


public class Interpreteur implements Visiteur {
    private TableDesSymboles tds;

    public Interpreteur(TableDesSymboles tds){
        this.tds = tds;
    }

    public void visiter(DeclarationMultiple dm) throws Throwable {
        for (DeclarationSimple ds : dm.getDeclarationsSimples()) {
            dm.accepter(this);
        }
    }

    public void visiter(DeclarationSimple ds) throws Throwable {
        Identifiant id = ds.getIdentifiant();
        Object o = this.creerObject(ds.getFonction());
        this.tds.setObject(id,o);
    }

    public void visiter(Fonction fct) throws Throwable {
        fct.getArguments().accepter(this);
        this.creerObject(fct);
    }

    public Object creerObject(Fonction fct) throws Throwable {
        
        String idfct = fct.getIdentifiant();
        ArgumentSimple[] args = fct.getArguments().toArray(new ArgumentSimple[0]);
        switch (idfct) {
            case "monde" : return this.creationMonde(args);
            case "humain" : return this.creationJoueurHumain(args);
            case "piece" : return this.creationPiece(args);
            case "porte" : return this.creationPorte(args);
            case "cle" : return this.creationCle(args);
            case "pied_de_biche" : return creationPDB(args);
            case "cdf_vivant_dans_piece" : return creationCDFVDP(args);
            case "cdf_vivant_possede" : return creationCDFVP(args);
            case "cdf_conjonction" : return creationCDFC(args);
            case "monstre" : return creationMonstre(args);
        }
    }

    public Monde creationMonde(ArgumentSimple[] args) throws Thowable {
        if (args.length != 1 & !(args[0] instanceof ChaineDeCaractere)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        return new Monde(args[0].getValeur());
    }

    public JoueurHumain creationJoueurHumain(ArgumentSimple[] args) throws Thowable {
        if (args.length < 5
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaractere)
        | !(args[2] instanceof Nombre)
        | !(args[3] instanceof Nombre)
        | !(args[4] instanceof Identifiant)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[0]) instanceof Monde)
        | !(this.tds.getObject(args[4]) instanceof Piece)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length > 5) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 5, args.length-1));
        }
            
        return new JoueurHumain(args[1],this.tds.getObject(args[0]),args[2],args[3],this.tds.getObject(args[4]),stockObjet);
    } 

    public Piece creationPiece(ArgumentSimple[] args) throws Thowable {
        if (args.length < 2
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaractere)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[0]) instanceof Monde)) {
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length > 2) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 3, args.length-1));
        }
            
        return new Piece(args[1],this.tds.getObject(args[0]),stockObjet);
    }

    public Porte creationPorte(ArgumentSimple[] args) throws Thowable {
        if (args.length < 4
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaractere)
        | !(args[2] instanceof Identifiant)
        | !(args[3] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("mauvais paramètre");
        }
        if (!(this.tds.getObject(args[0]) instanceof Monde)
        | !(this.tds.getObject(args[2]) instanceof Piece)
        | !(this.tds.getObject(args[3]) instanceof Piece)) {//même piece Exception ??
        throw new Exception();
        }
        if (args.length == 5){
            if (!(args[4] instanceof Fonction)
            | args[4].getIdentifiant() != "serrure") {
                throw new Exception();
            }
            return new Porte(args[1].getValeur(), args[0], this.creationSerrure(args[4].getArguments(),(Monde)this.tds.getObject(args[1])), this.tds.getObject(args[2]), this.tds.getObject(args[3]));
        }
        else {
            return new Porte(args[1].getValeur(), args[0], this.tds.getObject(args[2]), this.tds.getObject(args[3]));
        }

    }

    public Cle creationCle(ArgumentSimple[] args) throws Thowable {
        if (args.length != 3
        |!(args[0] instanceof Identifiant)
        | !(args[1] instanceof Identifiant)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[0]) instanceof Porte)
        | !(this.tds.getObject(args[1]) instanceof Piece)){
            throw new Exception();
        }
        Porte p = (Porte)this.tds.getObject(args[0]);
        Piece piece = (Piece)this.tds.getObject(args[1]);
        piece.deposer(p.getSerrure().creerCle());
    }

    public PiedDeBiche creationPDB(ArgumentSimple[] args) throws Thowable {
        if (args.length != 2
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaractere)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[0]) instanceof Monde)) {
            throw new Exception();
        }
        return new PiedDeBiche(args[1].getValeur(), this.tds.getObject(args[0]));
    }

    public ConditionDeFin creationCDFVDP(ArgumentSimple[] args) throws Thowable {
        if (args.length != 3
        | !(args[0] instanceof Enumeration)
        | !(args[1] instanceof Identifiant)
        | !(args[2] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[1]) instanceof Vivant)
        | !(this.tds.getObject(args[2]) instanceof Piece)){
            throw new Exception();
        }
        return new ConditionDeFinVivantDansPiece(args[0].getValeur(), this.tds.getObject(args[1]), this.tds.getObject(args[2]));
    }

    public ConditionDeFin creationCDFVP(ArgumentSimple[] args) throws Thowable {
        if (args.length < 3
        | !(args[0] instanceof Enumeration)
        | !(args[1] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[1]) instanceof Vivant)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length >= 3) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 3, args.length-1));
        }

        return new ConditionDeFinVivantDansPiece(args[0].getValeur(), this.tds.getObject(args[1]), stockObjet);
    }

    public ConditionDeFin creationCDFC(ArgumentSimple[] args) throws Thowable {
        if (args.length < 3
        | !(args[0] instanceof Enumeration)
        | !(args[1] instanceof Identifiant)
        | !(args[2] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[1]) instanceof Vivant)
        || !(this.tds.getObject(args[2]) instanceof Piece)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length >= 4) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 4, args.length-1));
        }
        return new ConditionDeFinVivantDansPiece(args[0].getValeur(), this.tds.getObject(args[1]), this.tds.getObject(args[2]),stockObjet);
    }

    public Serrure creationSerrure(ArgumentSimple[] args, Monde monde) throws Thowable {
        if (args.length < 1
        | args.length > 2
        | !(args[0] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (args.length == 2){
            if (!(args[1] instanceof ChaineDeCaractere)){
                throw new TypeDeParametreIncorrectException("mauvais paramètre");
            }
            return new Serrure(args[1].getValeur(),monde);
        }
        return new Serrure(monde);

    }

    public Monstre creationMonstre(ArgumentSimple[] args) throws Thowable {
        if (args.length < 5
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaractere)
        | !(args[2] instanceof Nombre)
        | !(args[3] instanceof Nombre)
        | !(args[4] instanceof Identifiant)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject(args[0]) instanceof Monde)
        || !(this.tds.getObject(args[4]) instanceof Piece)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length > 5) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 5, args.length-1));
        }
            
        return new Monstre(args[1],this.tds.getObject(args[0]),args[2],args[3],this.tds.getObject(args[4]),stockObjet);
    }

    public Objet[] listeObjet(ArgumentSimple[] args) throws Throwable {
        Objet[] retour = new Objet[args.length-1];
        for (int i=0;i<args.length;i++) {
            if (!(args[i] instanceof Identifiant)) {
                throw new TypeDeParametreIncorrectException("Mauvais type");
            }
            retour[i] = this.tds.getObject(args[i].getValeur());            
        }
        return retour;
    }
} 