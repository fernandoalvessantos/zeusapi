package com.fernando.zeus.zeusapi.services;

import com.fernando.zeus.zeusapi.domain.Demanda;
import com.fernando.zeus.zeusapi.domain.DemandaGerente;
import com.fernando.zeus.zeusapi.domain.Usuario;
import com.fernando.zeus.zeusapi.repository.DemandaRepository;
import com.fernando.zeus.zeusapi.services.exceptions.DemandaNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DemandaService {

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Demanda> listar() {
        return demandaRepository.findAll();
    }

    public List<Demanda> listar(Long idCliente) {
        return demandaRepository.listarPorIdCliente(idCliente);
    }

    public List<Demanda> listarPesquisa(Long idCliente, Demanda demanda) {
        return demandaRepository.listaPesquisaPorCliente(idCliente,
                demanda.getId(),
                demanda.getNome(),
                demanda.getDescricao(),
                demanda.getSituacao());
    }

    public List<Demanda> listarNaoRelacionadas() {
        return demandaRepository.listaNaoRelacionadas();
    }

    public List<Demanda> listaPesquisaNaoRelacionadas(DemandaGerente demandaGerente) {
        return demandaRepository.listaPesquisaNaoRelacionada(demandaGerente.getIdDemanda(),
                demandaGerente.getNome(),
                demandaGerente.getDescricao(),
                demandaGerente.getNomeCliente());
    }

    public Demanda buscar(Long id) {
        Optional<Demanda> demandaOpt = demandaRepository.findById(id);
        if (!demandaOpt.isPresent()) {
            throw new DemandaNaoEncontradoException("Demanda não foi encontrado");
        }
        return demandaOpt.get();
    }

    public Demanda buscar(Long id, Long idCliente) {
        Optional<Demanda> demandaOpt = demandaRepository.buscaPorIdeIdCliente(id, idCliente);
        if (!demandaOpt.isPresent()) {
            throw new DemandaNaoEncontradoException("Demanda não foi encontrado");
        }
        return demandaOpt.get();
    }


    public Demanda salvar(Demanda demanda) {
        demanda.setId(null);
        demanda.setDataCadastro(new Date());
        demanda.setSituacao(1); //Novo
        return demandaRepository.save(demanda);
    }

    public void deletar(Long id) {
        try {

            demandaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DemandaNaoEncontradoException("Demanda não encontrada");
        }
    }

    public void cancelar(Long id) {
        try {
            Demanda demanda = this.buscar(id);
            demanda.setSituacao(6);
            this.atualizar(demanda);
        } catch (EmptyResultDataAccessException e) {
            throw new DemandaNaoEncontradoException("Demanda não encontrada");
        }
    }

    public void atualizar(Demanda demanda) {
        this.verificarExistencia(demanda);
        demandaRepository.save(demanda);
    }

    private void verificarExistencia(Demanda demanda) {
        this.buscar(demanda.getId());
    }

    public void relacionarDemandaGerente(DemandaGerente demandaGerente) {
        Demanda demanda = this.buscar(demandaGerente.getIdDemanda());
        Usuario usuario = usuarioService.buscarGerente(demandaGerente.getIdGerente());
        demanda.setGerente(usuario);
        demandaRepository.save(demanda);
    }

    public List<Demanda> listaPorGerente(Long idGerente) {
        return demandaRepository.listaPorGerente(idGerente);
    }

    public List<Demanda> listaPorGerentePesquisa(Long idGerente, DemandaGerente demandaGerente) {
        return demandaRepository.listaPesquisaPorGerente(demandaGerente.getIdDemanda(),
                demandaGerente.getNome(),
                demandaGerente.getDescricao(),
                demandaGerente.getNomeCliente(),
                demandaGerente.getSituacao(), idGerente);
    }

}
