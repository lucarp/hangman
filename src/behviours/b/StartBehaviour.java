package behviours.b;

import agents.AgentA;
import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class StartBehaviour extends OneShotBehaviour {
	AgentB agent;
	
	public StartBehaviour(AgentB a){
		this.agent = a;
	}
	
	
	@Override
	public void action() {
		//Attente de 10 secondes avant de commencer le jeu
		agent.doWait(10000);
		
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.setContent("START GAME");
		message.addReceiver(AgentA.IDENTIFIANT);
		agent.send(message);
	}

}
