package br.com.fiap.money_control_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_control_api.model.Category;

@RestController
public class CategoryController {

	private List<Category> repository = new ArrayList<>();

	@GetMapping("/categories") // Método get retorna todas as categorias
	public List<Category> index() { // mocky são dados fixos para variáveis de teste
		return repository;
	}

	@PostMapping("/categories")
	// @ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Category> create(@RequestBody Category category) {
		System.out.println("Cadastrando categoria" + category.getName());
		repository.add(category);
		return ResponseEntity.status(201).body(category);
	}

	@GetMapping("/categories/{id}") // Método vai retornar a categoria que eu estou pedindo
	public ResponseEntity<Category> get(@PathVariable Long id) {
		System.out.println("Buscando categoria" + id);
		var category = repository.stream()
				.filter(c -> c.getId().equals(id)).findFirst();
		/*
		 * stream para mandar os dados em partes pelo túnel. filter para filtrar o que
		 * eu quero que passe por ele.
		 * c -> c === é uma => arrow function do js,
		 */

		if (category.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(category.get());
	}

}
