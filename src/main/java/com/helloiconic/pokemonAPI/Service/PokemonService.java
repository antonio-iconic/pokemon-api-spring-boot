package com.helloiconic.pokemonAPI.Service;

import com.helloiconic.pokemonAPI.Model.Pokemon;
import com.helloiconic.pokemonAPI.Repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    public PokemonService(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    public Iterable<Pokemon> getPokemons() {
        return this.pokemonRepository.findAll();
    }

    public Pokemon createPokemon(Pokemon pokemon) {
        Optional<Pokemon> existingPokemon = this.pokemonRepository.findPokemonByNumber(pokemon.number);
        Pokemon p;
        if(!existingPokemon.isPresent()) {
            p = this.pokemonRepository.save(pokemon);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Pokemon already exists");
        }
        return p;
    }

    public Optional<Pokemon> getPokemonById(int id) {
        return this.pokemonRepository.findById(id);
    }

    public void deletePokemonById(int id) {
        this.pokemonRepository.deleteById(id);
    }

    public Optional<Pokemon> getPokemonByNumber(Long number) {
        return this.pokemonRepository.findPokemonByNumber(number);
    }
}
