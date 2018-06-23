/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Tel.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca haciendo uso de paso de mensajes a servidores
 * para tratar con la memoria compartida.
 */

package practica6;

import practica6.MessageValue;
import practica6.MessageSystem;

public class Tel extends Thread {
	int pid;
	int nTel, nInf;
	int nTot;

	Tel(int _pid) {
		pid = _pid;
	}

	public void row() {
		System.out.println("Remando...");
	}

	public void board() {
		System.out.println("Embarcando teleco...");
	}

	public void run() {

		try {
			MessageSystem ms = new MessageSystem(pid, "peers.txt", true);
			ms.send(22, new MessageValue(1, 1, true, false));
			Envelope e = ms.receive();
			nTel = ((MessageValue) e.getPayload()).getValueTel();
			nInf = ((MessageValue) e.getPayload()).getValueInf();

			nTel++;

			if (nTel == 2 && nInf >= 2) {
				nInf -= 2;
				nTel -= 2;

				ms.send(22, new MessageValue(nInf, nTel, false, false));

				ms.send(21, new MessageValue(1, 1, false, false));
				
				ms.send(20, new MessageValue(1, 1, false, false));
				ms.send(20, new MessageValue(1, 1, false, false));

			} else if (nTel == 4) {
				nTel -= 4;
				
				ms.send(22, new MessageValue(nInf, nTel, false, false));

				ms.send(21, new MessageValue(1, 1, false, false));
				ms.send(21, new MessageValue(1, 1, false, false));
				ms.send(21, new MessageValue(1, 1, false, false));

			} else {
				ms.send(22, new MessageValue(nInf, nTel, false, false));

				ms.send(21, new MessageValue(1, 1, true, false));
				ms.receive();
			}
			board();
			
			ms.send(23, new MessageValue(1, 1, true, true));
			Envelope env = ms.receive();

			nTot = ((MessageValue) env.getPayload()).getValueTel();
			
			nTot++;
			if (nTot == 4) {
				nTot = 0;
				row();
			}
			ms.send(23, new MessageValue(nTot, nTot, false, true));
			
			ms.stopMailbox();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
