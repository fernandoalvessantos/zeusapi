package com.fernando.zeus.zeusapi.resources;


import com.fernando.zeus.zeusapi.domain.DetalhesErro;
import com.fernando.zeus.zeusapi.domain.Login;
import com.fernando.zeus.zeusapi.domain.Usuario;
import com.fernando.zeus.zeusapi.services.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
@Api(value = "/usuarios",
        description = "Serviços para interação com os Usuários",
        authorizations = {@Authorization(value = "Basic")})
@RestController
@RequestMapping("/usuarios")
public class UsuarioResources {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Lista os Usuarios com perfil cliente",
            response = Usuario.class,
            responseContainer = "List",
            notes = "Este operação lista os usuários com perfil CLIENTE",
    produces = "application/json")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response= Usuario.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/clientes",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Usuario>> listarClientes(){
        List<Usuario> usuarios = usuarioService.listarClientes(UsuarioService.PERFIL_CLIENTE);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @ApiOperation(value = "Lista os Usuarios com perfil gerente",
            response = Usuario.class,
            responseContainer = "List",
            notes = "Este operação lista os usuários com perfil GERENTE",
            produces = "application/json")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response= Usuario.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/gerentes",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Usuario>> listarGerentes(){
        List<Usuario> usuarios = usuarioService.listarClientes(UsuarioService.PERFIL_GERENTE);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }


    @ApiOperation(value = "Salva um novo Usuário com Perfil Cliente",
            response = Usuario.class,
            notes = "Salva um novo Usuário com Perfil Cliente",
            produces = "application/json")
    @ApiResponses(value= {
            @ApiResponse(
                    code=201,
                    message="Retorna o Usuário criado",
                    response= Usuario.class
            ),
            @ApiResponse(
                    code=409,
                    message="Usuario já Existente",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/clientes",method = RequestMethod.POST)
    public ResponseEntity<Void> salvarCliente(@Valid @RequestBody Usuario usuario){
        usuario.setPerfil(UsuarioService.PERFIL_CLIENTE);
        usuario = usuarioService.salvar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Salva um novo Usuário com Perfil Gerente",
            response = Usuario.class,
            notes = "Salva um novo Usuário com Perfil Gerente",
            produces = "application/json")
    @ApiResponses(value= {
            @ApiResponse(
                    code=201,
                    message="Retorna o Usuário criado",
                    response= Usuario.class
            ),
            @ApiResponse(
                    code=409,
                    message="Usuario já Existente",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/gerentes", method = RequestMethod.POST)
    public ResponseEntity<Void> salvarGerente(@Valid @RequestBody Usuario usuario){
        usuario.setPerfil(UsuarioService.PERFIL_GERENTE);
        usuario = usuarioService.salvar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Busca Usuário com Perfil cliente",
            response = Usuario.class,
            produces = "application/json")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o Usuário criado",
                    response= Usuario.class
            ),
            @ApiResponse(
                    code=404,
                    message="Usuario Não encontrado",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/clientes/{id}",method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscarCliente(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarCliente(id));
    }

    @ApiOperation(value = "Busca Usuário com Perfil GERENTE",
            response = Usuario.class,
            produces = "application/json")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o Usuário criado",
                    response= Usuario.class
            ),
            @ApiResponse(
                    code=404,
                    message="Usuario Não encontrado",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/gerentes/{id}",method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscarGerente(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarGerente(id));
    }

    @ApiOperation(value = "Realiza Login do usuário no sistema",
            response = Usuario.class,
            produces = "application/json")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o Usuário de acordo com login e senha",
                    response= Usuario.class
            ),
            @ApiResponse(
                    code=409,
                    message="Usuario Não encontrado",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Usuario> realizaLogin(@Valid @RequestBody Login login){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.realizarLogin(login));
    }
}
