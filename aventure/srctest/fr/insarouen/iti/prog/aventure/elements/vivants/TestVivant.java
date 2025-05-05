package fr.insarouen.iti.prog.aventure.elements.vivants;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;


class ObjetConcret extends Objet {
    public ObjetConcret(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        super(nom, monde);
    }

    public boolean estDeplacable() {
        return false;
    }
}

class PDBConcret extends PiedDeBiche {
    public PDBConcret(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        super(nom, monde);
    }
}

public class TestVivant {

    private static Vivant vivant1;
    private static Monde monde;
    private static Piece chambre,cuisine;
    private static ObjetConcret objet;
    private static PDBConcret objetDeplacable;
    private static PDBConcret autrePdb;
    private static Porte porte;


    @BeforeClass
    public static void avantTest() throws VivantAbsentDeLaPieceException,NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        monde = new Monde("Terre");
        chambre = new Piece("Chambre", monde);
        cuisine = new Piece("Cuisine",monde);
        objet = new ObjetConcret("Patate", monde);
        objetDeplacable = new PDBConcret("pdb", monde);
        autrePdb = new PDBConcret("autre", monde);
        chambre.deposer(autrePdb);
        vivant1 = new Vivant("Yvan", monde, 10, 5, chambre, autrePdb);
        chambre.deposer(objetDeplacable);
        porte= new Porte("porte",monde,chambre,cuisine);

    }

    @Test(expected = ObjetAbsentDeLaPieceException.class)
    public void testPrendre_NonDansLaPiece() throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
        vivant1.prendre(objet);

    }

    
    @Test(expected = ObjetNonDeplacableException.class)
    public void testPrendre_NonDeplacable() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        chambre.deposer(objet);
        vivant1.prendre(objet);
    }
    

    @Test
    public void testPrendreEtDeposer_SansPb() throws ObjetAbsentDeLaPieceException, ObjetNonPossedeParLeVivantException, ObjetNonDeplacableException {
        vivant1.prendre(objetDeplacable);
        assertThat(vivant1.possede(objetDeplacable), equalTo(true));

        vivant1.deposer(objetDeplacable);
        assertThat(vivant1.possede(objetDeplacable), equalTo(false));
    }
    
    @Test
    public void test_getObjets()throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        vivant1.prendre(objetDeplacable);
        assertThat(vivant1.possede(objetDeplacable), equalTo(true));
        assertThat(vivant1.possede(autrePdb), equalTo(true));
        assertThat(vivant1.getObjets().toArray(new Objet[0]), arrayContainingInAnyOrder(objetDeplacable, autrePdb));
    }
    
    @Test(expected = ObjetNonPossedeParLeVivantException.class)
    public void testDeposer_SiObjetNonPossede() throws ObjetAbsentDeLaPieceException, ObjetNonPossedeParLeVivantException {
        vivant1.deposer("pdb"); 
    
    }

    @Test
    public void test_getObjet()throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException, ObjetNonPossedeParLeVivantException{
        assertThat(vivant1.getObjet("pdb"), equalTo(null));
        vivant1.prendre("pdb");
        assertThat(vivant1.getObjet("pdb"), equalTo(objetDeplacable));
        vivant1.deposer(objetDeplacable);

    }

    @Test(expected=PorteFermeException.class)
    public void test_franchirPorteFerme() throws VivantAbsentDeLaPieceException,PorteFermeException,PorteInexistanteDansLaPieceException{
        vivant1.franchir(porte);
    }

    @Test(expected=PorteInexistanteDansLaPieceException.class)
    public void test_franchirPorteInexsitante() throws Exception {
        Piece jardin = new Piece("Jardin",monde);
        Porte porte2 = new Porte("porte2",monde,jardin,cuisine);
        vivant1.franchir(porte2);
    }

    @Test
    public void test_franchirSansPb() throws Exception{
        porte.activer();
        vivant1.franchir(porte);
        assertThat(vivant1.getPiece(),equalTo(cuisine));
        vivant1.franchir("porte");
        assertThat(vivant1.getPiece(),equalTo(chambre));
        porte.activer();
    }
}