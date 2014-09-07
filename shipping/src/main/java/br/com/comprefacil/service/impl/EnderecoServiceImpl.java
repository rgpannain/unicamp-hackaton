package br.com.comprefacil.service.impl;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import br.com.comprefacil.dto.EnderecoTO;
import br.com.comprefacil.exception.CepInvalidoException;
import br.com.comprefacil.exception.CorreiosException;
import br.com.comprefacil.factory.EnderecoFactory;
import br.com.comprefacil.service.CorreiosService;
import br.com.comprefacil.service.EnderecoService;

/**
 * Default implementation for the EnderecoService.
 * @author espsoft06
 */
public class EnderecoServiceImpl implements EnderecoService {
	
	private static Logger logger = Logger.getLogger(EnderecoServiceImpl.class);

	public EnderecoTO recuperarEnderecoPorCep(String cep) 
		throws CepInvalidoException, CorreiosException {

		if(!isCepValido(cep)) throw new CepInvalidoException(String.format("Cep %s não encontra-se no formato 99999-999.", cep));

		CorreiosService correiosService = EnderecoFactory.getCorreiosInstance();
		JSONObject retornoWsCorreios = correiosService.recuperarEndereco(cep);

		EnderecoTO enderecoTO = null;

		try{
			 enderecoTO = EnderecoTO.create()
					 		.addTipoLogradouro(retornoWsCorreios.getString("tipoLogradouro"))
					 		.addEndereco(retornoWsCorreios.getString("endereco"))
					 		.addCep(retornoWsCorreios.getString("cep"))
					 		.addBairro(retornoWsCorreios.getString("bairro"))
					 		.addCidade(retornoWsCorreios.getString("cidade"))
					 		.addEstado(retornoWsCorreios.getString("estado"));
			 
			 logger.debug("Endereco encontrado: " + enderecoTO);
		}catch(Exception cause){
			cause.printStackTrace();
		}
		
		return enderecoTO;
	}
	
	public boolean isCepValido(String cep) {
		if(cep == null) return false;

		Pattern cepPattern = Pattern.compile("\\d{5}-\\d{3}");
		return cepPattern.matcher(cep).matches();
	}
	
}