package ejemplo;

import java.io.FileNotFoundException;
import ms.Envelope;
import ms.MessageSystem;

public class C {

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
			MessageSystem ms = new MessageSystem(3, networkFile, tcp);
			int valor;
			Envelope e;
			e = ms.receive();
			
			valor = ((MessageValue)e.getPayload()).getValue();
			System.out.println("El primer valor recibido es " + valor);
			e = ms.receive();
			valor = ((MessageValue)e.getPayload()).getValue();
			System.out.println("El segundo valor recibido es " + valor);
			ms.stopMailbox();
			
		} catch (FileNotFoundException e) {
			System.err.println("El fichero " + networkFile + " no existe.");
		}
	}

}
