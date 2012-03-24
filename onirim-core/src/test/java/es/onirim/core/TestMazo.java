package es.onirim.core;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMazo {

	Mazo mazo = null;

	@Before
	public void init() {
		mazo = new Mazo();
		mazo.init();
	}

	@Test
	public void TestInit() {
		Assert.assertEquals("Numero de cartas inicial en el mazo", Mazo.getTotalCartas(), mazo.getNumCartas());
		Assert.assertEquals("Numero de pesadillas iniciales en el mazo", Mazo.getTotalPesadillas(), mazo.getNumPesadillas());
		Assert.assertEquals("Numero de puertas iniciales en el mazo", Mazo.getTotalPuertas(), mazo.getNumPuertas());
		Assert.assertEquals("Numero de habitaciones iniciales en el mazo", Mazo.getTotalHabitaciones(), mazo.getNumHabitaciones());
	}

	@Test
	public void TestRobar() {
		Carta carta = mazo.robar();
		Assert.assertNotNull("Carta robada", carta);
		Assert.assertTrue("Mazo decrece", mazo.getNumCartas()<Mazo.getTotalCartas());
		Assert.assertTrue("Mazo decrece de uno en uno", mazo.getNumCartas()==Mazo.getTotalCartas()-1);
	}

	@Test
	public void TestBarajar() {
		mazo.barajar();
		Assert.assertTrue("Mazo barajado", mazo.getNumCartas()==Mazo.getTotalCartas());
	}

	@Test
	public void TestRobarMano() {
		mazo.barajar();
		List<Carta> mano = mazo.robarMano();
		Assert.assertEquals("Tamano mano", Mazo.getTotalMano().intValue(), mano.size());
		Assert.assertEquals("Tamano mazo mas mano igual al total de cartas", Mazo.getTotalCartas().intValue(), mazo.getNumCartas()+mano.size());
	}
}
