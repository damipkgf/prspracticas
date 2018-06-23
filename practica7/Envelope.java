/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Envelope.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package practica7;

import java.io.Serializable;

public class Envelope implements Serializable {
	private static final long serialVersionUID = 1L;
	private int source;
	private int destination;
	private Serializable payload;

	public Envelope(int s, int d, Serializable p) {
		source = s;
		destination = d;
		payload = p;
	}
	
	public int getSource() { return source; }
	public int getDestination() { return destination; }
	public Serializable getPayload() { return payload; }
}
