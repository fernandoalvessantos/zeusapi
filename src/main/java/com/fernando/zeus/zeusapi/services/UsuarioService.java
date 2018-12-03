package com.fernando.zeus.zeusapi.services;

import com.fernando.zeus.zeusapi.domain.Login;
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

    public static final String PERFIL_CLIENTE = "CLIENTE";
    public static final String PERFIL_GERENTE = "GERENTE";
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarClientes(String perfil){
        if(PERFIL_CLIENTE.equals(perfil)){
            return usuarioRepository.listaUsuariosClientes();
        }else{
            return usuarioRepository.listaUsuariosGerentes();
        }
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

    public Usuario buscarCliente(Long id){
        Optional<Usuario> opt = usuarioRepository.buscaUsuarioPorIdePerfil(id, UsuarioService.PERFIL_CLIENTE);
        if(!opt.isPresent()){
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
        return opt.get();
    }

    public Usuario buscarGerente(Long id){
        Optional<Usuario> opt = usuarioRepository.buscaUsuarioPorIdePerfil(id, UsuarioService.PERFIL_GERENTE);
        if(!opt.isPresent()){
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
        return opt.get();
    }

    public Usuario realizarLogin(Login login){
        Optional<Usuario> opt = usuarioRepository.buscaLogin(login.getEmail(), login.getSenha());
        if(!opt.isPresent()){
            throw new UsuarioNaoEncontradoException("Login e/ou senha inválidos");
        }
        return opt.get();
    }
}
