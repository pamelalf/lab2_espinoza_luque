package com.example.pucp.Repository;

import com.example.pucp.Entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository   extends JpaRepository<Area,Integer> {

}