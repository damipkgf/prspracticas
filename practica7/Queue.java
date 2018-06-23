/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Queue.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package practica7;

import java.util.LinkedList;

public class Queue {

	private LinkedList<Envelope> _mailbox = new LinkedList<Envelope>();
	private Envelope _ultimo;
	private int tamBuzon = 1;

	public synchronized Envelope getNext() {
		while (_mailbox.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		_ultimo = _mailbox.getFirst();
		_mailbox.remove();
		notifyAll();
		return _ultimo;

	}

	public synchronized void putNext(Envelope sobre) {
		while (_mailbox.size() > tamBuzon) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		_mailbox.add(sobre);
		notifyAll();
	}
}
