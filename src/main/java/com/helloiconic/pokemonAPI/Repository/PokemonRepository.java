package com.helloiconic.pokemonAPI.Repository;

import com.helloiconic.pokemonAPI.Model.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {

    Optional<Pokemon> findPokemonByNumber(Long number);
}
