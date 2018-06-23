/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Babuino.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea threads babuino que comparten un recurso cuerda donde se ejecutan
 * o bloquean segun unas condiciones.
 */

package practica3;

import java.util.Scanner;

public class Babuino {
	public static void main(String args[]) {
		Scanner entrada = new Scanner(System.in);

		System.out.print("Indica el valor de K: ");
		int k = entrada.nextInt();

		Cuerda cuerda = new Cuerda(k);

		while (true) {
			System.out.print("Indica el siguiente babuino (0-derecha		1-izquierda): ");
			int s = entrada.nextInt();

			if (s == 0) {
				Thread babuino_derecha = new BabuinoDerecha(cuerda);
				babuino_derecha.start();
			} else {
				Thread babuino_izquierda = new BabuinoIzquierda(cuerda);
				babuino_izquierda.start();
			}
		}
	}
}
