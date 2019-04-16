package behviours.a;

import agents.AgentA;
import jade.core.behaviours.OneShotBehaviour;

public class FinJeuBehaviour extends OneShotBehaviour {
	AgentA agent;
	
	public FinJeuBehaviour(AgentA a){
		this.agent = a;
	}
	
	public void action() {
		if(agent.getMissingLetters() > 0) {
			System.out.println(agent.getAID().getLocalName() + " : Game over! You've lost. :(");
		} else {
			System.out.println(agent.getAID().getLocalName() + " : Game over! You've won! \\o/");
		}
		agent.doDelete();
	}

}
