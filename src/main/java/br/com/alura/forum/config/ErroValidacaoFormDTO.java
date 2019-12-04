package br.com.alura.forum.config;

public class ErroValidacaoFormDTO {

	private String mensagem;
	private String campo;

	public ErroValidacaoFormDTO(String mensagem, String campo) {
		this.mensagem = mensagem;
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getCampo() {
		return campo;
	}
}
