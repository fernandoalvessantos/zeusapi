package com.fernando.zeus.zeusapi.repository;

import com.fernando.zeus.zeusapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
