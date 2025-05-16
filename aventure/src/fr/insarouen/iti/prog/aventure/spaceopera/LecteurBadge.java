package fr.insarouen.iti.prog.aventure.spaceopera;

import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class LecteurBadge extends Serrure{

    public LecteurBadge(Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(galaxie);
    }

    public LecteurBadge(String nom, Galaxie galaxie) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, galaxie);
    }

}