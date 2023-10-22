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

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(@RequestBody Restaurante restaurante) {
        Restaurante novoRestaurante = restauranteService.criarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
    }

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
    
    @GetMapping("/nome/{nome}")
    public List<Restaurante> buscarRestaurantesPorNome(@PathVariable String nome) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorNome(nome);
        return restaurantes;
    }

    @GetMapping("/endereco/{endereco}")
    public List<Restaurante> buscarRestaurantesPorEndereco(@PathVariable String endereco) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorEndereco(endereco);
        return restaurantes;
    }

    @GetMapping("/cozinha/{cozinha}")
    public List<Restaurante> buscarRestaurantesPorTipoCozinha(@PathVariable String cozinha) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorTipoCozinha(cozinha);
        return restaurantes;
    }
}
