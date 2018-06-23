/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Sending.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package practica7;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;

interface SendingInterface {
	public void send(int dst, Serializable message);
}

public abstract class Sending implements SendingInterface {
	ArrayList<InetSocketAddress> _peers;
}

