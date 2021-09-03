package br.com.ottimizza.regraslistener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    
    @GetMapping
	@RequestMapping("/")
    public String getIndex() {
        return "{\"api\":\"Api-Regras(listner) is on!\", \"by\":\"Ottimizza\"}";
    }

}
