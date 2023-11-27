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
public class ViajeTaxi extends Servicios {
    private int numPersonas;
    //CONSTRUCTOR
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
    public double calcularValorAPagar(){
        Random rd = new Random();
        int kms = rd.nextInt(41)+5;
        return kms*0.5;
    }
    public double calcularValorAPagar(String TC){
        double valorAPagar = calcularValorAPagar();
        valorAPagar += valorAPagar*0.15;
        return valorAPagar;
    }
}
