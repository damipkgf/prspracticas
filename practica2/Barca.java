/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Barca.java
 * TIEMPO: 5h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca.
 */

package practica2;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Barca {

	/*
	 * Pide al usuario el siguiente elemento a añadir en la cola (teleco o
	 * informatico), crea un thread y lo ejecuta de forma asincrona.
	 * 
	 * @return void
	 */
	public static void main(String args[]) {
		Scanner entrada = new Scanner(System.in);
		Semaphore colatel = new Semaphore(0, true);
		Semaphore colainf = new Semaphore(0, true);
		Semaphore mutex = new Semaphore(1, true);
		Semaphore mutex2 = new Semaphore(1, true);
		Thread informatico, teleco;
		int nInf = 0, nTel = 0, nTot = 0;
		Barrera b = new Barrera(colainf, colatel, mutex, mutex2, nInf, nTel, nTot);

		while (true) {
			System.out.print("Indica el siguiente pasajero en la cola (0 inf o 1 tel): ");
			int s = entrada.nextInt();

			if (s == 0) {
				informatico = new Inf(b);
				informatico.start();
			} else {
				teleco = new Tel(b);
				teleco.start();
			}
		}
	}
}
