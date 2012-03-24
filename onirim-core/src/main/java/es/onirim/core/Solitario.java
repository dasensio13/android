package es.onirim.core;

import es.onirim.core.Carta.Color;
import es.onirim.core.Carta.Tipo;

public class Solitario {

	private Mazo mazo = null;
	private Laberinto laberinto = null;
	private Limbo limbo = null;
	private PilaDescartes descartes = null;
	private PuertasConseguidas puertas = null;
	private Mano mano = null;

	public Solitario() {
		super();
		mazo = new Mazo();
		mazo.init();
		mazo.barajar();

		laberinto = new Laberinto();
		limbo = new Limbo();
		descartes = new PilaDescartes();
		puertas = new PuertasConseguidas();
		mano = new Mano(mazo.robarMano());
	}

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public Laberinto getLaberinto() {
		return laberinto;
	}

	public void setLaberinto(Laberinto laberinto) {
		this.laberinto = laberinto;
	}

	public Limbo getLimbo() {
		return limbo;
	}

	public void setLimbo(Limbo limbo) {
		this.limbo = limbo;
	}

	public PilaDescartes getDescartes() {
		return descartes;
	}

	public void setDescartes(PilaDescartes descartes) {
		this.descartes = descartes;
	}

	public PuertasConseguidas getPuertas() {
		return puertas;
	}

	public void setPuertas(PuertasConseguidas puertas) {
		this.puertas = puertas;
	}

	public Mano getMano() {
		return mano;
	}

	public void setMano(Mano mano) {
		this.mano = mano;
	}

	public Carta robarPuerta(Color color) {
		Carta c = new Carta(Tipo.PUERTA, color, null);
		return mazo.robar(c);
	}
}
