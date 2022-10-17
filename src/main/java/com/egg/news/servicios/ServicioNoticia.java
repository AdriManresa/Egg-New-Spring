package com.egg.news.servicios;

import com.egg.news.entidades.Foto;
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
import org.springframework.web.multipart.MultipartFile;

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
    public void crearNoticia(String titulo, String cuerpo, MultipartFile archivo) throws MiException, IOException {

        validarAtributos(titulo, cuerpo, archivo);

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(new Date());
        noticia.setBaja(true);

        Foto foto = fotoServicio.guardar(archivo);
        noticia.setFoto(foto);

        noticiasRepositorio.save(noticia);
    }

    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList();
        noticias = noticiasRepositorio.findAll();

        return noticias;
    }

    public Noticia buscarPorId(String id) {
        Noticia noticia = new Noticia();
        noticia = noticiasRepositorio.buscarporId(id);
        return noticia;
    }
  @Transactional
    public void modificarNoticia(Long id, String titulo, String cuerpo, MultipartFile archivo) throws MiException, IOException {

        validarAtributos(titulo, cuerpo, archivo);
        Optional<Noticia> respuesta = noticiasRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            
            String idFoto = null;
            if (noticia.getFoto()!=null) {
                idFoto = noticia.getFoto().getId();
            }
            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            noticia.setFoto(foto);

            noticiasRepositorio.save(noticia);
        } else {
            throw new MiException("No se encontró la noticia");
        }
    }
    
    private void validarAtributos(String titulo, String cuerpo, MultipartFile archivo) throws MiException {

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede ser nulo");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El cuerpo no puede ser nulo");
        }
        if (archivo.isEmpty() || archivo == null) {
            throw new MiException("La foto no puede ser nulo");
        }

    }

}
