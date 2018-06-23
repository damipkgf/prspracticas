/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: HandleMessage
 * TIEMPO: 10h
 * DESCRIPCI'ON: Este programa implementa un intercambio de mensajes mediante sockets TCP donde la estructura de
 * campos de dichos mensajes puede variarse. Sigue una topologia cliente-servidor donde el cliente manda una peticion
 * al servidor indicando un intervalo sobre el que calcular los numeros primos contenidos en el.
 */

package practica5;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleMessage {

	private ClientMsg _msg; // Clase para el mensaje
	private ClientCoder _coder;// Codificador del mensaje
	private ClientFramer _framer;// Clase para enviar/recibir el mensaje: entramar/desentramar en bytes a partir
									// de la clase mensaje
	private int _int1;
	private int _int2;
	private String _ip;
	private int _port;
	private Socket sock;
	private int timeout = 1000;

	public HandleMessage(int int1, int int2, String ip, int port) {
		_ip = ip;
		_port = port;
		_int1 = int1;
		_int2 = int2;
	}

	private void sendMessage(ClientMsg msg, ClientCoder coder, ClientFramer framer) throws Exception {

		OutputStream out = sock.getOutputStream();

		byte[] mensaje = _coder.toWire(msg);

		_framer.frameMsg(mensaje, out);

	}

	private ClientMsg receiveMessage(ClientCoder coder, ClientFramer framer) throws Exception {

		InputStream in = sock.getInputStream();

		byte[] info = framer.nextMsg(in);

		return coder.fromWire(info);

	}

	public int[] getPrimeNumbers() {

		int intentos = 1;
		boolean acabado = false;

		try {
			sock = new Socket(_ip, _port);
		} catch (Exception e) {

		}

		_msg = new ClientMsg();
		_msg.setIni(_int1);
		_msg.setFin(_int2);

		_coder = new ClientCoder();

		_framer = new ClientFramer();
		ClientMsg _msgrecibido = new ClientMsg();

		while (intentos < 10 && !acabado) {
			try {
				sock.setSoTimeout(timeout);
				sendMessage(_msg, _coder, _framer);
				_msgrecibido = receiveMessage(_coder, _framer);
				acabado = true;
			} catch (Exception e) {
				System.out.println("Excepcion, reintentando...");
				timeout = 2 * timeout;
			}

		}

		return _msgrecibido.getPrimos();
	}

}
