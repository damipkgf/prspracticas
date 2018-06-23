/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Invocador.java
 * TIEMPO: 6h
 * DESCRIPCION: Implementacion del algoritmo Ricart-Agrawala mediante paso de mensajes.
 */

package practica7;

import practica7.Listener;

public class Invocador {

	public static void main(String args[]) {
		
		int pid = Integer.parseInt(args[0]);

		try {
			MessageSystem ms = new MessageSystem(pid, "peers.txt", true);

			SharedVars compartidas = new SharedVars(ms);
			compartidas.me = pid;

			Listener lis = new Listener(compartidas);
			lis.start();
			Thread.sleep(5000);
			
			compartidas.mutex.acquire();
			compartidas.reqcritsec = true;
			compartidas.ourSecNum = compartidas.highestsecnum + 1;
			compartidas.mutex.release();
			
			compartidas.outRepCount = compartidas.N - 1;
			
			for (int i = 1; i <= compartidas.N; i++) {
				if (i != compartidas.me) {
					compartidas.ms.send(i, new MessageValue(true, compartidas.ourSecNum, compartidas.me));
				}
			}
			
			compartidas.espera.acquire();
			
			System.out.println("Proceso " + compartidas.me + " entra en la critical section");
			Thread.sleep(5000);
			
			compartidas.reqcritsec = false;

			for (Integer deferred : compartidas.repDef) {
				compartidas.ms.send(deferred, new MessageValue(false, 1, 1));
			}
			compartidas.repDef.removeAll(compartidas.repDef);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
