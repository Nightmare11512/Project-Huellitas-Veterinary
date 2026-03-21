package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "estados")
public class EstadoModel {
    
        @Id
        @Column(name = "idEstado")
        private int idEstado;
        private String nombre;

        @ManyToOne
        @JoinColumn(name = "idPais")
        private PaisModel pais;

        public EstadoModel(int idEstado, String nombre, PaisModel pais) {
            this.idEstado = idEstado;
            this.nombre = nombre;
            this.pais = pais;
        }

        public EstadoModel() {
        }

        public int getIdEstado() {
            return idEstado;
        }

        public void setIdEstado(int idEstado) {
            this.idEstado = idEstado;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public PaisModel getPais() {
            return pais;
        }
        
        public void setPais(PaisModel pais) {
            this.pais = pais;
        }

        @Override
        public String toString() {
            return "[idEstado=" + idEstado + ", nombre=" + nombre + ", pais=" + pais.getIdPais() + "]";
        }
}