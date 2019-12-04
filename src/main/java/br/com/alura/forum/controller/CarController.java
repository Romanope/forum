package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarController {

	@GetMapping("/")
	@ResponseBody
	public String acelerar() {
		return "Acelerando";
	}
}
