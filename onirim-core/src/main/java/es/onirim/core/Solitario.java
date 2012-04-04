package es.onirim.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.onirim.core.Carta.Color;
import es.onirim.core.Carta.Tipo;

public class Solitario {

	private static final Log LOG = LogFactory.getLog(Solitario.class);

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

	public Carta robarPuerta(Color color) {
		Carta c = new Carta(Tipo.PUERTA, color, null);
		return mazo.robar(c);
	}

	public List<Carta> getCartasMano() {
		return mano.getCartas();
	}

	public Carta cogerCartaMano(int index) {
		return mano.cogerCarta(index);
	}

	public Carta getCartaMano(int index) {
		return mano.getCarta(index);
	}

	public boolean puedoInsertarCartaLaberinto(Carta carta) {
		return laberinto.puedoInsertarCarta(carta);
	}

	public void insertarCartaLaberinto(Carta carta) {
		laberinto.insertar(carta);
	}

	public Carta getUltimaCartaLaberinto() {
		return laberinto.getUltimaCarta();
	}

	public int getTamanoLaberinto() {
		return laberinto.getLaberinto().size();
	}

	public boolean puertaConseguida() {
		return laberinto.puertaConseguida();
	}

	public void resetNumSeguidasPuerta() {
		laberinto.resetNumSeguidas();
	}

	public Color getColorUltimaCartaLaberinto() {
		return laberinto.getUltimaCarta().getColor();
	}

	public void insertarPuerta(Carta puerta) {
		if (puerta!=null) {
			puertas.insertarPuerta(puerta);
		}
	}

	public boolean isVictoria() {
		return puertas.isVictoria();
	}

	public int getNumPuertas() {
		return puertas.numPuertas();
	}

	public void descartar(Carta carta) {
		descartes.insertar(carta);
	}

	public Carta getUltimaCartaDescartes() {
		return descartes.getUltimaCarta();
	}

	public int getTamanoDescartes() {
		return descartes.getPila().size();
	}

	public boolean isManoCompleta() {
		return mano.isCompleta();
	}

	public Carta robarMazo() {
		Carta carta = null;
		if (isFinal()) {
			LOG.warn("No robamos carta porque el juego ha terminado");
		} else {
			carta = mazo.robar();
		}
		return carta;
	}

	public boolean isFinal() {
		return isVictoria() || isDerrota();
	}

	public int rellenarMano(Carta carta) {
		return mano.rellenarMano(carta);
	}

	public void insertarLimbo(Carta carta) {
		limbo.insertar(carta);
	}

	public boolean isLimboEmpty() {
		return limbo.isEmpty();
	}

	public List<Carta> vaciarLimbo() {
		return limbo.vaciar();
	}

	public void insertarMazo(List<Carta> cartas) {
		mazo.insertarEncima(cartas);
	}

	public void barajarMazo() {
		mazo.barajar();
	}

	public Integer getNumCartasMazo() {
		return mazo.getNumCartas();
	}

	public boolean isDerrota() {
		return mazo.getNumHabitaciones()==0;
	}

	public List<Carta> getDescartes() {
		return descartes.getPila();
	}

	public List<Carta> getPuertasObtenidas() {
		return puertas.getPuertas();
	}

	public List<Carta> getCartasLaberinto() {
		return laberinto.getLaberinto();
	}

	public boolean tieneLlaveMano() {
		return mano.containsLlave();
	}

	public boolean tieneLlaveMano(Color color) {
		return mano.containsLlave(color);
	}

	public Carta cogerCartaMano(Carta carta) {
		return mano.cogerCarta(carta);
	}

	public List<Carta> descartarMano() {
		return mano.descartar();
	}

	public List<Carta> cogerCartasMazo(int numCartas) {
		List<Carta> cartas = new ArrayList<Carta>();
		for (int i=0; i<numCartas; i++) {
			cartas.add(mazo.robar());
		}
		return cartas;
	}
}
