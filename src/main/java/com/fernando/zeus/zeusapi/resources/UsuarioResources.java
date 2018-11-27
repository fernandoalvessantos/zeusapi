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

    @RequestMapping(value = "/clientes",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Usuario>> listarClientes(){
        List<Usuario> usuarios = usuarioService.listarClientes(UsuarioService.PERFIL_CLIENTE);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @RequestMapping(value = "/gerentes",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Usuario>> listarGerentes(){
        List<Usuario> usuarios = usuarioService.listarClientes(UsuarioService.PERFIL_GERENTE);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @RequestMapping(value = "/clientes",method = RequestMethod.POST)
    public ResponseEntity<Void> salvarCliente(@Valid @RequestBody Usuario usuario){
        usuario.setPerfil(UsuarioService.PERFIL_CLIENTE);
        usuario = usuarioService.salvar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/gerentes", method = RequestMethod.POST)
    public ResponseEntity<Void> salvarGerente(@Valid @RequestBody Usuario usuario){
        usuario.setPerfil(UsuarioService.PERFIL_GERENTE);
        usuario = usuarioService.salvar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/clientes/{id}",method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscarCliente(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarCliente(id));
    }

    @RequestMapping(value = "/gerentes/{id}",method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscarGerente(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarGerente(id));
    }
}
