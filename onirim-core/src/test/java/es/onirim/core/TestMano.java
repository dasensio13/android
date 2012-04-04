package es.onirim.core;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMano {

	Mazo mazo = null;
	Mano mano = null;

	@Before
	public void init() {
		mazo = new Mazo();
		mazo.init();
		mazo.barajar();
	}

	@Test
	public void TestConstructor() {
		mano = new Mano(mazo.robarMano());
		Assert.assertNotNull("Mano creada", mano);
		Assert.assertTrue("Mano completa", mano.isCompleta());
	}

	@Test
	public void TestRobarCompletar() {
		mano = new Mano(mazo.robarMano());
		mano.cogerCarta(3);
		Assert.assertFalse("Mano no completa", mano.isCompleta());
		int posicion = mano.rellenarMano(mazo.robar());
		Assert.assertTrue("Mano completa", mano.isCompleta());
		Assert.assertTrue("Posicion correcta", posicion == 3);
	}

	@Test
	public void descartar() {
		mano = new Mano(mazo.robarMano());
		Assert.assertTrue("Mano completa", mano.isCompleta());
		List<Carta> cartas = mano.descartar();
		Assert.assertTrue("Descartadas 5 cartas", cartas.size()==5);
		Assert.assertNull("Carta 1 nula", mano.getCarta(0));
		Assert.assertNull("Carta 2 nula", mano.getCarta(1));
		Assert.assertNull("Carta 3 nula", mano.getCarta(2));
		Assert.assertNull("Carta 4 nula", mano.getCarta(3));
		Assert.assertNull("Carta 5 nula", mano.getCarta(4));
	}
}
