/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Tel.java
 * TIEMPO: 5h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca.
 */

package practica2;

public class Tel extends Thread {
	Barrera barrera;

	Tel(Barrera b) {
		barrera = b;
	}

	public void row() {
		System.out.println("Remando...");
	}

	public void board() {
		System.out.println("Embarcando teleco...");
	}

	public void run() {

		try {
			barrera.mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		barrera.nTel++;

		if (barrera.nTel == 2 && barrera.nInf >= 2) {
			barrera.nTel -= 2;
			barrera.nInf -= 2;
			barrera.mutex.release();

			barrera.colatel.release();
			barrera.colainf.release(2);
			//barrera.colainf.release();

		} else if (barrera.nTel == 4) {
			barrera.nTel -= 4;
			barrera.mutex.release();

			barrera.colatel.release(3);
			//barrera.colatel.release();
			//barrera.colatel.release();

		} else {
			barrera.mutex.release();

			try {
				barrera.colatel.acquire();
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
