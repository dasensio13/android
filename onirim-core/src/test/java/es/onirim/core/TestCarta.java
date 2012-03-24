package es.onirim.core;

import org.junit.Assert;
import org.junit.Test;

import es.onirim.core.Carta.Color;
import es.onirim.core.Carta.Simbolo;
import es.onirim.core.Carta.Tipo;

public class TestCarta {

	Carta pesadilla = new Carta(Tipo.PESADILLA, null, null);
	Carta puertaVerde = new Carta(Tipo.PUERTA, Color.VERDE, null);
	Carta puertaRoja = new Carta(Tipo.PUERTA, Color.ROJO, null);
	Carta otraPuertaRoja = new Carta(Tipo.PUERTA, Color.ROJO, null);
	Carta laberintoLunaRojo = new Carta(Tipo.LABERINTO, Color.ROJO, Simbolo.LUNA);
	Carta laberintoLlaveRojo = new Carta(Tipo.LABERINTO, Color.ROJO, Simbolo.LLAVE);
	Carta laberintoSolRojo = new Carta(Tipo.LABERINTO, Color.ROJO, Simbolo.SOL);
	Carta otralaberintoLunaRojo = new Carta(Tipo.LABERINTO, Color.ROJO, Simbolo.LUNA);
	

	@Test
	public void testEquals() {
		Assert.assertTrue("Puertas rojas iguales", puertaRoja.equals(otraPuertaRoja));
		Assert.assertTrue("Lunas rojas iguales", laberintoLunaRojo.equals(otralaberintoLunaRojo));
		Assert.assertFalse("Pesadilla diferente a puerta verde", pesadilla.equals(puertaVerde));
		Assert.assertFalse("Luna Roja diferente a sol rojo", laberintoLunaRojo.equals(laberintoSolRojo));
	}
}
