package fr.insarouen.iti.prog.aventure.data.fichier.compilation.interpreteur;

import fr.insarouen.iti.prog.aventure.data.fichier.patronsConception.visiteur.Visiteur;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles.TableDesSymboles;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationMultiple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.DeclarationSimple;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Identifiant;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Fonction;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.EnumerationCDF;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ChaineDeCaracteres;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.Nombre;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.AST.ArgumentSimple;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.TypeDeParametreIncorrectException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.IdentifiantInconnuException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.NombreParametresIncorrectException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.MondeUniqueException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.CompilationException;
import fr.insarouen.iti.prog.aventure.data.fichier.compilation.exceptions.TypeIdentifiantIncorrectException;

import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

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
import fr.insarouen.iti.prog.aventure.EtatDuJeu;

/**
 * Classe interprétant l'AST produit par l'analyseur syntaxique
 * elle fait aussi office d'analyseur sémantique
 */
public class Interpreteur implements Visiteur {

    //Table des symboles de l'interpréteur : Identifiant (unique) -> Objet associé
    private TableDesSymboles tds;

    //Le monde qui est construit à partir de la lecture du fichier
    private Monde leMonde=null;

    //L'ensemble des Conditions de fin
    private Collection<ConditionDeFin> lesCDF;


    /**
     * Constructeur de l'Interpréteur
     * @param tds la table des symboles associé à cet interpréteur
     */
    public Interpreteur(TableDesSymboles tds){
        this.tds = tds;
        this.lesCDF = new ArrayList<>();
    }

    /**
     * Permet de visiter une DeclarationMultiple
     * @param dm la déclaration multiple à visiter
     * @throws Throwable pour propager n'importe quelle Exception
     */
    public void visiter(DeclarationMultiple dm) throws Throwable {
        for (DeclarationSimple ds : dm.getDeclarationsSimples()) {
            ds.accepter(this);
        }
    }

    /**
     * Permet de visiter une DeclarationSimple
     * @param ds la déclaration simple à visiter
     * @throws Throwable pour propager n'importe quelle Exception
     */
    public void visiter(DeclarationSimple ds) throws Throwable {
        Identifiant id = ds.getIdentifiant();
        Object o = this.creerObject(ds.getFonction());
        this.tds.setObject(id,o);
        if (o instanceof ConditionDeFin){
            this.lesCDF.add((ConditionDeFin)o);
        }
    }

    /**
     * Permet de visiter une Fonction
     * @param fct la focntion à visiter
     * @throws Throwable pour propager n'importe quelle Exception
     */
    public void visiter(Fonction fct) throws Throwable {
        
    }

    /**
     * Permet de récupérer le monde qui est en construction
     * @return le Monde construit
     */
    public Monde getMonde(){
        return this.leMonde;
    }

    /**
     * Permet de récupérer l'ensembles des Conditions de Fin du jeu
     * @return l'ensemble des CDF
     */
    public Collection<ConditionDeFin> getConditionsDeFin(){
        return this.lesCDF;
    }

    private static void testParam(ArgumentSimple arg, String classe) throws CompilationException {
        boolean estInstanceDe = false;
        switch (classe) {
            case "identifiant" : estInstanceDe = arg instanceof Identifiant; break;
            case "chaine de caractères" : estInstanceDe = arg instanceof ChaineDeCaracteres; break;
            case "nombre" : estInstanceDe = arg instanceof Nombre; break;
            case "enumeration" : estInstanceDe = arg instanceof EnumerationCDF; break;
        }
        if (!estInstanceDe){
            throw new TypeDeParametreIncorrectException(String.format("Le format du paramètre (%s) de correspond pas au format attendu (%s)",arg.getClass().getSimpleName(),classe));
        }
    }

    private void testIdentifiant(ArgumentSimple arg, String classe) throws CompilationException {
        boolean estInstanceDe = false;
        Interpreteur.testParam(arg,"identifiant");
        Identifiant id = (Identifiant)arg;
        switch (classe) {
            case "monde" : estInstanceDe = this.tds.getObject(id) instanceof Monde; break;
            case "vivant" : estInstanceDe = this.tds.getObject(id) instanceof Vivant; break;
            case "porte" : estInstanceDe = this.tds.getObject(id) instanceof Porte; break;
            case "piece" : estInstanceDe = this.tds.getObject(id) instanceof Piece; break;
            case "objet" : estInstanceDe = this.tds.getObject(id) instanceof Objet; break;
        }
        if (!estInstanceDe){
            throw new TypeIdentifiantIncorrectException(String.format("Le type de l'objet associé à cet identifiant (%s) n'est le type attendu (%s)",arg.getClass().getSimpleName(),classe));
        }
    }

    /**
     * Permet de créer les objets correspondant aux différents appels de fonction possible dans le fichier de configuration
     * @param fct la fonction qui décrit l'objet à créer
     * @return un object
     * @throws Throwable pour propager les Exceptions
     */
    private Object creerObject(Fonction fct) throws Throwable {
        
        String idfct = fct.getIdentifiant();
        ArgumentSimple[] args = fct.getArguments().getArguments().toArray(new ArgumentSimple[0]);
        switch (idfct) {
            case "monde" : 
                if (this.leMonde == null) {
                    this.leMonde = this.creationMonde(args);
                    return this.leMonde;
                }
                else {
                    throw new MondeUniqueException("Il ne peut y avoir qu'un seul Monde");
                }
            case "humain" : return this.creationJoueurHumain(args);
            case "piece" : return this.creationPiece(args);
            case "porte" : return this.creationPorte(args);
            case "cle" : return this.creationCle(args);
            case "pied_de_biche" : return this.creationPDB(args);
            case "cdf_vivant_dans_piece" : return this.creationCDFVDP(args);
            case "cdf_vivant_possede" : return this.creationCDFVP(args);
            case "cdf_conjonction" : return this.creationCDFC(args);
            case "monstre" : return creationMonstre(args);
            default : return null;
        }
    }

    public Monde creationMonde(ArgumentSimple[] args) throws Throwable {
        if (args.length != 1){
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 1 - reçu : %s",args.length));
        }
        Interpreteur.testParam(args[0],"chaine de caractères");
        return new Monde(args[0].getValeur());
    }

    public JoueurHumain creationJoueurHumain(ArgumentSimple[] args) throws Throwable {
        if (args.length < 5) {
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 5 minimum - reçu : %s",args.length));
        }
        this.testIdentifiant(args[0],"monde");
        Interpreteur.testParam(args[1],"chaine de caractères");
        Interpreteur.testParam(args[2],"nombre");
        Interpreteur.testParam(args[3],"nombre");
        this.testIdentifiant(args[4],"piece");
        
        Objet[] stockObjet = new Objet[0];
        if (args.length > 5) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 5, args.length-1));
        }

        String nom = args[1].getValeur();
        Monde m = (Monde)this.tds.getObject((Identifiant)args[0]);
        int pv = ((Nombre)args[2]).getValeurEntier();
        int pf = ((Nombre)args[3]).getValeurEntier();
        Piece p = (Piece)this.tds.getObject((Identifiant)args[4]);
        return new JoueurHumain(nom,m,pv,pf,p,stockObjet);
    } 

    public Piece creationPiece(ArgumentSimple[] args) throws Throwable {
        if (args.length < 2){
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 2 minimum - reçu : %s",args.length));
        }
        this.testIdentifiant(args[0],"monde");
        Interpreteur.testParam(args[1],"chaine de caractères");

        Objet[] stockObjet = new Objet[0];
        if (args.length >= 3) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 2, args.length-1));
        }
        String nom = args[1].getValeur();
        Monde m = (Monde)this.tds.getObject((Identifiant)args[0]);
        return new Piece(nom,m,stockObjet);
    }

    public Porte creationPorte(ArgumentSimple[] args) throws Throwable {
        if (args.length < 4){
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 4 minimum - reçu : %s",args.length));
        }
        this.testIdentifiant(args[0],"monde");
        Interpreteur.testParam(args[1],"chaine de caractères");
        this.testIdentifiant(args[2],"piece");
        this.testIdentifiant(args[3],"piece");

        String nom = args[1].getValeur();
        Monde m = (Monde)this.tds.getObject((Identifiant)args[0]);
        Piece p1 = (Piece)this.tds.getObject((Identifiant)args[2]);
        Piece p2 = (Piece)this.tds.getObject((Identifiant)args[3]);

        if (args.length == 5){
            if (!(args[4] instanceof Fonction)
            | (!((Fonction)args[4]).getIdentifiant().equals("serrure"))) {
                throw new TypeIdentifiantIncorrectException("Le 5e paramètre doit être un type Serrure/Fonction serrure ou ne pas être renseigné");
            }
            Collection<ArgumentSimple> nomSerrure = ((Fonction)args[4]).getArguments().getArguments();
            Serrure ser = (Serrure)this.creationSerrure(nomSerrure);
            return new Porte(nom, m, ser, p1, p2);
        }
        else {
            return new Porte(nom, m, p1, p2);
        }

    }

    public Cle creationCle(ArgumentSimple[] args) throws Throwable {
        if (args.length != 2) {
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 2 - reçu : %s",args.length));
        }

        this.testIdentifiant(args[0],"porte");
        this.testIdentifiant(args[1],"piece");

        Porte p = (Porte)this.tds.getObject((Identifiant)args[0]);
        Piece piece = (Piece)this.tds.getObject((Identifiant)args[1]);
        Cle temp = p.getSerrure().creerCle();
        piece.deposer(temp);
        return temp;
    }

    public PiedDeBiche creationPDB(ArgumentSimple[] args) throws Throwable {
        if (args.length != 2) {
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 2 - reçu : %s",args.length));
        }
        this.testIdentifiant(args[0],"monde");
        Interpreteur.testParam(args[1],"chaine de caractères");
        
        Monde m = (Monde)this.tds.getObject((Identifiant)args[0]);
        String nom = args[1].getValeur();
        return new PiedDeBiche(nom,m);
    }

    public ConditionDeFinVivantDansPiece creationCDFVDP(ArgumentSimple[] args) throws Throwable {
        if (args.length != 3) {
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 3 minimum - reçu : %s",args.length));
        }

        Interpreteur.testParam(args[0],"enumeration");
        this.testIdentifiant(args[1],"vivant");
        this.testIdentifiant(args[2],"piece");
    
        EtatDuJeu etat = ((EnumerationCDF)args[0]).getValeurEnum();
        Vivant vivant = (Vivant)this.tds.getObject((Identifiant)args[1]);
        Piece piece = (Piece)this.tds.getObject((Identifiant)args[2]);
        return new ConditionDeFinVivantDansPiece(etat, vivant, piece);
    }

    public ConditionDeFinVivantPossedeObjets creationCDFVP(ArgumentSimple[] args) throws Throwable {
        if (args.length < 3){
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 2 minimum - reçu : %s",args.length));
        }

        Interpreteur.testParam(args[0],"enumeration");
        this.testIdentifiant(args[1],"vivant");

        Objet[] stockObjet = new Objet[0];
        if (args.length >= 3) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 2, args.length-1));
        }
        EtatDuJeu etat = ((EnumerationCDF)args[0]).getValeurEnum();
        Vivant vivant = (Vivant)this.tds.getObject((Identifiant)args[1]);
        return new ConditionDeFinVivantPossedeObjets(etat, vivant, stockObjet);
    }

    public ConditionDeFinVivantDansPieceEtPossedeObjets creationCDFC(ArgumentSimple[] args) throws Throwable {
        if (args.length < 4) {
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 4 minimum - reçu : %s",args.length));
        }

        Interpreteur.testParam(args[0],"enumeration");
        this.testIdentifiant(args[1],"vivant");
        this.testIdentifiant(args[2],"piece");

        Objet[] stockObjet = new Objet[0];
        if (args.length >= 4) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 3, args.length-1));
        }
        EtatDuJeu etat = ((EnumerationCDF)args[0]).getValeurEnum();
        Vivant vivant = (Vivant)this.tds.getObject((Identifiant)args[1]);
        Piece p = (Piece)this.tds.getObject((Identifiant)args[2]);
        return new ConditionDeFinVivantDansPieceEtPossedeObjets(etat, vivant, p,stockObjet);
    }

    public Serrure creationSerrure(Collection<ArgumentSimple> lesArgs) throws Throwable {
        ArgumentSimple[] args = lesArgs.toArray(new ArgumentSimple[0]);
        if (args.length < 1 | args.length > 2) {
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 1 ou 2 - reçu : %s",args.length));
        }

        this.testIdentifiant(args[0],"monde");
        
        Monde monde = (Monde)this.tds.getObject((Identifiant)args[0]);
        if (args.length == 2){
            Interpreteur.testParam(args[1],"chaine de caractères");
            String nom = args[1].getValeur();
            return new Serrure(nom,monde);
        }
        return new Serrure(monde);

    }

    public Monstre creationMonstre(ArgumentSimple[] args) throws Throwable {
        if (args.length < 5) {
            throw new NombreParametresIncorrectException(String.format("Nombre de paramètre incorrect, attendu 5 minimum - reçu : %s",args.length));
        }

        this.testIdentifiant(args[0],"monde");
        Interpreteur.testParam(args[1],"chaine de caractères");
        Interpreteur.testParam(args[2],"nombre");
        Interpreteur.testParam(args[3],"nombre");
        this.testIdentifiant(args[4],"piece");
        
        Objet[] stockObjet = new Objet[0];
        if (args.length > 5) {
            stockObjet = this.listeObjet(Arrays.copyOfRange(args, 5, args.length-1));
        }
        String nom = args[1].getValeur();
        Monde monde = (Monde)this.tds.getObject((Identifiant)args[0]);
        int pv = ((Nombre)args[2]).getValeurEntier();
        int pf = ((Nombre)args[3]).getValeurEntier();
        Piece p = (Piece)this.tds.getObject((Identifiant)args[4]);
        return new Monstre(nom,monde,pv,pf,p,stockObjet);
    }

    public Objet[] listeObjet(ArgumentSimple[] args) throws Throwable {
        Objet[] retour = new Objet[args.length];
        for (int i=0;i<args.length;i++) {

            this.testIdentifiant(args[i],"objet");

            retour[i] = (Objet)this.tds.getObject((Identifiant)args[i]);            
        }
        return retour;
    }
} 