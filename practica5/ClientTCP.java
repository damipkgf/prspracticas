/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: ClientTCP
 * TIEMPO: 10h
 * DESCRIPCI'ON: Este programa implementa un intercambio de mensajes mediante sockets TCP donde la estructura de
 * campos de dichos mensajes puede variarse. Sigue una topologia cliente-servidor donde el cliente manda una peticion
 * al servidor indicando un intervalo sobre el que calcular los numeros primos contenidos en el.
 */

package practica5;

public class ClientTCP {

	public static void main(String args[]) throws Exception {
		
		
		HandleMessage msg = new HandleMessage(1, 100000000, "0.0.0.0", 2000);

		int[] primos = msg.getPrimeNumbers();

		for (int i = 0; i < primos.length; i++)
			System.out.print(primos[i] + " ");
	}
}
