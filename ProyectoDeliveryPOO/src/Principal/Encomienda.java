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

    
    //CONSTRUCTOR
    public Encomienda(){
        
    }
    public Encomienda(int numeroServicio, String cedulaCliente, Conductor conductor, Ruta ruta, Date fecha, String hora,String tipoEncomienda, String cantProductos, String pesoKG){
        super(numeroServicio, cedulaCliente, conductor, ruta, fecha, hora);
        this.tipoEncomienda = TipoEncomienda.valueOf(tipoEncomienda);
        this.cantProductos = Integer.parseInt(cantProductos);
        this.pesoKG = Double.parseDouble(pesoKG);
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
               Tipo Encomienda:"""+" "+tipoEncomienda
                + "\nCantidad: " + cantProductos
                + super.toString();
    }
    
    
    //METODO CALCULAR VALOR A PAGAR
    @Override
    public double[] calcularValorAPagar(){
        double subtotal = this.cantProductos + 4;
        double total = subtotal;
        double[] valoresAPagar = {subtotal, total};
        System.out.println("Subotal a pagar por el servicio: "+subtotal+" USD");
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
