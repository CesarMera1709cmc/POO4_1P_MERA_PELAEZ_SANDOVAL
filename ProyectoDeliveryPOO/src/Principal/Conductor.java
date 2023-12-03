/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoEstado;
import java.util.ArrayList;

/**
 *
 * @author frank
 */
public class Conductor extends Usuario {

    private Vehiculo vehiculo;
    private TipoEstado estado;

    public Conductor() {
    }

    public Conductor(String numCedula, String nombre, String apellido, String user, String contrasena, String celular) {
        super(numCedula, nombre, apellido, user, contrasena, celular);
    }

    // Getters & Setters  ----> Conductor
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = TipoEstado.valueOf(estado);
    }

    //TO STRING
    @Override
    public String toString() {
        return nombre + " " + apellido + " " + vehiculo;
    }

    @Override
    public void consultarServicios() {
        System.out.println("/*********************SERVICIOS ASIGNADOS********************/");
        System.out.println("/                                                           */");
        System.out.println("/************************************************************/");

        boolean serviciosEncontrados = false;

        for (Servicio servicio : Sistema.servicios) {
            if (this.equals(servicio.getConductor())) {
                System.out.println(servicio);
                serviciosEncontrados = true;
            }
        }

        if (!serviciosEncontrados) {
            System.out.println("NO HAY NINGUN SERVICIO ASIGNADO HASTA AHORA");
        }
    }

}
