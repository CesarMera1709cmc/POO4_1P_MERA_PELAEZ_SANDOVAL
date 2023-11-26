/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoEncomienda;
import Principal.enums.TipoServicio;
import java.util.Date;

/**
 *
 * @author cesar
 */
public class Encomienda extends Servicios {
    private int cantProductos;
    private TipoEncomienda tipoEncomienda;
    private double pesoKG;
    protected String hora;

    public Encomienda(Ruta ruta, Date fecha, Conductor conductor, double valorPagar,int cantProductos, TipoEncomienda tipoEncomienda, double pesoKG, String hora) {
        super(ruta,fecha,hora,conductor,valorPagar);
        this.cantProductos = cantProductos;
        this.tipoEncomienda = tipoEncomienda;
        this.pesoKG = pesoKG;
    }
    
    public int getCantProductos() {
        return cantProductos;
    }
    public String toString(){
        return super.toString()+"\nTipo encomienda"+tipoEncomienda+"\nCantidad"+cantProductos+"\nHora:"+hora;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    public TipoEncomienda getTipoEncomienda() {
        return tipoEncomienda;
    }

    public void setTipoEncomienda(TipoEncomienda tipoEncomienda) {
        this.tipoEncomienda = tipoEncomienda;
    }

    public double getPesoKG() {
        return pesoKG;
    }

    public void setPesoKG(double pesoKG) {
        this.pesoKG = pesoKG;
    }
    
}
