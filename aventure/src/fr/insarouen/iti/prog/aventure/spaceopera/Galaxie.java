package fr.insarouen.iti.prog.aventure.spaceopera;

import fr.insarouen.iti.prog.aventure.Monde;

public class Galaxie extends Monde{

    public Galaxie(String nom){
        super(nom);
    }

    public String toString(){
        
        StringBuilder resultat = new StringBuilder("Nom de la Galaxie : ");
        resultat.append(this.getNom());
        resultat.append("\nEntites :");
        Entite [] listeEntites = this.tabEntite.values().toArray(new Entite[0]);
		for (int i=0;i<this.tabEntite.size();i++){
			resultat.append(listeEntites[i].getNom());
            resultat.append("\n");
		}
        return resultat.toString();
    }

}