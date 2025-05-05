package fr.insarouen.iti.prog.aventure.elements.vivants;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;


public class TestMonstre {

    private static Monde monde;
    private static Piece chambre,cuisine;
    private static ObjetConcret objet;
    private static PDBConcret objetDeplacable;
    private static PDBConcret autrePdb;
    private static Porte porte;
    private static Monstre vampire;
    private static Piece salon;
    private static Piece sdb;
    private static Serrure serrure;
    private static Porte porteVerrouillee;


    @BeforeClass
    public static void avantTest() throws VivantAbsentDeLaPieceException,NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        monde = new Monde("Terre");
        chambre = new Piece("Chambre", monde);
        cuisine = new Piece("Cuisine",monde);
        objet = new ObjetConcret("Patate", monde);
        objetDeplacable = new PDBConcret("pdb", monde);
        autrePdb = new PDBConcret("autre", monde);
        vampire = new Monstre("Georges", monde, 10, 5, cuisine, autrePdb);
        chambre.deposer(objetDeplacable);
        porte = new Porte("porte",monde,chambre,cuisine);

        salon = new Piece("Salon", monde);
        sdb = new Piece("SDB", monde);
        serrure = new Serrure(monde);
        porteVerrouillee = new Porte("PCS", monde, serrure, sdb, salon);


    }

    @Test
    public void testComportementMonstre() throws VivantAbsentDeLaPieceException, PorteFermeException, PorteInexistanteDansLaPieceException, ActivationImpossibleAvecObjetException, ObjetAbsentDeLaPieceException, ObjetNonPossedeParLeVivantException, ObjetNonDeplacableException{
        int pvavant = vampire.getPointVie();

        vampire.executer();

        //System.out.println("dans la chambre il y a "+ chambre.getObjets());
        //System.out.println("Georges a "+ vampire.getObjets());

        assertThat(vampire.getPointVie(), equalTo(pvavant-1));
        assertThat(vampire.getPiece(), equalTo(chambre));

        //test de l'échange 
        assertThat(chambre.contientObjet(autrePdb), equalTo(true));
        assertThat(vampire.possede(objetDeplacable), equalTo(true));

        assertThat(chambre.contientObjet(objetDeplacable), equalTo(false));
        assertThat(vampire.possede(autrePdb), equalTo(false));

    }
    
    @Test
    public void testNeChangePasDePieceSiPorteVerrouillee() throws VivantAbsentDeLaPieceException, PorteFermeException, PorteInexistanteDansLaPieceException, ActivationImpossibleAvecObjetException, ObjetAbsentDeLaPieceException, ObjetNonPossedeParLeVivantException, ObjetNonDeplacableException{
        
        vampire.setPiece(sdb);
        vampire.executer();
        assertThat(vampire.getPiece(), equalTo(sdb));

        vampire.setPiece(cuisine);
    }
    
}