package fr.insarouen.iti.prog.aventure.data.fichier.compilation.interpreteur;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.patronsConception.visiteur.Visiteur;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.TableDesSymboles;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationMultiple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.EnumerationCDF;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ChaineDeCaracteres;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Nombre;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.TableDesSymboles;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.TypeDeParametreIncorrectException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.IdentifiantInconnuException;

import java.util.Collection;
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
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFinVivantDansPiece;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFinVivantPossedeObjets;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFinVivantDansPieceEtPossedeObjets;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;


public class Interpreteur implements Visiteur {
    private TableDesSymboles tds;
    private Monde leMonde=null;
    private ArrayList<ConditionDeFin> lesCDF;

    public Interpreteur(TableDesSymboles tds){
        this.tds = tds;
        this.lesCDF = new ArrayList<>();
    }

    public void visiter(DeclarationMultiple dm) throws Throwable {
        for (DeclarationSimple ds : dm.getDeclarationsSimples()) {
            ds.accepter(this);
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

    public Monde getMonde(){
        return this.leMonde;
    }

    public Object creerObject(Fonction fct) throws Throwable {
        
        String idfct = fct.getIdentifiant();
        ArgumentSimple[] args = fct.getArguments().getArguments().toArray(new ArgumentSimple[0]);
        switch (idfct) {
            case "monde" : 
                if (this.leMonde == null) {
                    this.leMonde = this.creationMonde(args);
                    return this.leMonde;
                }
                else {
                    throw new Exception();
                }
            case "humain" : return this.creationJoueurHumain(args);
            case "piece" : return this.creationPiece(args);
            case "porte" : return this.creationPorte(args);
            case "cle" : return this.creationCle(args);
            case "pied_de_biche" : return creationPDB(args);
            case "cdf_vivant_dans_piece" : 
                ConditionDeFin temp = creationCDFVDP(args);
                this.lesCDF.add(temp);
                return temp;
            case "cdf_vivant_possede" : 
                ConditionDeFin temp = creationCDFVDP(args);
                this.lesCDF.add(temp);
                return creationCDFVP(args);
            case "cdf_conjonction" : 
                ConditionDeFin temp = creationCDFVDP(args);
                this.lesCDF.add(temp);
                return creationCDFC(args);
            case "monstre" : return creationMonstre(args);
            default : return null;
        }
    }

    public Monde creationMonde(ArgumentSimple[] args) throws Throwable {
        if (args.length != 1 & !(args[0] instanceof ChaineDeCaracteres)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        return new Monde(args[0].getValeur());
    }

    public JoueurHumain creationJoueurHumain(ArgumentSimple[] args) throws Throwable {
        if (args.length < 5
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaracteres)
        | !(args[2] instanceof Nombre)
        | !(args[3] instanceof Nombre)
        | !(args[4] instanceof Identifiant)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[0]) instanceof Monde)
        | !(this.tds.getObject((Identifiant)args[4]) instanceof Piece)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length > 5) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 5, args.length-1));
        }
            
        return new JoueurHumain(args[1].getValeur(),(Monde)this.tds.getObject((Identifiant)args[0]),((Nombre)args[2]).getValeurEntier(),((Nombre)args[3]).getValeurEntier(),(Piece)this.tds.getObject((Identifiant)args[4]),stockObjet);
    } 

    public Piece creationPiece(ArgumentSimple[] args) throws Throwable {
        if (args.length < 2
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaracteres)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[0]) instanceof Monde)) {
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length > 2) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 3, args.length-1));
        }
            
        return new Piece(args[1].getValeur(),(Monde)this.tds.getObject((Identifiant)args[0]),stockObjet);
    }

    public Porte creationPorte(ArgumentSimple[] args) throws Throwable {
        if (args.length < 4
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaracteres)
        | !(args[2] instanceof Identifiant)
        | !(args[3] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("mauvais paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[0]) instanceof Monde)
        | !(this.tds.getObject((Identifiant)args[2]) instanceof Piece)
        | !(this.tds.getObject((Identifiant)args[3]) instanceof Piece)) {//même piece Exception ??
        throw new Exception();
        }
        if (args.length == 5){
            if (!(args[4] instanceof Fonction)
            | (!((Fonction)args[4]).getIdentifiant().equals("serrure"))) {
                throw new Exception();
            }
            return new Porte(args[1].getValeur(), (Monde)this.tds.getObject((Identifiant)args[0]), (Serrure)this.creationSerrure(((Fonction)args[4]).getArguments().getArguments(),(Monde)this.tds.getObject((Identifiant)args[0])), (Piece)this.tds.getObject((Identifiant)args[2]), (Piece)this.tds.getObject((Identifiant)args[3]));
        }
        else {
            return new Porte(args[1].getValeur(), (Monde)this.tds.getObject((Identifiant)args[0]), (Piece)this.tds.getObject((Identifiant)args[2]), (Piece)this.tds.getObject((Identifiant)args[3]));
        }

    }

    public Cle creationCle(ArgumentSimple[] args) throws Throwable {
        if (args.length != 2
        |!(args[0] instanceof Identifiant)
        | !(args[1] instanceof Identifiant)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[0]) instanceof Porte)
        | !(this.tds.getObject((Identifiant)args[1]) instanceof Piece)){
            throw new Exception();
        }
        Porte p = (Porte)this.tds.getObject((Identifiant)args[0]);
        Piece piece = (Piece)this.tds.getObject((Identifiant)args[1]);
        Cle temp = p.getSerrure().creerCle();
        piece.deposer(temp);
        return temp;
    }

    public PiedDeBiche creationPDB(ArgumentSimple[] args) throws Throwable {
        if (args.length != 2
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaracteres)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[0]) instanceof Monde)) {
            throw new Exception();
        }
        return new PiedDeBiche(args[1].getValeur(), (Monde)this.tds.getObject((Identifiant)args[0]));
    }

    public ConditionDeFinVivantDansPiece creationCDFVDP(ArgumentSimple[] args) throws Throwable {
        if (args.length != 3
        | !(args[0] instanceof EnumerationCDF)
        | !(args[1] instanceof Identifiant)
        | !(args[2] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[1]) instanceof Vivant)
        | !(this.tds.getObject((Identifiant)args[2]) instanceof Piece)){
            throw new Exception();
        }
        return new ConditionDeFinVivantDansPiece(((EnumerationCDF)args[0]).getValeurEnum(), (Vivant)this.tds.getObject((Identifiant)args[1]), (Piece)this.tds.getObject((Identifiant)args[2]));
    }

    public ConditionDeFinVivantPossedeObjets creationCDFVP(ArgumentSimple[] args) throws Throwable {
        if (args.length < 3
        | !(args[0] instanceof EnumerationCDF)
        | !(args[1] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[1]) instanceof Vivant)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length >= 3) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 3, args.length-1));
        }

        return new ConditionDeFinVivantPossedeObjets(((EnumerationCDF)args[0]).getValeurEnum(), (Vivant)this.tds.getObject((Identifiant)args[1]), stockObjet);
    }

    public ConditionDeFinVivantDansPieceEtPossedeObjets creationCDFC(ArgumentSimple[] args) throws Throwable {
        if (args.length < 3
        | !(args[0] instanceof EnumerationCDF)
        | !(args[1] instanceof Identifiant)
        | !(args[2] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[1]) instanceof Vivant)
        || !(this.tds.getObject((Identifiant)args[2]) instanceof Piece)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length >= 4) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 4, args.length-1));
        }
        return new ConditionDeFinVivantDansPieceEtPossedeObjets(((EnumerationCDF)args[0]).getValeurEnum(), (Vivant)this.tds.getObject((Identifiant)args[1]), (Piece)this.tds.getObject((Identifiant)args[2]),stockObjet);
    }

    public Serrure creationSerrure(Collection<ArgumentSimple> lesArgs, Monde monde) throws Throwable {
        ArgumentSimple[] args = lesArgs.toArray(new ArgumentSimple[0]);
        if (args.length < 1
        | args.length > 2
        | !(args[0] instanceof Identifiant)){
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (args.length == 2){
            if (!(args[1] instanceof ChaineDeCaracteres)){
                throw new TypeDeParametreIncorrectException("mauvais paramètre");
            }
            return new Serrure(args[1].getValeur(),monde);
        }
        return new Serrure(monde);

    }

    public Monstre creationMonstre(ArgumentSimple[] args) throws Throwable {
        if (args.length < 5
        | !(args[0] instanceof Identifiant)
        | !(args[1] instanceof ChaineDeCaracteres)
        | !(args[2] instanceof Nombre)
        | !(args[3] instanceof Nombre)
        | !(args[4] instanceof Identifiant)) {
            throw new TypeDeParametreIncorrectException("Mauvais Paramètre");
        }
        if (!(this.tds.getObject((Identifiant)args[0]) instanceof Monde)
        || !(this.tds.getObject((Identifiant)args[4]) instanceof Piece)){
            throw new Exception();
        }
        Objet[] stockObjet = new Objet[0];
        if (args.length > 5) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 5, args.length-1));
        }
            
        return new Monstre(args[1].getValeur(),(Monde)this.tds.getObject((Identifiant)args[0]),((Nombre)args[2]).getValeurEntier(),((Nombre)args[3]).getValeurEntier(),(Piece)this.tds.getObject((Identifiant)args[4]),stockObjet);
    }

    public Objet[] listeObjet(ArgumentSimple[] args) throws Throwable {
        Objet[] retour = new Objet[args.length-1];
        for (int i=0;i<args.length;i++) {
            if (!(args[i] instanceof Identifiant)) {
                throw new TypeDeParametreIncorrectException("Mauvais type");
            }
            retour[i] = (Objet)this.tds.getObject((Identifiant)args[i]);            
        }
        return retour;
    }
} 