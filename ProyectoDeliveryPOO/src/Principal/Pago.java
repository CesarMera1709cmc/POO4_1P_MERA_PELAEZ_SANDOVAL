/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoFormaPago;
import java.util.Date;

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
}
