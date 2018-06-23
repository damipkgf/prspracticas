/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: MailboxTCP.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package ms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import ms.Envelope;

public class MailBoxTCP extends MailBox {

	boolean abierto = true;
	ServerSocket welcoming;
	InetSocketAddress _server;

	public MailBoxTCP(InetSocketAddress server) {
		_server = server;
		try {
			welcoming = new ServerSocket(_server.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeSocket() {
		try {
			welcoming.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			while (abierto) {
				Socket input = welcoming.accept();
				ObjectInputStream ois = new ObjectInputStream(input.getInputStream());
				Envelope recibido = (Envelope)ois.readObject();
				_queue.putNext(recibido);
				//input.close();
			}
		} catch (SocketException e) {
			System.err.println("Cerrando buzon..."); // al cerrar el socket, "paramos" el hilo
			abierto = false;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
