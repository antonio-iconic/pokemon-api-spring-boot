package com.helloiconic.pokemonAPI.Controller;

import com.helloiconic.pokemonAPI.Service.MockupService;
import com.helloiconic.pokemonAPI.Model.DTO.Post;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pokemon")
@RestController
@RequestMapping("/posts")
public class PostController {
    private final MockupService mockupService;

    public PostController(MockupService mockupService) {
        this.mockupService = mockupService;
    }

    @GetMapping
    public Post[] getPosts() {
        return this.mockupService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById (@PathVariable("id") String id) {
        return this.mockupService.getPostById(id);
    }
}
