package com.example.pucp.Repository;

import com.example.pucp.Entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad,Integer> {

}
