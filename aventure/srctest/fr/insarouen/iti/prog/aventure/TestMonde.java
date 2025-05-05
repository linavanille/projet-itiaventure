package fr.insarouen.iti.prog.aventure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

class EntiteConcrete extends Entite {
        public EntiteConcrete (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
            super(nom,monde);
        }
    }

public class TestMonde{
    private  Monde monde;
    private  EntiteConcrete entite;
    private  EntiteConcrete entite2;

    @Before
    public  void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
        monde=new Monde("Terre");
    }

    @Test
    public void test_nom(){
        assertThat(monde.getNom(), equalTo("Terre"));
    }
    @Test
    public void testGetEntite() throws NomDEntiteDejaUtiliseDansLeMondeException{
        assertThat(this.monde.getEntite("Patate"), equalTo(null));
        entite= new EntiteConcrete("Patate", this.monde);
        assertThat(this.monde.getEntite("Patate"), equalTo(entite));
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void testAjouterEntiteNomDejaUtilise() throws NomDEntiteDejaUtiliseDansLeMondeException{
        entite = new EntiteConcrete("Patate", this.monde);
        entite2 = new EntiteConcrete("Patate", this.monde);
    }

    @Test(expected = EntiteDejaDansUnAutreMondeException.class)
    public void testAjouterEntiteDejaDansUnAutreMonde() throws NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
        Monde monde2 = new Monde("Mars");
        entite = new EntiteConcrete("Patate", this.monde);
        monde2.ajouter(entite);
    }
    
    @Test
    public void testGetEntites() throws NomDEntiteDejaUtiliseDansLeMondeException{
        entite=new EntiteConcrete ("Patate", this.monde);
        entite2=new EntiteConcrete ("PommeDeTerre", this.monde);
        assertThat(monde.getEntites().toArray(new Entite[0]),arrayContainingInAnyOrder(entite,entite2));
    }
    @Test
    public void testGenererNom() throws NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
        String[] tab_string= new String[10];
        for (int i=0; i<10; i++){
            tab_string[i]=monde.genererNom("rayen");
            new EntiteConcrete(tab_string[i],this.monde);
        }
    }
}
