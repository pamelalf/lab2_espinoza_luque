package com.example.pucp.Repository;

import com.example.pucp.Entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import sun.management.jdp.JdpPacket;

@Repository
public interface ActividadRepository  extends JpaRepository<Actividad, Integer> {
}
