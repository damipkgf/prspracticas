/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: SendingUDP.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package ms;

import java.io.ByteArrayOutputStream;
//TO-DO
/*
* A�adir imports necesarios
*/
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SendingUDP extends Sending{

	int _src;
	SendingUDP(int user, ArrayList<InetSocketAddress> peers){
		_src = user;
		_peers = peers;
	}

	@Override
	public void send(int dst, Serializable message) {
		try {
			DatagramSocket sender = new DatagramSocket();
			Envelope sobre = new Envelope(_src, dst, message);
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bs);
			oos.writeObject(sobre);
			byte[] bytes = bs.toByteArray();
			DatagramPacket p = new DatagramPacket(bytes, bytes.length, _peers.get(dst-1).getAddress(), _peers.get(dst-1).getPort());
			sender.send(p);
			sender.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
