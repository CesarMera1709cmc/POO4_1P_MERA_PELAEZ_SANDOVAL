/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoServicio;
import java.util.Date;

/**
 *
 * @author cesar
 */
public abstract class Servicios {
    protected Ruta ruta;
    protected String fecha  ;
    protected Conductor conductor;
    protected TipoServicio tipoServicio;
    protected double valorPagar;
    
    //CONSTRUCTORES
    public Servicios(){
        
    }
    public Servicios(Ruta ruta, String fecha, Conductor conductor, TipoServicio tipoServicio, double valorPagar){
        this.ruta = ruta;
        this.fecha = fecha;
        this.conductor = conductor;
        this.tipoServicio = tipoServicio;
        this.valorPagar = valorPagar;
    }
    public String toString(){
        return "/***********************************************/\nTipo: "+tipoServicio+"\nFecha: "+fecha+"\n"+ruta;
    }
    
    
    
    //GETTERS Y SETTERS
    
    public String getFecha() {
        return fecha;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }
    
    
    
            
}
