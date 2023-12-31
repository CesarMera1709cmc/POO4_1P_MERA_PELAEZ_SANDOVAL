/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.util.ArrayList;


/**
 *
 * @author frank
 */
public abstract class Usuario {
    
    protected String numCedula;
    protected String nombre;
    protected String apellido;
    protected String user;
    protected String contrasena;
    protected String celular;
    protected int edad;
    
    public Usuario() {
    }

    public Usuario(String numCedula, String nombre, String apellido, String user, String contrasena, String celular) {
        this.numCedula = numCedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.contrasena = contrasena;
        this.celular= celular;
    }
    
    

    // Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumCedula() {
        return numCedula;
    }

    public void setNumCedula(String numCedula) {
        this.numCedula = numCedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

     public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    
    //METODO ABSTRACTO CONSULTAR SERVICIOS
    public abstract void consultarServicios();
    
    /*
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", numCedula=" + numCedula
                + ", contrasena=" + contrasena + ", user=" + user + ", tipoUsuario=" + tipoUsuario + '}';

    }
    */



   
}
