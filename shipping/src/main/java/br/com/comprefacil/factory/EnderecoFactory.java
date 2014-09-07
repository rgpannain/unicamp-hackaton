package br.com.comprefacil.factory;

import br.com.comprefacil.service.CorreiosService;
import br.com.comprefacil.service.EnderecoService;
import br.com.comprefacil.service.impl.EnderecoServiceImpl;
import br.com.comprefacil.service.impl.StubCorreiosServiceImpl;

public final class EnderecoFactory {
	
	private static EnderecoService enderecoInstance = null;
	private static CorreiosService correiosInstance = null;

	private EnderecoFactory(){ }
	
	public static EnderecoService getInstance(){
		if(enderecoInstance == null) enderecoInstance = new EnderecoServiceImpl();
		return enderecoInstance;
	}
	
	public static CorreiosService getCorreiosInstance(){
		if(correiosInstance == null) correiosInstance = new StubCorreiosServiceImpl();
		return correiosInstance;
	}
	
}