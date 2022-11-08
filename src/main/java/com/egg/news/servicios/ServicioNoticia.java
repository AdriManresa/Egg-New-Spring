package com.egg.news.servicios;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.repositorio.NoticiaRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adri
 */
@Service
public class ServicioNoticia {
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @Autowired
    private NoticiaRepositorio noticiasRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException, IOException {
        
        validarAtributos(titulo, cuerpo);
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(new Date());
        noticia.setBaja(true);
        
        noticiasRepositorio.save(noticia);
    }
    
    public List<Noticia> listarNoticias() {
        
        List<Noticia> noticias = new ArrayList();
        noticias = noticiasRepositorio.findAll();
        
        return noticias;
    }

    public Noticia  getOne(String id) {
        return noticiasRepositorio.getOne(id);
    }
    
    @Transactional
    public void modificarNoticia(String id, String titulo, String cuerpo) throws MiException, IOException {
        
        validarAtributos(titulo, cuerpo);
        Optional<Noticia> respuesta = noticiasRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            
            noticiasRepositorio.save(noticia);
        } else {
            throw new MiException("No se encontró la noticia");
        }
    }
    
    private void validarAtributos(String titulo, String cuerpo) throws MiException {
        
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El Titulo no puede estar vacio");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El Cuerpo no puede estar vacio");
        }
        
    }
    
}
