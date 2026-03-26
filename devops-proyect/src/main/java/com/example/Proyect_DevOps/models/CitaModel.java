package com.example.Proyect_DevOps.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "citas")
public class CitaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;

    @ManyToOne
    @JoinColumn(name = "IdSucursal")
    private SucursalModel sucursalModel;

    @ManyToOne
    @JoinColumn(name = "IdMascota")
    private MascotaModel mascotaModel;

    @ManyToOne
    @JoinColumn(name = "IdUsuario")
    private UsuarioModel usuarioModel;

    private LocalDate fecha;
    private int estado_cita;

    public CitaModel(int idCita, SucursalModel sucursalModel, MascotaModel mascotaModel, UsuarioModel usuarioModel, LocalDate fecha, int estado_cita){
        this.idCita = idCita;
        this.sucursalModel = sucursalModel;
        this.mascotaModel = mascotaModel;
        this.usuarioModel = usuarioModel;
        this.fecha = fecha;
        this.estado_cita = estado_cita;
    }

    public CitaModel(){
    }

    public int getIdServicio(){
        return idCita;
    }

    public void setIdServicio(int idServicio){
        this.idCita = idServicio;
    }

    public SucursalModel getSucursalModel(){
        return sucursalModel;
    }

    public void setSucursalModel(SucursalModel sucursalModel){
        this.sucursalModel = sucursalModel;
    }

    public MascotaModel getMascotaModel(){
        return mascotaModel;
    }

    public void setMasotaModel(MascotaModel mascotaModel){
        this.mascotaModel = mascotaModel;
    }

    public UsuarioModel getUsuarioModel(){
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel){
        this.usuarioModel = usuarioModel;
    }

    public LocalDate getFecha(){
        return fecha;
    }

    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    public int getEstadoCita(){
        return estado_cita;
    }

    public void setEstadoCita(int estado_cita){
        this.estado_cita = estado_cita;
    }

    @Override
    public String toString(){
        return "idCita=" + idCita + 
        ", idSucursal=" + sucursalModel.getIdSucursal() + 
        ", idMascota=" + mascotaModel.getIdMascota() + 
        ", idUsuario=" + usuarioModel.getIdUsuario() + 
        ", fecha=" + fecha.toString() + ", estado_cita=" + estado_cita;
    }
}
