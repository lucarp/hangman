package behviours.b;

import agents.AgentA;
import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import outils.AgentLogger;

public class JeuBehaviour extends OneShotBehaviour {
	AgentB agent;
	int retour;
	
	public JeuBehaviour(AgentB a) {
		this.agent = a;
		retour = -1;
	}

	
	public void action() {
		int essai = agent.jouer();
		ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);
		message.setContent(essai + "");
		message.addReceiver(AgentA.IDENTIFIANT);
		agent.send(message);
		
		//Attente de la r�ponse de l'agent A
		agent.doWait();
		
		ACLMessage reponse = agent.receive();
		if (reponse != null && reponse.getContent() != null){
			AgentLogger.log(reponse);
			try{
				int resultat = Integer.parseInt(reponse.getContent());
				if (resultat == -1){
					agent.addIncorrectGuess(essai);
				} else if (resultat == 1) {
					agent.addCorrectGuess(essai);
				}
				
				retour = Math.abs(resultat);
				
			} catch (Exception e) {
				System.out.println(agent.getAID().getLocalName() + " : Une erreur est survenue.");
				agent.doDelete();
			}
		}
		
	}
	
	public int onEnd(){
		return retour;
	}

}
