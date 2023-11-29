/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoEncomienda;
import java.util.Date;

/**
 *
 * @author cesar
 */
public class Encomienda extends Servicio {
    private int cantProductos;
    private TipoEncomienda tipoEncomienda;
    private double pesoKG;
    protected String hora;

    
    //CONSTRUCTORES
    public Encomienda(){
        
    }
    
    public Encomienda(Ruta ruta, Date fecha, Conductor conductor,int cantProductos, TipoEncomienda tipoEncomienda, double pesoKG, String hora) {
        super(ruta,fecha,hora,conductor);
        this.cantProductos = cantProductos;
        this.tipoEncomienda = tipoEncomienda;
        this.pesoKG = pesoKG;
    }
    
    //GETTERS Y SETTERS
    public int getCantProductos() {
        return cantProductos;
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
    
    //METODOS
    @Override
    public String toString(){
        return """
               /************************************************************/
               Tipo: Viaje
               Tipo Encomienda: """ + tipoEncomienda
                + "\nCantidad: " + cantProductos
                + super.toString();
    }
    
    
    //METODO CALCULAR VALOR A PAGAR
    @Override
    public double[] calcularValorAPagar(){
        double subtotal = this.cantProductos + 4;
        double total = subtotal;
        double[] valoresAPagar = {subtotal, total};
        System.out.println("Subotal a pagar por el servicio: "+subtotal);
        return valoresAPagar;
    }
    public double[] calcularValorAPagar(String TC){
        double[] valores = calcularValorAPagar();
        double subtotal = valores[0];
        double total = valores[1] + subtotal*0.1;
        double[] valoresAPagar = {subtotal, total};
        return valoresAPagar;
    }
    
}
