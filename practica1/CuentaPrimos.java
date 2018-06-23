/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: CuentaPrimosThread.java
 * TIEMPO: 2
 * DESCRIPCION: Calculo del numero de primos que se encuentran hasta un numero
 * natural dado por el usuario utilizando un numero de threads tambien dado
 * por el usuario
 */
package practica1;

import java.util.Scanner;

public class CuentaPrimos {
	
	/*
	 * Crea tantos threads como se especifica en numThreads y divide el intervalo
	 * [2, num] en nThreads intervalos iguales asignando al ultimo el resto. Asigna
	 * a cada thread uno de estos intervalos y cuando todos han acabado recoge los
	 * resultados del numero de primos encontrados en cada intervalo y devuelve la
	 * suma de todas las contribuciones
	 * 
	 * @param int numThreads indica el numero de threads a crear
	 * 
	 * @param int num indica el numero hasta el que buscar primos
	 * 
	 * @return int devuelve el numero de primos encontrados hasta num
	 */
	public static int numPrimos(int numThreads, int num) {
		int primos = 0;
		Thread[] threadsPrimos = new Thread[numThreads];

		CuentaPrimosThread[] runnablesPrimos = new CuentaPrimosThread[numThreads];

		int resto = num % numThreads;
		int chunk = num / numThreads;
		int ini = 0;
		int fin = 0;

		for (int i = 0; i < numThreads; i++) {
			ini = ++fin;
			if (i < numThreads - 1) {
				fin = ini + chunk - 1;
			} else {
				fin = ini + chunk + resto - 1;
			}
			if (i == 0)
				ini = 2;
			runnablesPrimos[i] = new CuentaPrimosThread(ini, fin);
			threadsPrimos[i] = new Thread(runnablesPrimos[i]);
			threadsPrimos[i].start();
		}
		try {
			for (int i = 0; i < numThreads; i++) {
				threadsPrimos[i].join();
			}
		} catch (InterruptedException e) {
			System.err.println("Excepcion " + e);
		}

		for (int i = 0; i < numThreads; i++) {
			primos += runnablesPrimos[i].getResult();
		}

		return primos;
	}

	/*
	 * Pide al usuario numero de threads a utilizar y numero hasta el que buscar los
	 * primos, ejecuta la busqueda calculando el tiempo de ejecucion de esta y
	 * muestra los resultados por pantalla
	 * 
	 * @return void
	 */
	public static void main(String args[]) {
		int procesadores = Runtime.getRuntime().availableProcessors();
		System.out.print("Procesadores:" + procesadores + " Numero de threads:");
		Scanner entrada = new Scanner(System.in);
		int numThreads = Integer.parseInt(entrada.nextLine());

		System.out.print("Numero hasta el que buscar primos: ");
		int num = Integer.parseInt(entrada.nextLine());

		entrada.close();

		long Ti = System.currentTimeMillis();
		int primos = numPrimos(numThreads, num);
		long Tf = System.currentTimeMillis() - Ti;

		System.out.println("Tiempo en Milisegundos:" + Tf);

		System.out.println("Numero de primos = " + primos);
	}
}
