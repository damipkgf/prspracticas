/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: ClientCoder
 * TIEMPO: 10h
 * DESCRIPCI'ON: Este programa implementa un intercambio de mensajes mediante sockets TCP donde la estructura de
 * campos de dichos mensajes puede variarse. Sigue una topologia cliente-servidor donde el cliente manda una peticion
 * al servidor indicando un intervalo sobre el que calcular los numeros primos contenidos en el.
 */

package practica5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* Wire Format
 * Client:
 *                                1  1  1  1  1  1
 *  0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |     Magic       |Type |       ZERO            |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |				    Message ID				      |
 * |											      |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |              Initial Integer                  |
 * |										          |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |              Final Integer                    |
 * |         	               			          |  
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * 
 * Server: 
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |     Magic       |Type |       ZERO            |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |				    Message ID				      |
 * |											      |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |              Number of primes                 |
 * |											      |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |              First prime                      |
 * |         	               			          |  
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |              Second prime                     |
 * |         	               				      |  
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |              ............                     |
 * |         	               				      |  
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |              Last prime                       |
 * |         	               				      |  
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * 
 */
public class ClientCoder {

	public byte[] toWire(ClientMsg msg) throws IOException {

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(byteStream);

		out.writeShort(0xA800);

		out.writeInt(msg.getID());

		out.writeInt(msg.getIni());

		out.writeInt(msg.getFin());

		out.flush();

		byte[] data = byteStream.toByteArray();

		return data;
	}

	public ClientMsg fromWire(byte[] input) throws IOException {

		ClientMsg mensaje = new ClientMsg();

		ByteArrayInputStream bs = new ByteArrayInputStream(input);
		DataInputStream in = new DataInputStream(bs);

		in.readShort();

		int messageId = in.readInt();

		mensaje.setID(messageId);

		int numPrimos = in.readInt();

		mensaje.setNum(numPrimos);

		int primo;

		for (int i = 0; i < numPrimos; i++) {
			primo = in.readInt();
			mensaje.setPrimo(i, primo);
		}

		return mensaje;
	}
}
