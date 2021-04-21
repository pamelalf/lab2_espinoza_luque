package com.example.pucp.Repository;

import com.example.pucp.Entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import sun.management.jdp.JdpPacket;

import java.util.List;

@Repository
public interface ActividadRepository  extends JpaRepository<Actividad, Integer> {


    @Query(value = "SELECT * FROM pm_db.actividades where idproyecto= ?1 ;",nativeQuery = true)
    List<Actividad> findporidproyectoq(int id);

}
