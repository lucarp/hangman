package behviours.a;

import agents.AgentA;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import outils.AgentLogger;

public class JeuBehaviour extends OneShotBehaviour{
	AgentA agent;
	int retour;
	
	public JeuBehaviour(AgentA a) {
		this.agent = a;
		retour = -1;
	}
	
	public void action() {
		
		//Attente d'un essai
		agent.doWait();
		ACLMessage message = agent.receive();
		AgentLogger.log(message);
		int nb = 0;
		try{
			nb = Integer.parseInt(message.getContent());
		} catch(Exception ex){
			System.out.println("Erreur : message incorrect");
			agent.doDelete();
		}
		
		//Réponse
		retour = agent.essai(nb);
		ACLMessage reponse = new ACLMessage(ACLMessage.INFORM);
		reponse.setContent(retour+"");
		reponse.addReceiver(new AID("agentB",AID.ISLOCALNAME));
		agent.send(reponse);

	}
	
	public int onEnd(){
		return Math.abs(retour);
	}
}
