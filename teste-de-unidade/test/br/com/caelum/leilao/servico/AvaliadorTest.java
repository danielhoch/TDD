package br.com.caelum.leilao.servico;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario maria;
    private Usuario jose;
    private Usuario joao;

    @Before
    public void setUp() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("Jo�o");
        this.jose = new Usuario("Jos�");
        this.maria = new Usuario("Maria");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation 3 Novo")
            .lance(joao, 250)
            .lance(jose, 300)
            .lance(maria, 400)
            .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new CriadorDeLeilao()
        .para("Playstation 3 Novo")
        .lance(joao, 1000)
        .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMenorLance(), equalTo(leiloeiro.getMaiorLance()));
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation 3 Novo")
            .lance(joao, 100)
            .lance(maria, 200)
            .lance(joao, 300)
            .lance(maria, 400)
            .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());

        assertThat(maiores, hasItems(
                new Lance(maria, 400), 
                new Lance(joao, 300),
                new Lance(maria, 200)
        ));    
    }

    @Test(expected=RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();
        leiloeiro.avalia(leilao);
    }
}
