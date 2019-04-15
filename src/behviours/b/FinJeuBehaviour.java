package behviours.b;

import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;

public class FinJeuBehaviour extends OneShotBehaviour {
	AgentB agent;
	
	public FinJeuBehaviour(AgentB a){
		this.agent = a;
	}
	
	public void action() {
		System.out.println(agent.getAID().getLocalName() + " : Fin du jeu après " + agent.getNbEssais() +" essai(s)");
		agent.doDelete();
	}

}
