package com.fernando.zeus.zeusapi.services;

import com.fernando.zeus.zeusapi.domain.Usuario;
import com.fernando.zeus.zeusapi.repository.UsuarioRepository;
import com.fernando.zeus.zeusapi.services.exceptions.UsuarioExistenteException;
import com.fernando.zeus.zeusapi.services.exceptions.UsuarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario){
        if(usuario.getId() != null){
            Usuario u = usuarioRepository.findById(usuario.getId()).get();

            if(u != null){
                throw new UsuarioExistenteException("Usuário já existe");
            }
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id){
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if(!opt.isPresent()){
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
        return opt.get();
    }
}
