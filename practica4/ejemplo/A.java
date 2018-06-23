package ejemplo;

import java.io.FileNotFoundException;
import ms.MessageSystem;

public class A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean tcp = true;
		String networkFile = "peers.txt";
		
		for (String arg : args) {
			if (arg.equals("-u"))
				tcp = false;
			else
				networkFile = arg;
		}
		
		try {
			MessageSystem ms = new MessageSystem(1, networkFile, tcp);
			ms.send(3, new MessageValue(1));
			ms.stopMailbox();
		} catch (FileNotFoundException e) {
			System.err.println("El fichero " + networkFile + " no existe.");
		}
	}

}
