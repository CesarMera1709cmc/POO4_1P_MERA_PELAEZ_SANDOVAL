/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoVehiculo;

/**
 *
 * @author frank
 */
public class Vehiculo {
    private int codigoVehiculo;
    private String placa;
    private String modelo;
    private String marca;
    private TipoVehiculo tipoVehiculo;

    public Vehiculo(String codigoVehiculo, String placa, String modelo, String marca, String tipoVehiculo) {
        this.codigoVehiculo = Integer.parseInt(codigoVehiculo);
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.tipoVehiculo = TipoVehiculo.valueOf(tipoVehiculo);
    }
    
    //GETTERS Y SETTERS
    public int getCodigoVehiculo(){
        return this.codigoVehiculo;
    }
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
    public void setCodigoVehiculo(String codigoVehiculo){
        this.codigoVehiculo = Integer.parseInt(codigoVehiculo);
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
    //TO STRING
    @Override
    public String toString(){
        return "Vehiculo "+marca+" "+modelo+
                "\nPlaca: "+placa;
    }
}
