package es.onirim.core;

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
}
