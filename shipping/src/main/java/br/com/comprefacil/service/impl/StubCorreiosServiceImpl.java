package br.com.comprefacil.service.impl;

import org.json.JSONObject;

import br.com.comprefacil.call.HttpFetcher;
import br.com.comprefacil.exception.CorreiosException;
import br.com.comprefacil.service.CorreiosService;

public class StubCorreiosServiceImpl implements CorreiosService {
	
	private HttpFetcher instance = null;

	public StubCorreiosServiceImpl(){ instance = new HttpFetcher(); }
	
	public JSONObject recuperarEndereco(String cep) throws CorreiosException {
		JSONObject enderecoJSON = null;

		try {
			String endereco = instance.fetchAsString("http://localhost:8090/correios/enderecos?cep=" + cep);
			enderecoJSON = new JSONObject(endereco);
		}catch(Exception cause){
			cause.printStackTrace();
		}

		if(enderecoJSON != null && enderecoJSON.has("erro")){
			Integer errorCode = -1;
			
			try{
				errorCode = enderecoJSON.getInt("erro");
			}catch(Exception cause){
				cause.printStackTrace();
			}
			
			throw new CorreiosException(String.format("Codigo de Erro dos Correios: %d", errorCode), errorCode);
		}

		return enderecoJSON;
	}

}