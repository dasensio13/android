package es.onirim.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Laberinto {

	private static final Log LOG = LogFactory.getLog(Laberinto.class);

	private final int SEGUIDAS_PUERTA = 3;
	private List<Carta> laberinto = new ArrayList<Carta>();
	private int numIgualesSeguidas = 0;

	public boolean puertaConseguida() {
		return numIgualesSeguidas >= SEGUIDAS_PUERTA;
	}

	public void resetNumSeguidas() {
		numIgualesSeguidas = 0;
	}

	public void insertar(Carta carta) {
		if (puedoInsertarCarta(carta)) {
			laberinto.add(carta);
			aumentarSeguidas();
		} else {
			throw new RuntimeException("No se puede insertar la carta en el laberinto");
		}
	}

	private void aumentarSeguidas() {
		if (dosUltimasCartasMismoColor()) {
			numIgualesSeguidas++;
		} else {
			numIgualesSeguidas = 1;
		}
	}

	private boolean dosUltimasCartasMismoColor() {
		if (laberinto.size()>=2) {
			Carta ultima = getUltimaCarta();
			Carta penultima = getPenultimaCarta();
			return ultima.getColor().equals(penultima.getColor());
		} else {
			return true;
		}
	}

	public Carta getPenultimaCarta() {
		return laberinto.get(laberinto.size()-2);
	}

	public Carta getUltimaCarta() {
		return laberinto.get(laberinto.size()-1);
	}

	public boolean puedoInsertarCarta(Carta carta) {
		boolean sePuede = false;
		if (carta!=null && carta.isLaberinto()) {
			if (laberinto.size() > 0) {
				Carta ultimaCarta = getUltimaCarta();
				if (carta.getSimbolo().equals(ultimaCarta.getSimbolo())) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Ultima carta del laberinto del mismo tipo que la que se intenta insertar");
					}
					sePuede = false;
				} else {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Ultima carta del laberinto de distinto tipo que la que se intenta insertar");
					}
					sePuede = true;
				}
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Laberinto vacio");
				}
				sePuede = true;
			}
		} else {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Carta que no es laberinto");
			}
			sePuede = false;
		}
		return sePuede;
	}

	public List<Carta> getLaberinto() {
		return laberinto;
	}

	public void setLaberinto(List<Carta> laberinto) {
		this.laberinto = laberinto;
	}
}
