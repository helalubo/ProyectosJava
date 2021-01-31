package net.helalubo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.helalubo.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{

}
