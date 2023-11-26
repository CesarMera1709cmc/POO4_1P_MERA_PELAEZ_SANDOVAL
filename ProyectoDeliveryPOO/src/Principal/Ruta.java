/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author Pc
 */
public class Ruta {
    private String origen;
    private String destino;
    //CONSTRUCTORES
    public Ruta(){
        
    }
    public Ruta(String origen, String destino){
        this.origen = origen;
        this.destino = destino;
    }
    //GETTERS Y SETTERS
    public String getOrigen(){
        return this.origen;
    }
    public String getDestino(){
        return this.destino;
    }
    public void setOrigen(String origen){
        this.origen = origen;
    }
    public void setDestino(String destino){
        this.destino = destino;
    }
    @Override
    public String toString(){
        return "Desde: "+origen+"\nHasta: "+destino;
    }
}
