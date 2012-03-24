package es.onirim.core;

import java.util.ArrayList;
import java.util.List;

public class Limbo {

	private List<Carta> pila = new ArrayList<Carta>();

	public void insertar(Carta carta) {
		pila.add(carta);
	}

	public Carta robar() {
		if (pila.isEmpty()) {
			return null;
		} else {
			return pila.remove(0);
		}
	}

	public List<Carta> vaciar() {
		List<Carta> cartas = pila;
		pila = new ArrayList<Carta>();
		return cartas;
	}

	public boolean isEmpty() {
		return pila.isEmpty();
	}
}
