package com.egg.news.controladores;

import com.egg.news.entidades.Foto;
import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.servicios.FotoServicio;
import com.egg.news.servicios.ServicioNoticia;
import com.egg.news.servicios.UsuarioServicio;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Slf4j
public class PortalControlador {

    @GetMapping("/")
    public String index() {
        return "inicio.html";
    }

}
