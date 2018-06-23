/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: ClientMsg
 * TIEMPO: 10h
 * DESCRIPCI'ON: Este programa implementa un intercambio de mensajes mediante sockets TCP donde la estructura de
 * campos de dichos mensajes puede variarse. Sigue una topologia cliente-servidor donde el cliente manda una peticion
 * al servidor indicando un intervalo sobre el que calcular los numeros primos contenidos en el.
 */

package practica5;

public class ClientMsg {
	private int _id = 0;
	private int _finPrimos;
	private int _iniPrimos;
	private int _numPrimos;
	private int[] primos;

	public int getID() {
		return _id;
	}

	public int[] getPrimos() {
		return primos;
	}

	public void setPrimo(int i, int primo) {
		primos[i] = primo;
	}

	public void setID(int id) {
		_id = id;
	}

	public int getIni() {
		return _iniPrimos;
	}

	public void setIni(int ini) {
		_iniPrimos = ini;
	}

	public void setFin(int fin) {
		_finPrimos = fin;
	}

	public int getFin() {
		return _finPrimos;
	}

	public int getNum() {
		return _numPrimos;
	}

	public void setNum(int numPrimos) {
		_numPrimos = numPrimos;
		primos = new int[numPrimos];
	}

	public ClientMsg() throws IllegalArgumentException {

	}
}
