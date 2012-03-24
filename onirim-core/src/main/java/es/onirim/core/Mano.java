package es.onirim.core;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Mano {

	private static final Log LOG = LogFactory.getLog(Mano.class);

	private Carta[] mano = new Carta[5];
	private int selectedIndex = -1;

	public Mano(List<Carta> cartas) {
		for (Carta carta : cartas) {
			rellenarMano(carta);
		}
		if (!isCompleta()) {
			throw new RuntimeException("Mano no completa");
		}
	}

	public void rellenarMano(Carta carta) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Insertamos carta en la mano: " + carta);
		}
		boolean colocada = false;
		for (int i=0; i<mano.length && !colocada; i++) {
			if (mano[i]==null) {
				mano[i] = carta;
				colocada = true;
				if (LOG.isDebugEnabled()) {
					LOG.debug("Carta en posicion " + i);
				}
			}
		}
		if (!colocada) {
			throw new RuntimeException("Se ha intentado rellenar una mano ya completa");
		}
	}

	public boolean isCompleta() {
		boolean completa = true;
		for (int i=0; i<mano.length; i++) {
			if (mano[i]==null) {
				completa = false;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Mano completa? " + completa);
		}
		return completa;
	}

	public void seleccionar(int index) {
		if (index>0 && index<mano.length) {
			selectedIndex = index;
			if (LOG.isDebugEnabled()) {
				LOG.debug("Carta seleccionada: " + index);
			}
		} else {
			throw new RuntimeException("Se ha intentado seleccionar una carta fuera de rango: " + index);
		}
	}

	public void deseleccionar() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Ninguna carta seleccionada");
		}
		selectedIndex = -1;
	}

	public Carta cogerCartaSeleccionada() {
		return cogerCarta(selectedIndex);
	}

	public Carta getCarta(int index) {
		Carta carta = null;
		if (index>=0 && index<mano.length) {
			carta = mano[index];
		} else if (LOG.isDebugEnabled()) {
			LOG.debug("Indice fuera de rango al coger carta: " + index);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Carta obtenida: " + carta);
		}
		return carta;
	}

	public Carta cogerCarta(int index) {
		Carta carta = null;
		if (index>=0 && index<mano.length) {
			carta = mano[index];
			mano[index] = null;
		} else if (LOG.isDebugEnabled()) {
			LOG.debug("Indice fuera de rango al coger carta: " + index);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Carta extraida: " + carta);
		}
		return carta;
	}

	public List<Carta> getCartas() {
		return Arrays.asList(mano);
	}
}
