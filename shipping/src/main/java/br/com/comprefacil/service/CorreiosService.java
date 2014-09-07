package br.com.comprefacil.service;

import org.json.JSONObject;

import br.com.comprefacil.exception.CorreiosException;

public interface CorreiosService {
	public JSONObject recuperarEndereco(String cep) throws CorreiosException;
}