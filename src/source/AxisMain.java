package source;



public class AxisMain {
	public static void main(String[] args) {

		AxisClientThread.fillServerNameAndPort(args);
		int numOfClients = Integer.parseInt(args[2]);
		MainClient mainClient = new MainClient();
		mainClient.fillAvailableScreenResolutionsFromServer();	
		mainClient.chooseResolutionAndFpsForClients(numOfClients);

		for (int i = 0; i < numOfClients; i++) {
			Thread axisClientThread = new Thread(new AxisClientThread("AxisClient", i));
			axisClientThread.start();
		}

	}
	

	
}
