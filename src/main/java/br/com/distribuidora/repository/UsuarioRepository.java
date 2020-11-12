package br.com.distribuidora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.distribuidora.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
