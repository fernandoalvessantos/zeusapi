package com.fernando.zeus.zeusapi.resources;

import com.fernando.zeus.zeusapi.domain.Demanda;
import com.fernando.zeus.zeusapi.domain.Usuario;
import com.fernando.zeus.zeusapi.services.DemandaService;
import com.fernando.zeus.zeusapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/demandas")
public class DemandasResources {

    @Autowired
    private DemandaService demandaService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Demanda>> listar(@PathVariable("id") Long idCliente){
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listar(idCliente));
    }
    @RequestMapping(value = "/{id}/pesquisa", method = RequestMethod.POST)
    public ResponseEntity<List<Demanda>> listarPesquisa(@PathVariable("id") Long idCliente, @RequestBody Demanda demandaPesquisa){
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listarPesquisa(idCliente, demandaPesquisa));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@PathVariable("id")Long idCliente, @Valid @RequestBody Demanda demanda){
        Usuario cliente = usuarioService.buscarCliente(idCliente);
        demanda.setCliente(cliente);
        demanda = demandaService.salvar(demanda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(demanda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/{idDemanda}",method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id, @PathVariable("idDemanda") Long idDemanda ){
        Demanda demanda =  null;
        demanda = demandaService.buscar(idDemanda, id);

        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(demanda);
    }

    @RequestMapping(value = "/{idDemanda}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("idDemanda") Long id){
        demandaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Demanda demanda, @PathVariable("id") Long id){
        demanda.setId(id);
        demandaService.atualizar(demanda);
        return ResponseEntity.noContent().build();
    }
}
