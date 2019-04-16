package behviours.b;

import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;

public class FinJeuBehaviour extends OneShotBehaviour {
	AgentB agent;
	
	public FinJeuBehaviour(AgentB a){
		this.agent = a;
	}
	
	public void action() {
		if(agent.getLostMatch()) {
			System.out.println(agent.getAID().getLocalName() + " : Game over! I've lost. :(");
		} else {
			System.out.println(agent.getAID().getLocalName() + " : Game over! I've won! \\o/");
		}
		agent.doDelete();
	}

}
