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

public class CuentaPrimosThread implements Runnable {

	private int _ini;
	private int _fin;
	private int _nPrimos = 0;

	/*
	 * Define el constructor de objetos CuentaPrimosThread especificandole el valor
	 * de los atributos inicio y fin
	 * 
	 * @param int ini indica el inicio del intervalo en el que buscar primos
	 * 
	 * @param int ini indica el final del intervalo en el que buscar primos
	 */
	public CuentaPrimosThread(int ini, int fin) {
		_ini = ini;
		_fin = fin;
	}

	/*
	 * Devuelve el numero de primos encontrados que se encuentra en memoria local
	 * 
	 * @return int indica el numero de primos encontrados en el intervalo
	 */
	public int getResult() {
		return _nPrimos;
	}

	/*
	 * Devuelve la informacion acerca de si un numero indicado como parametro es
	 * primo o no
	 * 
	 * @param int num indica el numero que se desea conocer si es primo o no
	 * 
	 * @return boolean indica si el numero pasado como argumento es primo o no
	 */
	private boolean esPrimo(int num) {
		int lim = (int) Math.sqrt(num);
		for (int i = 2; i <= lim; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	/*
	 * Ejecuta la busqueda de numeros primos dentro de un intervalo y actualiza el
	 * valor de la variable _nPrimos cada vez que encuentra uno
	 * 
	 * @return void
	 */
	public void run() {
		for (int i = _ini; i <= _fin; i++) {
			if (esPrimo(i)) {
				_nPrimos++;
			}
		}
	}
}
