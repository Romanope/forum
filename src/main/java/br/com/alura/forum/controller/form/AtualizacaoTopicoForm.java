package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class AtualizacaoTopicoForm {

	@NotBlank @Length(min = 5)
	private String titulo;
	
	@NotBlank
	private String mensagem;

	public AtualizacaoTopicoForm(@NotBlank @Length(min = 5) String titulo, @NotBlank String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public AtualizacaoTopicoForm() {

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		topico.setMensagem(mensagem);
		topico.setTitulo(titulo);
		
		return topico;
	}
}
