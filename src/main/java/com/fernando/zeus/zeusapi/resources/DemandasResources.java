package com.fernando.zeus.zeusapi.resources;

import com.fernando.zeus.zeusapi.domain.Demanda;
import com.fernando.zeus.zeusapi.domain.DemandaGerente;
import com.fernando.zeus.zeusapi.domain.DetalhesErro;
import com.fernando.zeus.zeusapi.domain.Usuario;
import com.fernando.zeus.zeusapi.services.DemandaService;
import com.fernando.zeus.zeusapi.services.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(value = "/demandas",
        description = "Serviços para interação com Demandas",
authorizations = {@Authorization(value = "Basic")})
@RestController
@RequestMapping("/demandas")
public class DemandasResources {

    @Autowired
    private DemandaService demandaService;

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Lista as Demandas por cliente",
            response = Demanda.class,
            responseContainer = "List",
            notes = "Este operação lista as demandas de um cliente")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response=Demanda.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Demanda>> listar(@PathVariable("id") Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listar(idCliente));
    }

    @ApiOperation(value = "Pesquisa Demandas por cliente",
            consumes = "application/json",
            produces = "application/json",
            response = Demanda.class,
            responseContainer = "List",
            notes = "Este operação lista as demandas de um cliente")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response=Demanda.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/{id}/pesquisa", method = RequestMethod.POST)
    public ResponseEntity<List<Demanda>> listarPesquisa(@PathVariable("id") Long idCliente, @RequestBody Demanda demandaPesquisa) {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listarPesquisa(idCliente, demandaPesquisa));
    }

    @ApiOperation(value = "Salva uma Demanda do cliente",
            consumes = "application/json",
            produces = "application/json",
            response = Demanda.class,
            notes = "Este operação cria uma demanda")
    @ApiResponses(value= {
            @ApiResponse(
                    code=201,
                    message="Retorna a Demanda criada com status 200",
                    response=Demanda.class
            )
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@PathVariable("id") Long idCliente, @Valid @RequestBody Demanda demanda) {
        Usuario cliente = usuarioService.buscarCliente(idCliente);
        demanda.setCliente(cliente);
        demanda = demandaService.salvar(demanda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(demanda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Recupera uma Demanda específica",
            consumes = "application/json",
            produces = "application/json",
            response = Demanda.class,
            notes = "Este operação busca uma demanda na base de dados")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna a Demanda pelo ID informado",
                    response=Demanda.class
            ),
            @ApiResponse(
                    code=404,
                    message="Id informado não corresponde a alguma Demanda",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/busca/{idDemanda}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("idDemanda") Long idDemanda) {
        Demanda demanda = null;
        demanda = demandaService.buscar(idDemanda);
        return ResponseEntity.status(HttpStatus.OK).body(demanda);
    }

    @ApiOperation(value = "Exclui uma Demanda específica",
            notes = "Este operação exclui uma demanda na base de dados")
    @ApiResponses(value= {
            @ApiResponse(
                    code=204,
                    message="Retorna somente código NO CONTENT"
            ),
            @ApiResponse(
                    code=404,
                    message="Id informado não corresponde a alguma Demanda",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/{idDemanda}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("idDemanda") Long id) {
        demandaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Atualiza uma Demanda específica",
            notes = "Este operação atualizad uma demanda na base de dados")
    @ApiResponses(value= {
            @ApiResponse(
                    code=204,
                    message="Retorna somente código NO CONTENT"
            ),
            @ApiResponse(
                    code=404,
                    message="Id informado não corresponde a alguma Demanda",
                    response= DetalhesErro.class
            )
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Demanda demanda, @PathVariable("id") Long id) {
        demanda.setId(id);
        demandaService.atualizar(demanda);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Relaciona uma Demanda a um Gerente",
            consumes = "application/json",
            notes = "Este operação atualiza uma demanda incluindo a relação com um Gerente")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna somente código OK"
            )
    })
    @RequestMapping(value = "/relacionar", method = RequestMethod.POST)
    public ResponseEntity<Void> relacionarDemandaGerente(@RequestBody DemandaGerente demandaGerente) {
        demandaService.relacionarDemandaGerente(demandaGerente);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "Lista as Demandas Não Gerenciadas",
            response = Demanda.class,
            responseContainer = "List",
            notes = "Este operação lista as demandas que ainda não estão gerenciadas por nenhum Gerente")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response=Demanda.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/naorelacionados", method = RequestMethod.GET)
    public ResponseEntity<List<Demanda>> listaDemandaNaoRelacionadas() {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listarNaoRelacionadas());
    }

    @ApiOperation(value = "Pesquisa as Demandas Não Gerenciadas",
            response = Demanda.class,
            responseContainer = "List",
            notes = "Este operação pesquisa demandas que ainda não estão gerenciadas por nenhum Gerente")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response=Demanda.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/naorelacionados", method = RequestMethod.POST)
    public ResponseEntity<List<Demanda>> listaDemandaNaoRelacionadas(@RequestBody DemandaGerente demandaGerente) {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listaPesquisaNaoRelacionadas(demandaGerente));
    }

    @ApiOperation(value = "Lista as Demandas Gerenciadas pelo Gerente",
            response = Demanda.class,
            responseContainer = "List",
            notes = "Este operação lista as demandas que estão gerenciadas por um Gerente")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response=Demanda.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/gerente/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Demanda>> listarPorGerente(@PathVariable("id") Long idGerente) {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listaPorGerente(idGerente));
    }

    @ApiOperation(value = "Pesquisa as Demandas Gerenciadas pelo Gerente",
            response = Demanda.class,
            responseContainer = "List",
            notes = "Este operação lista as demandas que estão gerenciadas por um Gerente")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um List<Demanda> com status 200",
                    response=Demanda.class,
                    responseContainer = "List"
            )
    })
    @RequestMapping(value = "/gerente/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<Demanda>> listarPorGerente(@PathVariable("id") Long idGerente, @RequestBody DemandaGerente demandaGerente) {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listaPorGerentePesquisa(idGerente, demandaGerente));
    }

}
