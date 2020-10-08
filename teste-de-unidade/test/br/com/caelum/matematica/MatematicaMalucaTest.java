package br.com.caelum.matematica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class MatematicaMalucaTest {

	@Test
	public void deveMultiplicarNumerosMaioresQue30() {

		MatematicaMaluca math = new MatematicaMaluca();
		System.out.println(math.contaMaluca(50));
		assertEquals(50 * 4, math.contaMaluca(50));
	}

	@Test
	public void deveMultiplicarNumerosMaioresQue10MenoresQue30() {

		MatematicaMaluca math = new MatematicaMaluca();
		System.out.println(math.contaMaluca(20));
		assertEquals(20 * 3, math.contaMaluca(20));
	}

	@Test
	public void deveMultiplicarNumerosMenoresQue10() {

		MatematicaMaluca math = new MatematicaMaluca();
		System.out.println(math.contaMaluca(5));
		assertEquals(5 * 2, math.contaMaluca(5));
	}
}
