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
    private static int contadorServicios = 1000;
    //CONSTRUCTORES
    public Servicios(){
        
    }
    public Servicios(Ruta ruta, Date fecha, String hora, Conductor conductor, double valorPagar){
        this.numeroServicio = contadorServicios++;
        this.ruta = ruta;
        this.fecha = fecha;
        this.hora = hora;
        this.conductor = conductor;
        this.valorPagar = valorPagar;
    }
    @Override
    public String toString(){
        return "/***********************************************\nFecha: "+fecha+"\nHora: "+hora+"\n"+ruta;
    }
    
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

    public Ruta getRuta() {
        return ruta;
    }

    public String getHora() {
        return hora;
    }

    public int getNumeroServicio() {
        return numeroServicio;
    }
    
    
    
    
    
            
}
