/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Principal.enums.TipoFormaPago;
import Principal.lecturaArchivos.ManejoArchivos;
import java.text.SimpleDateFormat;
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
    private double subtotal;
    private double valorAPagar;
    private TipoFormaPago tipoFormaPago;
    private Cliente cliente;
    
    //CONSTRUCTORES

    public Pago(Servicio servicio, Cliente cliente){
        
        idPago = generarNumeroPagoUnico();
        
        fechaPago = servicio.getFecha();
        
        numeroServicio = servicio.getNumeroServicio();
        
        valorAPagar = servicio.getValorPagar();
        
        tipoFormaPago = servicio.getTipoFormaPago();
        
        this.cliente = cliente;
        
    }
    
    //GETTERS Y SETTERS

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(int numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public TipoFormaPago getTipoFormaPago() {
        return tipoFormaPago;
    }

    public void setTipoFormaPago(TipoFormaPago tipoFormaPago) {
        this.tipoFormaPago = tipoFormaPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
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
    
    //METODO CREAR PAGO
    public static Pago guardarPago(Servicio servicio, Cliente cliente, double subtotal){
        
        Pago pago = new Pago(servicio, cliente);
        
        pago.setSubtotal(subtotal);
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/aaaa");
        
        String linea = pago.getIdPago() + ","
                + formatoFecha.format(pago.getFechaPago()) + ","
                + pago.getNumeroServicio() + ","
                + pago.getTipoFormaPago() + ","
                + pago.getCliente().getNumCedula() + ","
                + pago.getSubtotal() + ","
                + pago.getValorAPagar();
        
        ManejoArchivos.EscribirArchivo("pagos.txt", linea);
        
        return pago;
    }
    
}
