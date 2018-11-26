package com.fernando.zeus.zeusapi.resources;

import com.fernando.zeus.zeusapi.domain.Demanda;
import com.fernando.zeus.zeusapi.services.DemandaService;
import com.fernando.zeus.zeusapi.services.exceptions.DemandaNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/demandas")
public class DemandasResources {

    @Autowired
    private DemandaService demandaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Demanda>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Demanda demanda){
        demanda = demandaService.salvar(demanda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(demanda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id){
        Demanda demanda =  null;
        demanda = demandaService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(demanda);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        demandaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualziar(@RequestBody Demanda demanda, @PathVariable("id") Long id){
        demanda.setId(id);
        demandaService.atualizar(demanda);
        return ResponseEntity.noContent().build();
    }
}
