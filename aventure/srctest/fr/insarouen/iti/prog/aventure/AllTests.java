package fr.insarouen.iti.prog.aventure;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.iti.prog.aventure.elements.AllTestsElements;

@RunWith(Suite.class)
@SuiteClasses({
    AllTestsElements.class
})
public class AllTests{
}