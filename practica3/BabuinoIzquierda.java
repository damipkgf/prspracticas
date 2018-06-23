/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: BabuinoIzquierda.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea threads babuino que comparten un recurso cuerda donde se ejecutan
 * o bloquean segun unas condiciones.
 */

package practica3;

public class BabuinoIzquierda extends Thread {

	Cuerda cuerda;

	BabuinoIzquierda(Cuerda c) {
		cuerda = c;
	}

	public void run() {
		cuerda.entrarizq();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cuerda.salirdcha();
	}
}
