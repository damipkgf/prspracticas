/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: ClientFramer
 * TIEMPO: 10h
 * DESCRIPCI'ON: Este programa implementa un intercambio de mensajes mediante sockets TCP donde la estructura de
 * campos de dichos mensajes puede variarse. Sigue una topologia cliente-servidor donde el cliente manda una peticion
 * al servidor indicando un intervalo sobre el que calcular los numeros primos contenidos en el.
 */

package practica5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientFramer {

	public ClientFramer() {

	}

	public void frameMsg(byte[] message, OutputStream out) throws IOException {
		/*
		 * Da formato de trama al mensaje (a???ade campo de longitud) y lo env???a por
		 * el socket
		 */
		DataOutputStream d_out = new DataOutputStream(out);
		d_out.writeInt(message.length);
		d_out.write(message);
		d_out.flush();
	}

	public byte[] nextMsg(InputStream in) throws IOException, EOFException {

		int length;

		DataInputStream d_in = new DataInputStream(in);

		length = d_in.readInt();

		byte[] msg = new byte[length];
		d_in.readFully(msg); // si devuelve excepción, es un error de entramado
		return msg;

	}
}
