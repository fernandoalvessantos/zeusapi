package com.fernando.zeus.zeusapi.resources;


import com.fernando.zeus.zeusapi.domain.Usuario;
import com.fernando.zeus.zeusapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResources {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuarios = usuarioService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Usuario usuario){
        usuario = usuarioService.salvar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscar(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscar(id));
    }
}
