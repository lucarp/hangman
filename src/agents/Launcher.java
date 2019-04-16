package agents;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Launcher {
	public static void main(String[] args) {
		Runtime runtime = Runtime.instance();
		Profile config = new ProfileImpl("localhost", 8888, null);
		config.setParameter("gui", "true");
		AgentContainer mc = runtime.createMainContainer(config);
		AgentController ac1;
		AgentController ac2;
		try {
			ac1 = mc.createNewAgent("agentA", AgentA.class.getName(), null);
			ac2 = mc.createNewAgent("agentB", AgentB.class.getName(), null);
			ac1.start();
			ac2.start();
		} catch (StaleProxyException e) {
			System.out.println(e.getMessage());
		}
	}
}
