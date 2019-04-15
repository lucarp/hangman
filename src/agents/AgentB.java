package agents;

import java.util.Random;

import behviours.b.FinJeuBehaviour;
import behviours.b.InitialisationBehaviour;
import behviours.b.JeuBehaviour;
import behviours.b.StartBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

public class AgentB extends Agent {
	
	private static final String BEHAVIOUR_FIN = "fin";
	private static final String BEHAVIOUR_JEU = "jeu";
	private static final String BEHAVIOUR_INITIALISATION = "initialisation";
	private static final String BEHAVIOUR_START = "start";

	public static AID IDENTIFIANT = new AID("agentB", AID.ISLOCALNAME);

	private int nbEssais;
	private String alphabet = "abcdefghijklmnopqrstuvxz";
	private String correctGuesses = "";
	private String incorrectGuesses = "";
	private int letterCount;
	
	public void setup(){
		FSMBehaviour behaviour = new FSMBehaviour(this);
		
		//Etats
		behaviour.registerFirstState(new StartBehaviour(this), BEHAVIOUR_START);
		behaviour.registerState(new InitialisationBehaviour(this), BEHAVIOUR_INITIALISATION);
		behaviour.registerState(new JeuBehaviour(this), BEHAVIOUR_JEU);
		behaviour.registerLastState(new FinJeuBehaviour(this), BEHAVIOUR_FIN);
		
		//Transitions
		behaviour.registerDefaultTransition(BEHAVIOUR_START, BEHAVIOUR_INITIALISATION);
		behaviour.registerDefaultTransition(BEHAVIOUR_INITIALISATION, BEHAVIOUR_JEU);
		behaviour.registerTransition(BEHAVIOUR_JEU, BEHAVIOUR_JEU, 1);
		behaviour.registerTransition(BEHAVIOUR_JEU, BEHAVIOUR_FIN, 0);
		
		addBehaviour(behaviour);
	}
	
	public int jouer(){
		nbEssais ++;
		Random random = new Random();
		int index = random.nextInt(alphabet.length());
		int guess = (int) alphabet.charAt(index);
		alphabet = alphabet.substring(0,index) + alphabet.substring(index+1);
		System.out.println(getLocalName() + " : Guess = " + (char)guess);
		return guess;
		//return (int) (borneInf + Math.random() * (borneSup - borneInf + 1));
	}
	

	public int getNbEssais() {
		return nbEssais;
	}

	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}


	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount;
	}
	
	public void addCorrectGuess(int letter) {
		this.correctGuesses += (char) letter;
	}
	
	public void addIncorrectGuess(int letter) {
		this.incorrectGuesses += (char) letter;
	}
	
}
