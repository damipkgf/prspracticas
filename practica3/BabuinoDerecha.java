/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: BabuinoDerecha.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea threads babuino que comparten un recurso cuerda donde se ejecutan
 * o bloquean segun unas condiciones.
 */

package practica3;

public class BabuinoDerecha extends Thread {

	Cuerda cuerda;

	BabuinoDerecha(Cuerda c) {
		cuerda = c;
	}

	public void run() {
		cuerda.entrardcha();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cuerda.salirizq();
	}
}
