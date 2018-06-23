/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Barrera.java
 * TIEMPO: 5h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca.
 */

package practica2;

import java.util.concurrent.Semaphore;

public class Barrera {
	int nTel, nInf, nTot;
	Semaphore colatel;
	Semaphore colainf;
	Semaphore mutex2;
	Semaphore mutex;

	public Barrera(Semaphore _colainf, Semaphore _colatel, Semaphore _mutex, Semaphore _mutex2, int _nInf, int _nTel,
			int _nTot) {
		mutex = _mutex;
		colatel = _colatel;
		colainf = _colainf;
		mutex2 = _mutex2;
		nTel = _nTel;
		nInf = _nInf;
		nTot = _nTot;
	}
}
