package fr.insarouen.iti.prog.aventure.spaceopera;

import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class Badge extends Cle{

        public Badge(String nom, Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException {
            super(nom, galaxie);
        }

}
