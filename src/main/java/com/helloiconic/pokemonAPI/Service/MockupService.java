package com.helloiconic.pokemonAPI.Service;

import com.helloiconic.pokemonAPI.Model.DTO.Post;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class MockupService {
    private WebClient client = WebClient
            .builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();

    private CacheManager cacheManager;

    public MockupService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Cacheable("Purpose:MochupService:getPosts")
    public Post[] getPosts() {
        Mono<Post[]> response = client
                .get()
                .uri("/typicode/demo/posts")
                .retrieve()
                .bodyToMono(Post[].class);
        Post[] posts = response.block();
        return posts;
    }

    @Cacheable("Purpose:MochupService:getPostById")
    public Post getPostById(String id) throws ResponseStatusException {
        Mono<Post> response = client
                .get()
                .uri(String.format("/typicode/demo/posts/%s", id))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, r -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")))
        .bodyToMono(Post.class);
        Post post = response.block();
        return post;
    }
}
