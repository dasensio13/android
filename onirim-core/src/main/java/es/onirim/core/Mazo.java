package es.onirim.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.onirim.core.Carta.Color;
import es.onirim.core.Carta.Simbolo;
import es.onirim.core.Carta.Tipo;

public class Mazo {

	private static final Log LOG = LogFactory.getLog(Mazo.class);

	private static final Integer TOTAL_PUERTAS = 8;
	private static final Integer TOTAL_PESADILLAS = 10;
	private static final Integer TOTAL_HABITACIONES = 58;
	private static final Integer TOTAL_PUERTAS_COLOR = 2;

	private static final Integer TOTAL_SOLES_ROJO = 9;
	private static final Integer TOTAL_SOLES_VERDE = 7;
	private static final Integer TOTAL_SOLES_AZUL = 8;
	private static final Integer TOTAL_SOLES_MARRON = 6;

	private static final Integer TOTAL_LUNAS_COLOR = 4;
	private static final Integer TOTAL_LLAVES_COLOR = 3;

	private static final Integer TOTAL_MANO = 5;

	private List<Carta> mazo = null;

	public Integer getNumCartas() {
		Integer numCartas = mazo.size();
		if (LOG.isDebugEnabled()) {
			LOG.debug(numCartas + " cartas en el mazo");
		}
		return numCartas;
	}

	public Integer getNumPesadillas() {
		int numPesadillas = 0;
		for (Carta carta : mazo) {
			if (carta.isPesadilla()) {
				numPesadillas++;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(numPesadillas + " pesadillas en el mazo");
		}
		return numPesadillas;
	}

	public Integer getNumPuertas() {
		int numPuertas = 0;
		for (Carta carta : mazo) {
			if (carta.isPuerta()) {
				numPuertas++;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(numPuertas + " puertas en el mazo");
		}
		return numPuertas;
	}

	public Integer getNumHabitaciones() {
		int numHabitaciones = 0;
		for (Carta carta : mazo) {
			if (carta.isLaberinto()) {
				numHabitaciones++;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(numHabitaciones + " habitaciones en el mazo");
		}
		return numHabitaciones;
	}

	public void barajar() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Barajamos el mazo");
		}
		for (int primera = 0; primera < mazo.size(); primera++) {
			int segunda = (int) (Math.random() * mazo.size());
			Carta temp = mazo.get(primera);
			mazo.set(primera, mazo.get(segunda));
			mazo.set(segunda, temp);
		}
	}

	public Carta robar(Carta carta) {
		if (!mazo.isEmpty()) {
			int index = mazo.indexOf(carta);
			return index>=0 ? mazo.remove(index) : null;
		} else {
			throw new RuntimeException("Mazo vacio");
		}
	}

	public Carta robar() {
		if (!mazo.isEmpty()) {
			Carta carta = mazo.remove(0);
			if (LOG.isDebugEnabled()) {
				LOG.debug("Carta robada del mazo: " + carta);
			}
			return carta;
		} else {
			throw new RuntimeException("Mazo vacio");
		}
	}

	public List<Carta> robarMano() {
		List<Carta> mano = new ArrayList<Carta>();
		List<Carta> limbo = new ArrayList<Carta>();
		Carta carta = null;
		while (mano.size()<TOTAL_MANO) {
			carta = robar();
			if (carta.isLaberinto()) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Carta en mano: " + carta);
				}
				mano.add(carta);
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Carta al limbo: " + carta);
				}
				limbo.add(carta);
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Mano robada: " + mano);
		}
		if (!limbo.isEmpty()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Hay cartas en el limbo: " + limbo);
			}
			mazo.addAll(limbo);
			barajar();
		}
		return mano;
	}

	public void init() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Insertamos cartas para crear el mazo");
		}
		mazo = new ArrayList<Carta>();
		insertPesadillas(mazo);
		for (Color color : Color.values()) {
			insertPuertas(mazo, color);
			insertLaberinto(mazo, color);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Mazo creado. Tamaño: " + mazo.size());
		}
	}

	public void insertarEncima(List<Carta> cartas) {
		mazo.addAll(0, cartas);
	}

	private void insertLaberinto(List<Carta> mazo, Color color) {
		insertLunas(mazo, color);
		insertLlaves(mazo, color);
		insertSoles(mazo, color);
	}

	private void insertSoles(List<Carta> mazo, Color color) {
		Integer numSoles = getTotalSoles(color);
		for (int i=0; i<numSoles; i++) {
			mazo.add(new Carta(Tipo.LABERINTO, color, Simbolo.SOL));
		}
	}

	private void insertLlaves(List<Carta> mazo, Color color) {
		for (int i=0; i<TOTAL_LLAVES_COLOR; i++) {
			mazo.add(new Carta(Tipo.LABERINTO, color, Simbolo.LLAVE));
		}
	}

	private void insertLunas(List<Carta> mazo, Color color) {
		for (int i=0; i<TOTAL_LUNAS_COLOR; i++) {
			mazo.add(new Carta(Tipo.LABERINTO, color, Simbolo.LUNA));
		}
	}

	private Integer getTotalSoles(Color color) {
		switch (color) {
		case ROJO:
			return TOTAL_SOLES_ROJO;
		case VERDE:
			return TOTAL_SOLES_VERDE;
		case AZUL:
			return TOTAL_SOLES_AZUL;
		case MARRON:
			return TOTAL_SOLES_MARRON;
		default:
			return 0;
		}
	}

	private void insertPuertas(List<Carta> mazo, Color color) {
		for (int i=0; i<TOTAL_PUERTAS_COLOR; i++) {
			mazo.add(new Carta(Tipo.PUERTA, color, null));
		}
	}

	private void insertPesadillas(List<Carta> mazo) {
		for (int i=0; i<TOTAL_PESADILLAS; i++) {
			mazo.add(new Carta(Tipo.PESADILLA, null, null));
		}
	}

	public static Integer getTotalPuertas() {
		return TOTAL_PUERTAS;
	}

	public static Integer getTotalPesadillas() {
		return TOTAL_PESADILLAS;
	}

	public static Integer getTotalHabitaciones() {
		return TOTAL_HABITACIONES;
	}

	public static Integer getTotalCartas() {
		return TOTAL_PUERTAS+TOTAL_PESADILLAS+TOTAL_HABITACIONES;
	}

	public static Integer getTotalMano() {
		return TOTAL_MANO;
	}
}
