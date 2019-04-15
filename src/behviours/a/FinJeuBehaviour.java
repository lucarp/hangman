package behviours.a;

import agents.AgentA;
import jade.core.behaviours.OneShotBehaviour;

public class FinJeuBehaviour extends OneShotBehaviour {
	AgentA agent;
	
	public FinJeuBehaviour(AgentA a){
		this.agent = a;
	}
	
	public void action() {
		System.out.println(agent.getAID().getLocalName() + " : Fin du jeu après " + agent.getNbEssais() +" essai(s)");
		agent.doDelete();
	}

}
