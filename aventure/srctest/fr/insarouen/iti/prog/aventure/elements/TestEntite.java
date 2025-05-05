package fr.insarouen.iti.prog.aventure.elements;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
class EntiteConcrete extends Entite {
        public EntiteConcrete (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
            super(nom,monde);
        }
    }

public class TestEntite{
  
    private static EntiteConcrete entiteConcrete,entiteConcrete2;
    private static Monde monde;
    @BeforeClass
    public static void avantTest(){
        monde = new Monde("Terre"); 
        try{
            entiteConcrete = new EntiteConcrete("Guillaume", monde);
            entiteConcrete2=new EntiteConcrete("David",monde);
        }
        catch(NomDEntiteDejaUtiliseDansLeMondeException ex){
            ex.printStackTrace();
        }    
    }

    @Test
    public void testGetNom_1(){
        assertThat(entiteConcrete.getNom(), equalTo("Guillaume"));
    }
    @Test
    public void testGetNom_2(){
        assertThat(entiteConcrete.getNom(), not(equalTo("Guillaum")));
    }
    @Test
    public void testGetMonde_1(){
        assertThat(entiteConcrete.getMonde(), equalTo(monde));
    }
    @Test
    public void testGetMonde_2(){
        assertThat(entiteConcrete.getMonde(), not(equalTo("Mars")));
    }

    @Test
    public void testequals_1(){  
        assertThat(entiteConcrete.equals(entiteConcrete), equalTo(true));
    }

    @Test
    public void testequals_2(){ 
        assertThat(entiteConcrete.equals(entiteConcrete2), equalTo(false));
    }

    

}