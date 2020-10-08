package br.com.caelum.matematica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveRetornarAnoBissexto() {        
	    AnoBissexto anoBissexto = new AnoBissexto();

	    assertEquals(true, anoBissexto.getAnoBissexto(2016));
	    assertEquals(true, anoBissexto.getAnoBissexto(2012));
	}

	@Test
	public void naoDeveRetornarAnoBissexto() {        
	    AnoBissexto anoBissexto = new AnoBissexto();        

	    assertEquals(false, anoBissexto.getAnoBissexto(2015));
	    assertEquals(false, anoBissexto.getAnoBissexto(2011));
	}
}
