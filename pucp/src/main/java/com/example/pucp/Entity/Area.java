package com.example.pucp.Entity;

import javax.persistence.*;

@Entity
@Table(name="areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idarea")
    private int idArea;
    private String nombrearea;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombrearea) {
        this.nombrearea = nombrearea;
    }
}
