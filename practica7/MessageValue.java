/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: MessageSystem.java
 * TIEMPO: 6h
 * DESCRIPCION: Implementacion del algoritmo Ricart-Agrawala mediante paso de mensajes.
 */

package practica7;

import java.io.Serializable;

public class MessageValue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	boolean request;
	int ourSecNum;
	int remitente;

	public MessageValue(boolean _request, int _ourSecNum, int _remitente) {
		request = _request;
		ourSecNum = _ourSecNum;
		remitente = _remitente;
	}
	
	public boolean getRequest() {
		return request;
	}
	
	public int getOurSecNum() {
		return ourSecNum;
	}
	
	public int getRemitente() {
		return remitente;
	}

}
