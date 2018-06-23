/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Barca.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea procesos teleco e informatico de manera sincronizada cumpliendo condiciones
 * de barrera y de combinaciones de procesos que pueden subirse a la barca haciendo uso de paso de mensajes a servidores
 * para tratar con la memoria compartida.
 */

package practica6;

import java.util.Scanner;

public class Barca {

	/*
	 * Pide al usuario el siguiente elemento a añadir en la cola (teleco o
	 * informatico), crea un thread y lo ejecuta de forma asincrona.
	 * 
	 * @return void
	 */
	
	
	//los semaforos son las barreras y los mutex
	public static void main(String args[]) {
		Scanner entrada = new Scanner(System.in);
		Thread informatico, teleco;
		int pid = 1;
		
		Thread barreraInf = new Barrera(20);
		barreraInf.start();
		Thread barreraTel = new Barrera(21);
		barreraTel.start();
		
		Thread mut1 = new Mutex(22);
		mut1.start();
		Thread mut2 = new Mutex(23);
		mut2.start();

		while (true) {
			System.out.print("Indica el siguiente pasajero en la cola (0 inf o 1 tel): ");
			int s = entrada.nextInt();
			
			pid = (pid + 1)%18 + 1;
			
			if (s == 0) {
				informatico = new Inf(pid);
				informatico.start();
			} else {
				teleco = new Tel(pid);
				teleco.start();
			}
		}
	}
}
