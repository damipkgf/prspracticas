/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: SharedVars.java
 * TIEMPO: 6h
 * DESCRIPCION: Implementacion del algoritmo Ricart-Agrawala mediante paso de mensajes.
 */

package practica7;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SharedVars {
	
	boolean reqcritsec = false;
	int highestsecnum = 0;
	int ourSecNum = 0;
	int outRepCount = 0;
	ArrayList<Integer> repDef = new ArrayList<Integer>();
	int me;
	int N = 3;
	Semaphore mutex = new Semaphore(1, true);
	Semaphore espera = new Semaphore(0, true);
	MessageSystem ms;
	
	SharedVars(MessageSystem _ms){
		ms = _ms;
	}
	
}
