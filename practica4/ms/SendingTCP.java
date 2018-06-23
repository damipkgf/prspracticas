/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: SendingTCP.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package ms;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SendingTCP extends Sending {
	
	int _src;

	SendingTCP(int user, ArrayList<InetSocketAddress> peers) {
		_src = user;
		_peers = peers;
	}
	
	@Override
	public void send(int dst, Serializable message) {
		try {
			Socket sender = new Socket(_peers.get(dst-1).getAddress(), _peers.get(dst-1).getPort());
			Envelope sobre = new Envelope(_src, dst, message);
			ObjectOutputStream oos = new ObjectOutputStream(sender.getOutputStream());
			oos.writeObject(sobre);
			oos.close();
			sender.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
