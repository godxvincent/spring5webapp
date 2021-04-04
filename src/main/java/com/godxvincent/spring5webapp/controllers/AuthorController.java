package com.godxvincent.spring5webapp.controllers;

import com.godxvincent.spring5webapp.repositories.AuthorRepository;
import com.godxvincent.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getBooks(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        // Thymeleaf usa el retorno especificado para saber que template usar
        return "author_list";
    }
}
