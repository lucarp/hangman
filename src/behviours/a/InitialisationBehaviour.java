package behviours.a;

import agents.AgentA;
import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import outils.AgentLogger;

public class InitialisationBehaviour extends OneShotBehaviour{
	AgentA agent;
	
	public InitialisationBehaviour(AgentA a){
		this.agent = a;
	}

	public void action() {
		//Attente d'un message de l'agent B pour commencer le jeu
		agent.doWait();
		ACLMessage message = agent.receive();
		AgentLogger.log(message);
		
		//Initialisation des param�tres du jeu (nombre cach�, bornes sup et inf, nombre d'essais effectu�s.
		agent.initialiserJeu();
		
		//Envoi des bornes inf et sup � l'agent B
		ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
		reponse.setContent(Integer.toString(agent.getLetterCount()));
		reponse.addReceiver(AgentB.IDENTIFIANT);
		agent.send(reponse);
		
	}

}
