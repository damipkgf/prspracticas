/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Listener.java
 * TIEMPO: 6h
 * DESCRIPCION: Implementacion del algoritmo Ricart-Agrawala mediante paso de mensajes.
 */

package practica7;

import practica7.Envelope;

public class Listener extends Thread {

	SharedVars compartidas;
	Envelope e;
	boolean defer_it;
	int k;
	int j;

	public Listener(SharedVars _compartidas) {
		compartidas = _compartidas;
	}

	public void run() {
		while (true) {
			e = compartidas.ms.receive();

			if (((MessageValue) e.getPayload()).getRequest()) {

				k = ((MessageValue) e.getPayload()).getOurSecNum();
				j = ((MessageValue) e.getPayload()).getRemitente();

				compartidas.highestsecnum = Math.max(compartidas.highestsecnum, k);

				try {
					compartidas.mutex.acquire();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				defer_it = (compartidas.reqcritsec)
						&& ((k > compartidas.ourSecNum) || ((k == compartidas.ourSecNum) && (j > compartidas.me)));

				compartidas.mutex.release();

				if (defer_it) {
					compartidas.repDef.add(j);
					
				} else {
					compartidas.ms.send(j, new MessageValue(false, 1, 1));
				}
			} else {
				compartidas.outRepCount--;
				if (compartidas.outRepCount == 0) {
					compartidas.espera.release();
				}
			}
		}
	}
}
