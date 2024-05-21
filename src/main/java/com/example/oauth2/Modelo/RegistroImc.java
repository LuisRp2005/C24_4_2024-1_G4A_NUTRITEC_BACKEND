package com.example.oauth2.Modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "nut_registro_imc")
public class RegistroImc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_imc")
    private Integer idRegistroImc;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_imc", referencedColumnName = "id_tipo_imc")
    private TipoIMC tipoImc;

    @Column(name = "fecha_hora_registro")
    private LocalDateTime fechaHoraRegistro;

    public Integer getIdRegistroImc() {
        return idRegistroImc;
    }

    public void setIdRegistroImc(Integer idRegistroImc) {
        this.idRegistroImc = idRegistroImc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoIMC getTipoImc() {
        return tipoImc;
    }

    public void setTipoImc(TipoIMC tipoImc) {
        this.tipoImc = tipoImc;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }
}
