package com.helloiconic.pokemonAPI.Controller;

import com.helloiconic.pokemonAPI.Model.Pokemon;
import com.helloiconic.pokemonAPI.Service.PokemonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Tag(name = "Pokemon")
@RestController
@RequestMapping("/pokemons")
public class PokemonController {
    private final List<Pokemon> pokemons = new ArrayList<>();
    private final PokemonService pokemonService;

    public PokemonController (
            PokemonService pokemonService
    ) {
        this.pokemonService = pokemonService;
    }
    @GetMapping
    public Iterable<Pokemon> getPokemons() {
        return this.pokemonService.getPokemons();
    }

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@Valid @RequestBody Pokemon pokemon) {
        Pokemon p = this.pokemonService.createPokemon(pokemon);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public Optional<Pokemon> getPokemon(@PathVariable("id") int id) {
        return this.pokemonService.getPokemonById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deletePokemon(@PathVariable("id") int id) {
        this.pokemonService.deletePokemonById(id);
        return true;
    }
}
