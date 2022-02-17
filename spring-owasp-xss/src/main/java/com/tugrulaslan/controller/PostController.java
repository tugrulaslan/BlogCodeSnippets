package com.tugrulaslan.controller;

import com.tugrulaslan.service.XssSanitizerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class PostController {

    private CopyOnWriteArraySet<String> posts = new CopyOnWriteArraySet<>();
    private final XssSanitizerService service;

    public PostController(XssSanitizerService service) {
        this.service = service;
    }

    @PostMapping("/posts")
    String create(@RequestBody String content) {
        if(posts.size() != 0) posts.clear();
        posts.add(service.sanitize(content));
        return retrievePost();
    }

    @GetMapping("/posts")
    String retrieve() {
        if(posts.size() == 0) return null;
        return retrievePost();
    }

    private String retrievePost() {
        return posts.stream()
                .findFirst()
                .get();
    }
}
