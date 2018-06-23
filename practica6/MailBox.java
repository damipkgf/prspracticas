/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Mailbox.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package practica6;

/*
* A�adir imports necesarios
*/
import practica6.Envelope;

interface MailBoxInterface {
	public void closeSocket();
	public Envelope getNextMessage();
}

public abstract class MailBox extends Thread implements MailBoxInterface {
	final static int SIZEMBMAX=100000;
	protected Queue _queue = new Queue();
	public Envelope getNextMessage(){
		return _queue.getNext();
	}
}
