package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoForm {

	@NotBlank @Length(min = 5)
	private String titulo;
	
	@NotBlank
	private String mensagem;
	
	@NotBlank
	private String nomeCurso;
	
	public TopicoForm(String titulo, String mensagem, String nomeCurso) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.nomeCurso = nomeCurso;
	}

	public TopicoForm() {

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

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
}
