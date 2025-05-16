package fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles;

import fr.insarouen.iti.prog.aventure.data.fichier.compilation.tableDesSymboles;

public class Variable {
	private String valeur;  
	
	public Variable(String valeur) {
		this.valeur = valeur;
	}

	@Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.id);
    }

	@Override
	public boolean equals(Object o) {
		if (o==this) {
			return true;            
		} else if (o instanceof Variable) {
			return ((Identifiant)o).toString().equals(toString());
		}
		return false;
	}

	@Override
    public String toString() {
		return id;
    }
}