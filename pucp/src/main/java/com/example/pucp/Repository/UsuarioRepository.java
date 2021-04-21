package com.example.pucp.Repository;

import com.example.pucp.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    List<Usuario> findByIdarea(int id);

}
