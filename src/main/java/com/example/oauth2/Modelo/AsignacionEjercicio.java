package com.example.oauth2.Modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "nut_asignacion_ejercicio")
public class AsignacionEjercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion_ejercicio")
    private Integer idAsignacionEjercicio;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_ejercicio", referencedColumnName = "id_ejercicio")
    private Ejercicio ejercicio;

    @Column(name = "fecha_hora_asignacion")
    private LocalDateTime fechaHoraAsignacion;

    public Integer getIdAsignacionEjercicio() {
        return idAsignacionEjercicio;
    }

    public void setIdAsignacionEjercicio(Integer idAsignacionEjercicio) {
        this.idAsignacionEjercicio = idAsignacionEjercicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public LocalDateTime getFechaHoraAsignacion() {
        return fechaHoraAsignacion;
    }

    public void setFechaHoraAsignacion(LocalDateTime fechaHoraAsignacion) {
        this.fechaHoraAsignacion = fechaHoraAsignacion;
    }
}
