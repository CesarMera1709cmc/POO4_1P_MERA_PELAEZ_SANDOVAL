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
    private int edad;
    private TipoEstado estado;

    public Conductor() {
    }

    public void consultarServiciosAsignados(ArrayList<Servicios> serviciosconductor){
    System.out.println("/*********************SERVICIOS ASIGNADOS******************/");
    System.out.println("/                                                         */");
    System.out.println("/**********************************************************/");
    for (Servicios servicio : serviciosconductor) {
        if(this.equals(servicio.conductor)){
            if (servicio instanceof Encomienda) {
            Encomienda encomienda = (Encomienda) servicio;
            System.out.println( encomienda); 
        } else if (servicio instanceof ViajeTaxi) {
            ViajeTaxi viajeTaxi = (ViajeTaxi) servicio;
            System.out.println(viajeTaxi);
        } else {
            System.out.println("Tipo de servicio desconocido");
        }
        }
    }
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
