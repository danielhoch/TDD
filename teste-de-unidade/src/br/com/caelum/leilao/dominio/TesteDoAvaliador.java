package br.com.caelum.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLance() {

		// parte 1: cenario
		Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        // parte 2: acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // parte 3: validacao
        double maiorEsperado = 400;
        double menorEsperado = 250;
        

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}
}
