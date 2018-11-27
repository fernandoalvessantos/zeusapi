package com.fernando.zeus.zeusapi.repository;

import com.fernando.zeus.zeusapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.perfil = 'CLIENTE'")
    List<Usuario> listaUsuariosClientes();

    @Query("SELECT u FROM Usuario u WHERE u.perfil = 'GERENTE'")
    List<Usuario> listaUsuariosGerentes();

    @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.perfil = :perfil")
    Optional<Usuario> buscaUsuarioPorIdePerfil(@Param("id") Long id, @Param("perfil") String perfil);
}
