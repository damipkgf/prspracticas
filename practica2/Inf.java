/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Inf.java
 * TIEMPO: 5h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca.
 */

package practica2;

public class Inf extends Thread {
	Barrera barrera;

	Inf(Barrera b) {
		barrera = b;
	}

	public void row() {
		System.out.println("Remando...");
	}

	public void board() {
		System.out.println("Embarcando informatico...");
	}

	public void run() {
		try {
			barrera.mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		barrera.nInf++;

		if (barrera.nInf == 2 && barrera.nTel >= 2) {
			barrera.nInf -= 2;
			barrera.nTel -= 2;
			barrera.mutex.release();
			
			barrera.colainf.release();
			barrera.colatel.release(2);
			//barrera.colatel.release();

		} else if (barrera.nInf == 4) {
			barrera.nInf -= 4;
			barrera.mutex.release();

			barrera.colainf.release(3);
			//barrera.colainf.release();
			//barrera.colainf.release();

		} else {
			barrera.mutex.release();
			
			try {
				barrera.colainf.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		board();
		try {
			barrera.mutex2.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		barrera.nTot++;
		if (barrera.nTot == 4) {
			barrera.nTot = 0;
			row();
		}
		barrera.mutex2.release();
	}
}
