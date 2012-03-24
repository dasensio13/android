package es.onirim.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.onirim.core.Carta.Color;
import es.onirim.core.Carta.Simbolo;
import es.onirim.core.Carta.Tipo;

public class TestLaberinto {

	Laberinto laberinto = null;

	@Before
	public void init() {
		laberinto = new Laberinto();
	}

	@Test
	public void testInsertar() {
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.LUNA));
		Assert.assertTrue("Carta laberinto insertada", laberinto.getUltimaCarta().isLaberinto());
		Assert.assertEquals("Carta azul insertada", laberinto.getUltimaCarta().getColor(), Color.AZUL);
		Assert.assertTrue("Carta luna insertada", laberinto.getUltimaCarta().isLuna());
	}

	@Test
	public void testInsertarPesadilla() {
		try {
			laberinto.insertar(new Carta(Tipo.PESADILLA, null, null));
		} catch (Exception e){
			Assert.assertTrue("Pesadilla no se inserta en laberinto", true);
		}
	}

	@Test
	public void testInsertarMismoSimbolo() {
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.LUNA));
		try {
			laberinto.insertar(new Carta(Tipo.LABERINTO, Color.ROJO, Simbolo.LUNA));
		} catch (Exception e){
			Assert.assertTrue("No se pueden insertar dos simbolos iguales seguidos", true);
		}
	}

	@Test
	public void testUltimaCarta() {
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.ROJO, Simbolo.LLAVE));
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.LUNA));
		Assert.assertTrue("Carta laberinto insertada", laberinto.getUltimaCarta().isLaberinto());
		Assert.assertEquals("Carta azul insertada", laberinto.getUltimaCarta().getColor(), Color.AZUL);
		Assert.assertTrue("Carta luna insertada", laberinto.getUltimaCarta().isLuna());
	}

	@Test
	public void testConseguirPuerta() {
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.LUNA));
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.LLAVE));
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.SOL));
		Assert.assertTrue("Puerta conseguida", laberinto.puertaConseguida());
		laberinto.resetNumSeguidas();
		Assert.assertFalse("Numero seguidas reiniciado", laberinto.puertaConseguida());
	}

	@Test
	public void testNoConseguirPuerta() {
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.LUNA));
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.ROJO, Simbolo.LLAVE));
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.SOL));
		Assert.assertFalse("Numero seguidas reiniciado", laberinto.puertaConseguida());
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.LLAVE));
		laberinto.insertar(new Carta(Tipo.LABERINTO, Color.AZUL, Simbolo.SOL));
		Assert.assertTrue("Puerta conseguida", laberinto.puertaConseguida());
	}
}
