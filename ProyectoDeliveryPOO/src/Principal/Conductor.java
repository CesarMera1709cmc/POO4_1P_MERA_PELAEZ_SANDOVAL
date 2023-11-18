/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoEstado;

/**
 *
 * @author frank
 */
public class Conductor extends Usuario {

    private String numLicencia;
    private Vehiculo vehiculo;
    private int edad;
    private TipoEstado estado;

    public Conductor() {
    }

    // Getters & Setters  ----> Conductor
    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = TipoEstado.valueOf(estado);
    }

}
