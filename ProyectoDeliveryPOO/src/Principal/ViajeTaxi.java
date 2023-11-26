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
public class ViajeTaxi extends Servicios {
    private int numPersonas;
    //CONSTRUCTOR
    public ViajeTaxi(){
        
    }
    public ViajeTaxi(Ruta ruta, Date fecha, String hora, Conductor conductor, TipoServicio tipoServicio, double valorPagar, int numPersonas){
        super(ruta, fecha, hora, conductor, tipoServicio, valorPagar);
        this.numPersonas = numPersonas;
    }
    //GETTERS Y SETTERS
    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }
    
}
