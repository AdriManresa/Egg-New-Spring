package com.egg.news.controladores;

import com.egg.news.excepciones.MiException;
import com.egg.news.servicios.FotoServicio;
import com.egg.news.servicios.ServicioNoticia;
import com.egg.news.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adri
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    ServicioNoticia noticiasService;

    @Autowired
    FotoServicio fotoService;

    @Autowired
    UsuarioServicio usuarioService;

    @GetMapping("/registro")
    public String registrar() {
        return "registro";
    }
        @GetMapping("/login")
    public String login() {
        return "login.html";
    }


    @PostMapping("/registro")
    public String registro(String nombreUsuario, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) throws MiException {
        try {
            usuarioService.registrar(nombreUsuario, email, password, password2);

        } catch (MiException ex) {
            modelo.addAttribute("nombreUsuario", nombreUsuario);
            modelo.addAttribute("email", email);
            modelo.addAttribute("password", password);
            modelo.addAttribute("password2", password2);
            modelo.put("error", ex.getMessage());

            return "registro";
        }
        return "index";
    }
}
