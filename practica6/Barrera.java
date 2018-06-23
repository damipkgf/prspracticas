/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Barrera.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca haciendo uso de paso de mensajes a servidores
 * para tratar con la memoria compartida.
 */

package practica6;

import java.util.LinkedList;

import practica6.Envelope;
import practica6.MessageSystem;

public class Barrera extends Thread {

	private int pid;

	public Barrera(int _pid) {
		pid = _pid;
	}

	public void run() {
		try {
			MessageSystem ms = new MessageSystem(pid, "peers.txt", true);
			Envelope e;
			int cont = 0;
			LinkedList<Integer> q = new LinkedList<Integer>();
			while (true) {
				e = ms.receive();

				if (((MessageValue) e.getPayload()).getWait()) {
					if (cont == 0) {
						q.add(e.getSource());
					} else if (cont == 1) {
						ms.send(e.getSource(), new MessageValue(1, 1, true, false));
						cont--;
					}
				} else {
					cont++;
					if (!q.isEmpty()) {
						ms.send(q.remove(), new MessageValue(1, 1, true, false));
						cont--;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
