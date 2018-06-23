/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: Cuerda.java
 * TIEMPO: 10h
 * DESCRIPCION: Programa concurrente que crea threads babuino que comparten un recurso cuerda donde se ejecutan
 * o bloquean segun unas condiciones.
 */

package practica3;

import java.util.LinkedList;

public class Cuerda {

	private int cuerdadcha = 0;
	private int cuerdaizq = 0;
	private int barreraizq = 0;
	private int barreradcha = 0;
	private int bordeizq = 0;
	private int bordedcha = 0;
	private int k;

	private LinkedList<Long> coladcha = new LinkedList<Long>();
	private LinkedList<Long> colaizq = new LinkedList<Long>();

	Cuerda(int j) {
		k = j;
	}

	synchronized public void entrardcha() {

		bordedcha++;

		coladcha.add(Thread.currentThread().getId());

		while ((cuerdaizq > 0) || (cuerdadcha == 5) || (barreradcha == k)
				|| coladcha.getFirst().longValue() != Thread.currentThread().getId()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		coladcha.remove();

		if (bordeizq > 0) {
			barreradcha++;
		}

		bordedcha--;
		cuerdadcha++;
		if (barreradcha == k || bordeizq == 0) {
			barreraizq = 0;
		}
	}

	synchronized public void entrarizq() {

		bordeizq++;

		colaizq.add(Thread.currentThread().getId());

		while ((cuerdadcha > 0) || (cuerdaizq == 5) || (barreraizq == k)
				|| colaizq.getFirst().longValue() != Thread.currentThread().getId()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		colaizq.remove();

		if (bordedcha > 0) {
			barreraizq++;
		}

		bordeizq--;
		cuerdaizq++;
		barreradcha = 0;

		if (barreraizq == k || bordedcha == 0) {
			barreradcha = 0;
		}
	}

	synchronized public void salirdcha() {
		cuerdaizq--;
		notifyAll();
		System.out.println(cuerdadcha + "Babuino sale por la derecha");
	}

	synchronized public void salirizq() {
		cuerdadcha--;
		notifyAll();
		System.out.println(cuerdaizq + "Babuino sale por la izquierda");
	}
}
