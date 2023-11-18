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
public class Servicios {
    protected Ruta ruta;
    protected Date fecha  ;
    protected Conductor conductor;
    protected TipoServicio tipoServicio;
    protected double valorPagar;

    public Date getFecha() {
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

    public void setFecha(Date fecha) {
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
