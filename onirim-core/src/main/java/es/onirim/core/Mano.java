package es.onirim.core;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.onirim.core.Carta.Color;

public class Mano {

	private static final Log LOG = LogFactory.getLog(Mano.class);

	private Carta[] mano = new Carta[5];

	public Mano(List<Carta> cartas) {
		for (Carta carta : cartas) {
			rellenarMano(carta);
		}
		if (!isCompleta()) {
			throw new RuntimeException("Mano no completa");
		}
	}

	public int rellenarMano(Carta carta) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Insertamos carta en la mano: " + carta);
		}
		boolean colocada = false;
		int i;
		for (i=0; i<mano.length && !colocada; i++) {
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
		} else {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Posicion final: " + i);
			}
			return i-1;
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

	public boolean containsLlave() {
		for (Carta carta : getCartas()) {
			if (carta.isLlave()) {
				return true;
			}
		}
		return false;
	}

	public boolean containsLlave(Color color) {
		for (Carta carta : getCartas()) {
			if (carta!=null && carta.isLlave() && carta.isColor(color)) {
				return true;
			}
		}
		return false;
	}

	public Carta cogerCarta(Carta carta) {
		int index = getIndexOf(carta);
		if (index>=0 && index<mano.length) {
			return cogerCarta(index);
		} else {
			return null;
		}
	}

	private int getIndexOf(Carta carta) {
		for (int i=0; i<mano.length; i++) {
			if (mano[i]!=null && mano[i].equals(carta)) {
				return i;
			}
		}
		return -1;
	}

	public List<Carta> descartar() {
		List<Carta> cartas = getCartas();
		mano = new Carta[5];
		return cartas;
	}
}
