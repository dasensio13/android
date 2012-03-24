package es.onirim.core;

import java.util.ArrayList;
import java.util.List;

public class PilaDescartes {

	private List<Carta> pila = new ArrayList<Carta>();

	public void insertar(Carta carta) {
		pila.add(carta);
	}
	
	public void insertar(List<Carta> cartas) {
		pila.addAll(cartas);
	}

	public List<Carta> getPila() {
		return pila;
	}

	public Carta getUltimaCarta() {
		Carta carta = null;
		if (!pila.isEmpty()) {
			carta = pila.get(pila.size()-1);
		}
		return carta;
	}
	
}
