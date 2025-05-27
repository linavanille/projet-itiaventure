package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.ActivationException;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.spaceopera.Galaxie;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Collection;

/**
 * Classe représentant un joueur contrôlé par l'utilisateur.
 * Il peut exécuter des commandes textuelles pour interagir avec le monde.
 */
public class JoueurHumain extends Vivant {

	private String ordre;

	/**
	 * Crée un joueur humain.
	 */
	public JoueurHumain(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde, pointsVie, pointsForce, piece, objets);
	}

	public String getOrdre() {
		return this.ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	private void commandePrendre(String nomObjet) throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
		this.prendre(nomObjet);
	}

	private void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException {
		this.deposer(nomObjet);
	}

	private void commandeFranchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
		this.franchir(nomPorte);
	}

	private void commandeOuvrirPorte(String nomPorte) throws ActivationException, PorteInexistanteDansLaPieceException {
		if (!this.getPiece().aLaPorte(nomPorte)) {
			throw new PorteInexistanteDansLaPieceException(
				String.format("%s ne possède pas %s", this.getPiece().getNom(), nomPorte));
		}
		this.getPiece().getPorte(nomPorte).activer();
	}

	private void commandeOuvrirPorte(String nomPorte, String nomObjet)
			throws ActivationException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException {
		if (!this.possede(nomObjet)) {
			throw new ObjetNonPossedeParLeVivantException(
				String.format("%s ne possède pas %s", this.getNom(), nomObjet));
		}
		if (!this.getPiece().aLaPorte(nomPorte)) {
			throw new PorteInexistanteDansLaPieceException(
				String.format("%s ne possède pas %s", this.getPiece().getNom(), nomPorte));
		}
		this.getPiece().getPorte(nomPorte).activerAvec(this.getObjet(nomObjet));
	}

	private Method getMethod(String commande, List<String> args) throws NoSuchMethodException {
		Class<?>[] argTypes = new Class<?>[args.size()];
		Arrays.fill(argTypes, String.class);
		return this.getClass().getDeclaredMethod("commande" + commande, argTypes);
	}

	/**
	 * Exécute la commande tapée par l'utilisateur.
	 */
	@Override
	public void executer() throws ITIAventureException {
		Scanner stdin = new Scanner(this.ordre);
		String commande = stdin.next();
		List<String> args = new ArrayList<>();
		while (stdin.hasNext()) {
			args.add(stdin.next());
		}

		try {
			Method m = this.getMethod(commande, args);
			m.invoke(this, args.toArray());
		} catch (InvocationTargetException e) {
			throw (ITIAventureException) e.getTargetException();
		} catch (Exception e) {
			throw new CommandeImpossiblePourLeVivantException("Commande invalide : " + commande);
		} finally {
			stdin.close();
		}
	}

	/**
	 * Affiche un résumé du joueur, de sa position et de ses objets.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Le joueur ").append(this.getNom());

		if (this.getMonde() instanceof Galaxie) {
			sb.append(" est dans le vaisseau spatial ").append(this.getPiece().getNom());
			sb.append("\nObjets dans le vaisseau spatial : ").append(this.getPiece().getObjets());
			sb.append("\nObjets possédés : ").append(this.getObjets());
			sb.append("\nPortails accessibles : ");
		} else {
			sb.append(" est dans la pièce ").append(this.getPiece().getNom());
			sb.append("\nObjets dans la pièce : ").append(this.getPiece().getObjets());
			sb.append("\nObjets possédés : ").append(this.getObjets());
			sb.append("\nPortes accessibles : ");
		}

		for (Porte porte : this.getPiece().getPortes()) {
			sb.append(porte.getNom()).append(" [").append(porte.getEtat()).append("], ");
		}

		if (!this.getPiece().getPortes().isEmpty()) {
			sb.setLength(sb.length() - 2); // Enlève la dernière virgule
		}

		return sb.toString();
	}

}

