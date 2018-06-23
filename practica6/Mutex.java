/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Mutex.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca haciendo uso de paso de mensajes a servidores
 * para tratar con la memoria compartida.
 */

package practica6;

import java.util.LinkedList;

import practica6.Envelope;
import practica6.MessageSystem;

public class Mutex extends Thread {

	private int pid;

	public Mutex(int _pid) {
		pid = _pid;
	}

	public void run() {
		try {
			MessageSystem ms = new MessageSystem(pid, "peers.txt", true);
			Envelope e;
			int cont1 = 1, cont2 = 1;
			int nTel = 0, nInf = 0;
			int nTot = 0;
			LinkedList<Integer> q = new LinkedList<Integer>();
			LinkedList<Integer> qTot = new LinkedList<Integer>();
			while (true) {
				e = ms.receive();
				if (((MessageValue) e.getPayload()).getTot()) { //mutex2
					if(((MessageValue) e.getPayload()).getWait()) {
						if (cont2 == 0) {
							qTot.add(e.getSource());
						} else if (cont2 == 1) {
							ms.send(e.getSource(), new MessageValue(nTot, nTot, true, false));
							cont2--;
						}
					} else {
						cont2++;
						nTot = ((MessageValue) e.getPayload()).getValueTel();
						if (!qTot.isEmpty()) {
							ms.send(qTot.remove(), new MessageValue(nTot, nTot, true, false));
							cont2--;
						}
						
					}
				} else if (((MessageValue) e.getPayload()).getWait()) { //mutex1
					if (cont1 == 0) { 
						q.add(e.getSource());
					} else if (cont1 == 1) {
						ms.send(e.getSource(), new MessageValue(nInf, nTel, true, false));
						cont1--;
					}
				} else {
					cont1++;
					nTel = ((MessageValue) e.getPayload()).getValueTel();
					nInf = ((MessageValue) e.getPayload()).getValueInf();
					if (!q.isEmpty()) {
						ms.send(q.remove(), new MessageValue(nInf, nTel, true, false));
						cont1--;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
