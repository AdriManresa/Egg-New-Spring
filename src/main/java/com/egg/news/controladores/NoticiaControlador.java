package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.servicios.ServicioNoticia;
import com.egg.news.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adri
 */
@Controller
@RequestMapping("/noticia")

public class NoticiaControlador { //localhost:8080/noticia

    @Autowired
    private ServicioNoticia noticiasService;
    

    @GetMapping("/crear")
    public String creando(){
    return "noticia_form.html";
    }

    @PostMapping("/creando")
    public String redactarNoticia(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) throws MiException, IOException {
        try {
            noticiasService.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia fue cargada con exito");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_form.html";
        }
        return "index.html";
    }

    @GetMapping("/ver")
    public String verNoticias(ModelMap modelo) {
        List<Noticia> noticias = noticiasService.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "mis_publicaciones";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
    modelo.put("usuario",noticiasService.getOne(id));
    
    return "noticia_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo,String cuerpo, ModelMap modelo) throws MiException, IOException{
        try {
             noticiasService.modificarNoticia(id, titulo, cuerpo);
             return "redirect:../ver";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
         return "noticia_modificar.html";
        }
       
    }
    
}
