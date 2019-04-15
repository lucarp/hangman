package behviours.b;

import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import outils.AgentLogger;

public class InitialisationBehaviour extends OneShotBehaviour{
	AgentB agent;
	
	public InitialisationBehaviour(AgentB a){
		this.agent = a;
	}

	public void action() {
		//Attente d'un message de l'agent A contenant les bornes sup et inf
		agent.doWait();
		ACLMessage message = agent.receive();
		AgentLogger.log(message);
		
		agent.setNbEssais(0);
		
		if (message != null && message.getContent() != null){
			try{
				agent.setLetterCount(Integer.parseInt(message.getContent()));
			} catch(Exception ex){
				System.out.println(ex.getMessage());
				agent.doDelete();
			}
		}
		
	}

}
