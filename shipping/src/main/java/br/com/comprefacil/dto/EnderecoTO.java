package br.com.comprefacil.dto;

public class EnderecoTO {

	private String tipoLogradouro;
	private String endereco;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;

	private EnderecoTO(){ }
	
	public static EnderecoTO create(){
		return new EnderecoTO();
	}
	
	public EnderecoTO addTipoLogradouro(String tipoLogradouro){
		this.tipoLogradouro = tipoLogradouro;
		return this;
	}
	
	public EnderecoTO addEndereco(String endereco){
		this.endereco = endereco;
		return this;
	}
	
	public EnderecoTO addCep(String cep){
		this.cep = cep;
		return this;
	}

	public EnderecoTO addBairro(String bairro){
		this.bairro = bairro;
		return this;
	}

	public EnderecoTO addCidade(String cidade){
		this.cidade = cidade;
		return this;
	}
	
	public EnderecoTO addEstado(String estado){
		this.estado = estado;
		return this;
	}
	
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCep() {
		return cep;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	@Override
	public String toString(){
		return String.format("EnderecoTO[tipoLogradouro: %s, endereco: %s, cep: %s, bairro: %s, cidade: %s, estado: %s]", 
								tipoLogradouro, endereco, cep, bairro, cidade, estado);
	}

}