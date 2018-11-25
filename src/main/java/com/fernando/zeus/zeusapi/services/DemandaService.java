package com.fernando.zeus.zeusapi.services;

import com.fernando.zeus.zeusapi.domain.Demanda;
import com.fernando.zeus.zeusapi.repository.DemandaRepository;
import com.fernando.zeus.zeusapi.services.exceptions.DemandaNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandaService {

    @Autowired
    private DemandaRepository demandaRepository;

    public List<Demanda> listar(){
        return demandaRepository.findAll();
    }

    public Demanda buscar(Long id){
        Optional<Demanda> demandaOpt = demandaRepository.findById(id);
        if(!demandaOpt.isPresent()){
            throw new DemandaNaoEncontradoException("Demanda não foi encontrado");
        }
        return demandaOpt.get();
    }

    public Demanda salvar(Demanda demanda){
        demanda.setId(null);
        return demandaRepository.save(demanda);
    }

    public void deletar(Long id){
        try{
            demandaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new DemandaNaoEncontradoException("Demanda não encontrada");
        }
    }

    public void atualizar(Demanda demanda){
        this.verificarExistencia(demanda);
        demandaRepository.save(demanda);
    }

    private void verificarExistencia(Demanda demanda){
        this.buscar(demanda.getId());
    }
}
