package es.onirim.core;

import java.util.ArrayList;
import java.util.List;

import es.onirim.core.Carta.Color;

public class PuertasConseguidas {

	private static final Integer TOTAL_PUERTAS_COLOR = 2;
	private static final Integer TOTAL_PUERTAS = 8;

	private List<Carta> puertas = new ArrayList<Carta>();

	public void insertarPuerta(Carta carta) {
		if (carta.isPuerta()) {
			puertas.add(carta);
		} else {
			throw new RuntimeException("No s epuede meter una carta que no sea puerta");
		}
	}

	public int numPuertas() {
		return puertas.size();
	}

	public int numPuertas(Color color) {
		int numPuertas = 0;
		for (Carta carta : puertas) {
			if (isPuerta(carta, color)) {
				numPuertas++;
			}
		}
		return numPuertas;
	}

	private boolean isPuerta(Carta carta, Color color) {
		return carta.isPuerta() && carta.isColor(color);
	}

	public boolean isVictoria() {
		boolean victoria = numPuertas() == TOTAL_PUERTAS
			&& numPuertas(Color.VERDE) == TOTAL_PUERTAS_COLOR
			&& numPuertas(Color.ROJO) == TOTAL_PUERTAS_COLOR
			&& numPuertas(Color.AZUL) == TOTAL_PUERTAS_COLOR
			&& numPuertas(Color.MARRON) == TOTAL_PUERTAS_COLOR;
		return victoria;
	}

	public List<Carta> getPuertas() {
		return puertas;
	}
}
