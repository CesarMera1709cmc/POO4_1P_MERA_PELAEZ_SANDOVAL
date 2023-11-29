/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoFormaPago;
import Principal.lecturaArchivos.ManejoArchivos;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Pc
 */
public class Pago {
    private int idPago;
    private Date fechaPago; //CAMBIAR A DATE
    private int numeroServicio;
    private double valorAPagar;
    private TipoFormaPago tipoFormaPago;
    private Cliente cliente;
    
    //GETTERS Y SETTERS
    public int getIdPago(){
        return this.idPago;
    }
    public Date getFechaPago(){
        return this.fechaPago;
    }
    public int getNumeroServicio(){
        return this.numeroServicio;
    }
    public double getValorAPagar(){
        return this.valorAPagar;
    }
    public TipoFormaPago getTipoFormaPago(){
        return this.tipoFormaPago;
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    public void setIdPago(int idPago){
        this.idPago = idPago;
    }
    public void setFechaPago(Date fechaPago){
        this.fechaPago = fechaPago;
    }
    public void setNumeroServicio(int numeroServicio){
        this.numeroServicio = numeroServicio;
    }
    public void setValorAPagar(double valorAPagar){
        this.valorAPagar = valorAPagar;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    //METODOS
    
    public static int generarNumeroPagoUnico(){
        
        ArrayList<String> datosServicios = ManejoArchivos.LeeFichero("pagos.txt");

        ArrayList<Integer> numeros = new ArrayList<>();

        datosServicios.remove(0);
        
        if (!numeros.isEmpty()){
            
            for (String lineaServicios : datosServicios) {

                String[] datos = lineaServicios.split(",");

                numeros.add(Integer.valueOf(datos[0]));
            }
            
        }
        boolean validez = true;

        do {
            Random rd = new Random();
            int numServicio = rd.nextInt(899) + 100;

            if (!numeros.contains(numServicio)) {
                validez = false;
                return numServicio;
            }
        } while (validez);

        return 0;
    }
    
}
