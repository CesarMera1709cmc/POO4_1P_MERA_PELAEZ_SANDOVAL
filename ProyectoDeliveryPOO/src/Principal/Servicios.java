/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.util.Date;

/**
 *
 * @author cesar
 */
public abstract class Servicios {
    protected int numeroServicio;
    protected Ruta ruta;
    protected Date fecha;
    protected String hora;
    protected Conductor conductor;
    protected double valorPagar;
    //CONSTRUCTORES
    public Servicios(){
        
    }

    public Servicios(Ruta ruta, Date fecha, String hora, Conductor conductor){
        this.ruta = ruta;
        this.fecha = fecha;
        this.hora = hora;
        this.conductor = conductor;
    }
    //METODOS
    @Override
    public String toString(){
        return "/***********************************************\nFecha: "+fecha+"\nHora: "+hora+"\n"+ruta;
    }
    public abstract double calcularValorAPagar();
    //GETTERS Y SETTERS
    
    public Date getFecha() {
        return fecha;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public int getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(int numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
              
    
            
}
