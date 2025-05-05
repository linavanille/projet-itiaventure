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
import fr.insarouen.iti.prog.aventure.elements.Etat;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;

public class TestPorte{
    private static Monde monde;
    private static Piece piece1,piece2;
    private static Porte porte,porte2;
    private static ObjetConcret objet;
    @BeforeClass 
    public static void avant_test() throws NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
        monde = new Monde("Maison");
        piece1 = new Piece("Chambre",monde);
        piece2 = new Piece("Cuisine",monde);
        porte = new Porte("porte",monde,piece1,piece2);
        objet = new ObjetConcret("Patate",monde);
    }
    
    @Test
    public void test_getPieceAutreCote(){
        assertThat(porte.getPieceAutreCote(piece1), equalTo(piece2));
        assertThat(porte.getPieceAutreCote(piece2), equalTo(piece1));
    }

    @Test
    public void test_getEtat(){
        assertThat(porte.getEtat(), equalTo(Etat.FERME));
    }
    
    @Test
    public void test_activer_sans_exception() throws ActivationImpossibleAvecObjetException{
        porte.activer();
        assertThat(porte.getEtat(),equalTo(Etat.OUVERT));
        porte.activer();
        assertThat(porte.getEtat(),equalTo(Etat.FERME));
        assertThat(porte.activableAvec(objet),equalTo(false));
    }

    @Test(expected=ActivationImpossibleAvecObjetException.class)
    public void test_activer_exception() throws ActivationImpossibleAvecObjetException,ActivationImpossibleException{
        porte.activerAvec(objet);
    }

    @Test
    public void test_porte_functions() throws EntiteDejaDansUnAutreMondeException,NomDEntiteDejaUtiliseDansLeMondeException{
        //piece1.addPorte(porte);
        assertThat(piece1.aLaPorte(porte),equalTo(true));
        assertThat(piece1.aLaPorte(porte.getNom()),equalTo(true));
        assertThat(piece2.aLaPorte(porte),equalTo(true));
        assertThat(piece2.aLaPorte(porte.getNom()),equalTo(true));
        porte2= new Porte("porte2",monde,piece1,piece2);
        piece1.addPorte(porte2);
        assertThat(piece1.getPortes().toArray(new Porte[0]),arrayContainingInAnyOrder(porte,porte2));
        assertThat(piece2.getPortes().toArray(new Porte[0]),arrayContainingInAnyOrder(porte,porte2));

    }
}