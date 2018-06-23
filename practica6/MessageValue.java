/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: MessageValue.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca haciendo uso de paso de mensajes a servidores
 * para tratar con la memoria compartida.
 */

package practica6;

import java.io.Serializable;

public class MessageValue implements Serializable {
	private static final long serialVersionUID = 1L;

	private int nTel, nInf;
	private boolean wait;
	private boolean tot;

	public MessageValue(int _nInf, int _nTel, boolean _wait, boolean _tot) {
		nInf = _nInf;
		nTel = _nTel;
		wait = _wait;
		tot = _tot;
	}

	public boolean getTot() {
		return tot;
	}
	
	public boolean getWait() {
		return wait;
	}
	
	public int getValueInf() {
		return nInf;
	}
	
	public int getValueTel() {
		return nTel;
	}

	public String toString() {
		return "Asignar valor";
	}
}
