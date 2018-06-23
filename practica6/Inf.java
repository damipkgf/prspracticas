/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Inf.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca haciendo uso de paso de mensajes a servidores
 * para tratar con la memoria compartida.
 */

package practica6;

import practica6.MessageValue;
import practica6.MessageSystem;

public class Inf extends Thread {
	int pid;
	int nTel, nInf;
	int nTot;

	Inf(int _pid) {
		pid = _pid;
	}

	public void row() {
		System.out.println("Remando...");
	}

	public void board() {
		System.out.println("Embarcando informatico...");
	}

	public void run() {

		try {
			MessageSystem ms = new MessageSystem(pid, "peers.txt", true);
			//mutex1 acquire
			ms.send(22, new MessageValue(1, 1, true, false));
			Envelope e = ms.receive();
			nTel = ((MessageValue) e.getPayload()).getValueTel();
			nInf = ((MessageValue) e.getPayload()).getValueInf();

			nInf++;
			
			if (nInf == 2 && nTel >= 2) {
				nInf -= 2;
				nTel -= 2;

				//mutex1 release
				ms.send(22, new MessageValue(nInf, nTel, false, false));

				//barrera colainf release
				ms.send(20, new MessageValue(1, 1, false, false));
				
				//barrera colatel release
				ms.send(21, new MessageValue(1, 1, false, false));
				ms.send(21, new MessageValue(1, 1, false, false));

			} else if (nInf == 4) {
				nInf -= 4;
				
				//mutex1 release
				ms.send(22, new MessageValue(nInf, nTel, false, false));

				//barrera colainf release
				ms.send(20, new MessageValue(1, 1, false, false));
				ms.send(20, new MessageValue(1, 1, false, false));
				ms.send(20, new MessageValue(1, 1, false, false));

			} else {
				//mutex1 release
				ms.send(22, new MessageValue(nInf, nTel, false, false));

				//barrera colainf acquire
				ms.send(20, new MessageValue(1, 1, true, false));
				ms.receive();
			}
			board();
			
			ms.send(23, new MessageValue(1, 1, true, true));
			Envelope env = ms.receive();
			//mutex2 acquire

			nTot = ((MessageValue) env.getPayload()).getValueTel();
			
			nTot++;
			if (nTot == 4) {
				nTot = 0;
				row();
			}
			//mutex2 release
			ms.send(23, new MessageValue(nTot, nTot, false, true));
			
			ms.stopMailbox();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
