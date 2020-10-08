package br.com.caelum.leilao.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static br.com.caelum.leilao.dominio.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.dominio.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
	}

	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").lance(new Usuario("Steve Jobs"), 2000)
				.lance(new Usuario("Steve Wozniak"), 3000).constroi();

		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Usuario steveJobs = new Usuario("Steve Jobs");
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").lance(steveJobs, 2000.0).lance(steveJobs, 3000.0)
				.constroi();

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");

		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").lance(steveJobs, 2000).lance(billGates, 3000)
				.lance(steveJobs, 4000).lance(billGates, 5000).lance(steveJobs, 6000).lance(billGates, 7000)
				.lance(steveJobs, 8000).lance(billGates, 9000).lance(steveJobs, 10000).lance(billGates, 11000)
				.lance(steveJobs, 12000).constroi();

		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		assertEquals(11000.0, leilao.getLances().get(ultimo).getValor(), 0.00001);
	}
	
}