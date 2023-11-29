/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author cesar
 */
public class ViajeTaxi extends Servicio {
    private int numPersonas;
    //CONSTRUCTORES
    public ViajeTaxi(){
        
    }
    public ViajeTaxi(Ruta ruta, Date fecha, String hora, Conductor conductor, int numPersonas){
        super(ruta, fecha, hora, conductor);
        this.numPersonas = numPersonas;
    }
    
    //GETTERS Y SETTERS
    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }
    
    //METODOS
    @Override
    public String toString(){
        return """
               /************************************************************/
               Tipo: Viaje
               Cantidad de pasajeros:""" + numPersonas
                + super.toString();
    }
    
    @Override
    public double[] calcularValorAPagar(){
        Random rd = new Random();
        int kms = rd.nextInt(41)+5;
        double subtotal = kms*0.5;
        double total = kms;
        double[] valoresAPagar = {subtotal, total};
        System.out.println("Subotal a pagar por el servicio: "+subtotal);
        return valoresAPagar;
    }
    public double[] calcularValorAPagar(String TC){
        double[] valores = calcularValorAPagar();
        double subtotal = valores[0];
        double total = valores[1] + subtotal*0.15;
        double[] valoresAPagar = {subtotal, total};
        return valoresAPagar;
    }
}
