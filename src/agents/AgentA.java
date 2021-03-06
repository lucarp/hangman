package agents;

import java.util.*;

import behviours.a.FinJeuBehaviour;
import behviours.a.InitialisationBehaviour;
import behviours.a.JeuBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

public class AgentA extends Agent {
	
	private static final String BEHAVIOUR_FIN = "fin";
	private static final String BEHAVIOUR_INITIALISATION = "initialisation";
	private static final String BEHAVIOUR_JEU = "jeu";
	
	public static AID  IDENTIFIANT = new AID("agentA",AID.ISLOCALNAME);
	
	private String motCache;
	private String missingLetters;
	private String alphabet = "abcdefghijklmnopqrstuvxz";
	private int nombreCache;
	private int nbEssais;
	public int borneSup ;
	public int borneInf;
	
	public void setup(){
		FSMBehaviour behaviour = new FSMBehaviour(this);
		
		//Etats
		behaviour.registerFirstState(new InitialisationBehaviour(this), BEHAVIOUR_INITIALISATION);
		behaviour.registerState(new JeuBehaviour(this), BEHAVIOUR_JEU);
		behaviour.registerLastState(new FinJeuBehaviour(this), BEHAVIOUR_FIN);
		
		//Transitions
		behaviour.registerDefaultTransition(BEHAVIOUR_INITIALISATION, BEHAVIOUR_JEU);
		behaviour.registerTransition(BEHAVIOUR_JEU, BEHAVIOUR_JEU, 1);
		behaviour.registerTransition(BEHAVIOUR_JEU, BEHAVIOUR_FIN, 0);
		behaviour.registerTransition(BEHAVIOUR_JEU, BEHAVIOUR_FIN, 2);
		
		addBehaviour(behaviour);
		
	}
	
	public void initialiserMotCache(){
		motCache = "lucas";
		missingLetters = "lucas";
		this.setNbEssais(2 * motCache.length());
		System.out.println(getLocalName() + " : Mot cache = " + motCache);
	}
	
	public int essai(int essai){ //, String finalGuess
		String letter = String.valueOf((char) essai);
		
		if(missingLetters.length()>0 && nbEssais < 0) {
			//RETURN GANHOU JOGO!
			return -2;
		}
		
		if (alphabet.contains(letter)) {
			alphabet = alphabet.replace(letter, "");
			System.out.println("Alphabet = " + alphabet);
			if (missingLetters.contains(letter)) {
				System.out.println(getLocalName() + " : " + motCache + " contains letter " + letter);
				missingLetters = missingLetters.replace(letter, "");
				System.out.println("MissingLetters = " + missingLetters);
				if(missingLetters.length()==0) {
					//RETURN GANHOU JOGO!
					return 0;
				} else {
					// ACERTOU LETRA MAS O JOGO SEGUE
					return 1;
				}
			} else {
				// PALAVAR NAO TEM LETRA! PENALIZADO
				nbEssais--;
				System.out.println(getLocalName() + " : " + motCache + " does not contain letter " + letter);
				return -1;
			}
		} else {
			//ESCOLHEU LETRA QUE JA TINHA ESCOLHIDO ANTES ...NAO PERDE NADA
			return -1;
		}
	}

	public int getNbEssais() {
		return nbEssais;
	}

	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	public int getBorneSup() {
		return borneSup;
	}
	
	public int getLetterCount() {
		return this.motCache.length();
	}

	public int getMissingLetters() {
		return this.missingLetters.length();
	}

	public int getBorneInf() {
		return borneInf;
	}

	public void setBorneInf(int borneInf) {
		this.borneInf = borneInf;
	}

	public void initialiserJeu() {
		this.initialiserMotCache();
		
	}
}
