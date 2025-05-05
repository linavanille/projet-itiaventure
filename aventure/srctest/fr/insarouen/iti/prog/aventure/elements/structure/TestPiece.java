package fr.insarouen.iti.prog.aventure.elements.structure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;

class ObjetConcret extends Objet {
        public ObjetConcret (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
            super(nom,monde);
        }
        public boolean estDeplacable(){
            return false;
        }
    }

class PDBConcret extends PiedDeBiche {
        public PDBConcret (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
            super(nom,monde);
        }
    }

public class TestPiece{
  
    private static Vivant vivant;
    private static Vivant ami;
    private static Monde monde;
    private static Piece chambre;
    private static ObjetConcret objet;
    private static PDBConcret objetdeplacable;



    @BeforeClass
    public static void avantTest() throws VivantAbsentDeLaPieceException,NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
        monde = new Monde("Terre"); 
        chambre = new Piece("Chambre", monde);
        objet = new ObjetConcret("Patate", monde);
        vivant = new Vivant("Yvan", monde, 10, 5, chambre, objet);
        objetdeplacable = new PDBConcret("pdb", monde);
        ami = new Vivant("Son pote", monde, 10, 5, chambre, objet);
    }

    @Test
    public void test_contientobjetTrue() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        chambre.deposer(objetdeplacable);
        assertThat(chambre.contientObjet(objetdeplacable), equalTo(true));
        assertThat(chambre.contientObjet("pdb"), equalTo(true));
        chambre.retirer(objetdeplacable);
    }

    @Test
    public void test_contientobjetFalse(){
        assertThat(chambre.contientObjet(objetdeplacable), equalTo(false));
        assertThat(chambre.contientObjet("pdb"), equalTo(false));  
    }
   
    @Test
    public void test_deposerRetirerSansPb() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        assertThat(chambre.contientObjet(objetdeplacable), equalTo(false));
        chambre.deposer(objetdeplacable);
        assertThat(chambre.contientObjet("pdb"), equalTo(true)); 
        chambre.retirer(objetdeplacable);
        assertThat(chambre.contientObjet("pdb"), equalTo(false)); 
    }

    @Test(expected = ObjetAbsentDeLaPieceException.class)
    public void test_retirerObjetAbsent() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        chambre.retirer(objetdeplacable);
    }

    @Test(expected = ObjetNonDeplacableException.class)
    public void test_retirerObjetNonDeplacable() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        chambre.deposer(objet);
        chambre.retirer(objet);
    }

    @Test
    public void test_getObjets()throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        chambre.deposer(objetdeplacable);
        assertThat(chambre.getObjets().toArray(new Objet[0]), arrayContainingInAnyOrder(objetdeplacable, objet));
        chambre.retirer(objetdeplacable);
    }

    @Test
    public void test_getVivants() throws VivantAbsentDeLaPieceException{
        chambre.entrer(vivant);
        assertThat(chambre.getVivants().toArray(new Vivant[0]), arrayContainingInAnyOrder(vivant, ami));
        chambre.sortir(vivant);
    }   

    @Test
    public void test_getObjet()throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        chambre.deposer(objetdeplacable);
        assertThat(chambre.getObjet("pdb"), equalTo(objetdeplacable));
        chambre.retirer("pdb");
        assertThat(chambre.getObjet("pdb"), equalTo(null));
    }

    
    @Test
    public void test_contientVivant()throws VivantAbsentDeLaPieceException,ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        assertThat(chambre.contientVivant(vivant), equalTo(true));
        assertThat(chambre.contientVivant("Yvan"), equalTo(true));

        chambre.sortir(vivant);
        assertThat(chambre.contientVivant(vivant), equalTo(false));
        assertThat(chambre.contientVivant("Yvan"), equalTo(false));
    }

    @Test
    public void test_entrerSortir() throws VivantAbsentDeLaPieceException{
        assertThat(chambre.contientVivant("Yvan"), equalTo(true)); 
        chambre.sortir(vivant);
        assertThat(chambre.contientVivant("Yvan"), equalTo(false)); 
        chambre.entrer(vivant);
        assertThat(chambre.contientVivant(vivant), equalTo(true));
    }

}









