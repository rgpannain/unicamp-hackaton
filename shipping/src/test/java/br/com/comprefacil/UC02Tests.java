package br.com.comprefacil;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.comprefacil.dto.EnderecoTO;
import br.com.comprefacil.exception.CepInvalidoException;
import br.com.comprefacil.exception.CorreiosException;
import br.com.comprefacil.factory.EnderecoFactory;
import br.com.comprefacil.service.EnderecoService;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * Testes para o caso de uso UC02
 * @author Bruno Moura
 */
public class UC02Tests {

	@Rule 
	public ExpectedException thrown = ExpectedException.none();

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);

	private EnderecoService enderecoService = null;

	@Before
	public void setUp(){
		enderecoService = EnderecoFactory.getInstance();
	}

	@Test
	public void testCepExistente() throws Exception {
		String cepIC = "13083-852";
	    stubFor(get(urlEqualTo("/correios/enderecos?cep="+cepIC))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/json")
	                .withBody("{ \"tipoLogradouro\": \"Avenida\", \"endereco\": \"Albert Einstein\", \"cep\": \"13083-852\", \"bairro\": \"Cidade Universitaria\", \"cidade\": \"Campinas\", \"estado\": \"SP\" }")));

		EnderecoTO enderecoTO = enderecoService.recuperarEnderecoPorCep(cepIC);
		
		assertNotNull(enderecoTO);
		assertTrue("Avenida".equals(enderecoTO.getTipoLogradouro()));
		assertTrue("Albert Einstein".equals(enderecoTO.getEndereco()));
		assertTrue("13083-852".equals(enderecoTO.getCep()));
		assertTrue("Cidade Universitaria".equals(enderecoTO.getBairro()));
		assertTrue("Campinas".equals(enderecoTO.getCidade()));
		assertTrue("SP".equals(enderecoTO.getEstado()));
	}

	@Test
	public void testCepInvalido() throws Exception {
		thrown.expect(CepInvalidoException.class);
		enderecoService.recuperarEnderecoPorCep("000000000");
	}

	@Test
	public void testCepInexistente() throws Exception {
	    thrown.expect(CorreiosException.class);
		thrown.expectMessage("Codigo de Erro dos Correios: -3");

		String cepInexistente = "00000-000";
	    stubFor(get(urlEqualTo("/correios/enderecos?cep="+cepInexistente))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/json")
	                .withBody("{ \"erro\": \"-3\" }")));

	    enderecoService.recuperarEnderecoPorCep(cepInexistente);
	}

	@Test
	public void testServicoIndisponivel() throws Exception {
	    thrown.expect(CorreiosException.class);
		thrown.expectMessage("Codigo de Erro dos Correios: -7");

		String cep = "11430-110";
	    stubFor(get(urlEqualTo("/correios/enderecos?cep="+cep))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/json")
	                .withBody("{ \"erro\": \"-7\" }")));
	    
	    enderecoService.recuperarEnderecoPorCep(cep);
	}

}