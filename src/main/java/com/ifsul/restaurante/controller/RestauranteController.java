package com.ifsul.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsul.restaurante.model.Restaurante;
import com.ifsul.restaurante.service.RestauranteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@Operation(summary = "Busca todos os restaurantes cadastrados no banco.", method = "GET")
	@GetMapping
	public List<Restaurante> listarRestaurantes() {
		return restauranteService.listarRestaurantes();
	}

	@Operation(summary = "Busca um restaurante pelo id.", parameters = @Parameter(description = "O id do restaurante buscado."))
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cadastra o restaurante passado por parâmetro no corpo da requisição.", parameters = @Parameter(description = "Restaurante que se deseja cadastrar no banco."))
	@PostMapping
	public ResponseEntity<Restaurante> criarRestaurante(@RequestBody Restaurante restaurante) {
		Restaurante novoRestaurante = restauranteService.criarRestaurante(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
	}

	@Operation(summary = "Atualiza o restaurante pelo id informado com os dados do restaurante passado no corpo da requisição.")
	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		Restaurante restauranteExistente = restauranteService.buscarRestaurantePorId(id);
		if (restauranteExistente != null) {
			restaurante.setId(id);
			return ResponseEntity.ok(restauranteService.criarRestaurante(restaurante));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Deleta o restaurante pelo id informado.", parameters = @Parameter(description = "O id do restaurante que se deseja deletar do banco."))
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
		Restaurante restauranteExistente = restauranteService.buscarRestaurantePorId(id);
		if (restauranteExistente != null) {
			restauranteService.deletarRestaurante(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Busca restaurantes pelo nome informado.", parameters = @Parameter(description = "O nome do(s) restaurante(s) buscado(s)."))
	@GetMapping("/nome/{nome}")
	public List<Restaurante> buscarRestaurantesPorNome(@PathVariable String nome) {
		List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorNome(nome);
		return restaurantes;
	}

	@Operation(summary = "Busca restaurantes pelo endereço informado.", parameters = @Parameter(description = "O endereço do(s) restaurante(s) buscado(s)."))
	@GetMapping("/endereco/{endereco}")
	public List<Restaurante> buscarRestaurantesPorEndereco(@PathVariable String endereco) {
		List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorEndereco(endereco);
		return restaurantes;
	}

	@Operation(summary = "Busca restaurantes pelo tipo de cozinha informado.", parameters = @Parameter(description = "O tipo de cozinha do(s) restaurante(s) buscado(s)."))
	@GetMapping("/cozinha/{cozinha}")
	public List<Restaurante> buscarRestaurantesPorTipoCozinha(@PathVariable String cozinha) {
		List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorTipoCozinha(cozinha);
		return restaurantes;
	}

}
