package com.helalubo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.helalubo.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{

}
