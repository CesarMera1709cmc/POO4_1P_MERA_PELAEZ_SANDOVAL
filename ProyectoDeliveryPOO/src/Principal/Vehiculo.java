/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author frank
 */
public class Vehiculo {
    private String placa;
    private String modelo;
    private String marca;
    private TipoVehiculo tipoVehiculo;
    //GETTERS Y SETTERS
    public String getPlaca(){
        return this.placa;
    }
    public String getModelo(){
        return this.modelo;
    }
    public String getMarca(){
        return this.marca;
    }
    public TipoVehiculo getTipoVehiculo(){
        return this.tipoVehiculo;
    }
    public void setPlaca(String placa){
        this.placa = placa;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setPlaca(TipoVehiculo tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
    }
}
