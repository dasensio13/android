package es.onirim.core;

public class Carta {
	public enum Tipo {
		PESADILLA, PUERTA, LABERINTO
	}

	public enum Color {
		ROJO, VERDE, AZUL, MARRON
	}

	public enum Simbolo {
		SOL, LUNA, LLAVE
	}

	private final Tipo tipo;
	private final Color color;
	private final Simbolo simbolo;

	public Carta(Tipo tipo, Color color, Simbolo simbolo) {
		this.tipo = tipo;
		this.color = color;
		this.simbolo = simbolo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Color getColor() {
		return color;
	}

	public Simbolo getSimbolo() {
		return simbolo;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder().append(tipo);
		if (isPuerta() || isLaberinto()) {
			result.append(" ").append(color);
		}
		if (isLaberinto()) {
			result.append(" ").append(simbolo);
		}
		return result.toString();
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof Carta) {
			Carta oCarta = (Carta) o;
			result = this.getTipo().equals(oCarta.getTipo());
			result = result && (this.getSimbolo()==null || this.getSimbolo().equals(oCarta.getSimbolo()));
			result = result && (this.getColor()==null || this.getColor().equals(oCarta.getColor()));
		}
		return result;
	}

	public boolean isPesadilla() {
		return Tipo.PESADILLA.equals(tipo);
	}

	public boolean isPuerta() {
		return Tipo.PUERTA.equals(tipo);
	}

	public boolean isLaberinto() {
		return Tipo.LABERINTO.equals(tipo);
	}

	public boolean isSol() {
		return Simbolo.SOL.equals(simbolo);
	}

	public boolean isLuna() {
		return Simbolo.LUNA.equals(simbolo);
	}

	public boolean isLlave() {
		return Simbolo.LLAVE.equals(simbolo);
	}

	public boolean isColor(Color color) {
		return color.equals(this.color);
	}
}