package br.com.comprefacil.service;

import br.com.comprefacil.dto.EnderecoTO;
import br.com.comprefacil.exception.CepInvalidoException;
import br.com.comprefacil.exception.CorreiosException;

/**
 * Interface do servico de endereco provido pelo componente.
 * @author Bruno Moura
 */
public interface EnderecoService {
	public EnderecoTO recuperarEnderecoPorCep(String cep) throws CepInvalidoException, CorreiosException;
	public boolean isCepValido(String cep);
}