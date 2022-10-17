package com.egg.news.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adri
 */
@Controller
@RequestMapping("/noticia")
public class PortalNoticia { //localhost:8080/noticia

    @GetMapping("/registrar") //localhost:8080/noticia/registrar
    public String resgistrar() {
        return "inicio.html";
    }
    
    @PostMapping("/registro")
    
    public String registro( @RequestParam String titulo){
        System.out.println("Titulo" + titulo);
         return "inicio.html";
                
    }
    
}
