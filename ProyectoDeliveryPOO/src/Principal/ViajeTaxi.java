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
    private int distancia;
    
    //CONSTRUCTOR
    public ViajeTaxi(){
        
    }

    public ViajeTaxi(int numeroServicio, String cedulaCliente, Conductor conductor, Ruta ruta, Date fecha, String hora,String numPersonas, String distancia){
        super(numeroServicio, cedulaCliente, conductor, ruta, fecha, hora);
        this.numPersonas = Integer.parseInt(numPersonas);
        this.distancia = Integer.parseInt(distancia);
    }
    
    //GETTERS Y SETTERS
    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    //METODOS
    @Override
    public String toString(){
        return """
               /************************************************************/
               Tipo: Viaje
               Cantidad de pasajeros:"""+" "+numPersonas
                + super.toString();
    }
    
    @Override
    public double[] calcularValorAPagar(){
        Random rd = new Random();
        int distanciaKm = rd.nextInt(41)+5;
        this.setDistancia(distanciaKm);
        double subtotal = distanciaKm*0.5;
        double total = distanciaKm;
        double[] valoresAPagar = {subtotal, total};
        System.out.println("Subotal a pagar por el servicio: "+subtotal+" USD");
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
