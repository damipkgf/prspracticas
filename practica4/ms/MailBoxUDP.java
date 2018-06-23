/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: MailboxUDP.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package ms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

//TO-DO
/*
* A�adir imports necesarios
*/
import ms.Envelope;

public class MailBoxUDP extends MailBox{
	
	InetSocketAddress _server;
	boolean abierto = true;
	DatagramSocket input;
	
	public MailBoxUDP(InetSocketAddress server){
		_server = server;
		try {
			input = new DatagramSocket(_server.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void closeSocket() {
		input.close();
	}

	@Override
	public void run() {
		try {
			byte[] buzon = new byte[SIZEMBMAX];
			DatagramPacket recibido = new DatagramPacket(buzon, buzon.length);
			while (abierto) {
				input.receive(recibido);
				ByteArrayInputStream bs = new ByteArrayInputStream(buzon);
				ObjectInputStream ois = new ObjectInputStream(bs);
				Envelope sobre = (Envelope)ois.readObject();
				_queue.putNext(sobre);
			}
		} catch (SocketException e) {
			System.err.println("Cerrando buzon...");
			abierto = false;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
