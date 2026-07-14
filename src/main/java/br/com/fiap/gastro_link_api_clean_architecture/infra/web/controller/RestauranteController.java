package br.com.fiap.gastro_link_api_clean_architecture.infra.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController {

	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
}
