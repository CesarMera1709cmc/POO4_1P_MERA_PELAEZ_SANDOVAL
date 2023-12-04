/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaqueteServicio;

import PaqueteEnums.TipoFormaPago;
import Principal.Cliente;
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
    public Pago(Servicio servicio, Cliente cliente) {

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

    /**
     *
     * Este metodo lee el archivo "pagos.txt" para obtener los números de pago
     * ya existentes, luego genera aleatoriamente un número de pago y verifica
     * que sea unico
     *
     * @return Un numero de pago unico y no utilizado previamente
     *
     */
    public static int generarNumeroPagoUnico() {

        ArrayList<String> datosServicios = ManejoArchivos.LeeFichero("pagos.txt");

        ArrayList<Integer> numeros = new ArrayList<>();

        datosServicios.remove(0);

        if (!numeros.isEmpty()) {

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

    /**
     * Este metodo recibe un objeto Servicio, un objeto Cliente y el subtotal
     * del pago. Utiliza esta información para crear un objeto Pago, establece
     * el subtotal y guarda la información del pago en el archivo "pagos.txt".
     * Luego, retorna el objeto Pago creado.
     *
     * @param servicio El servicio asociado al pago.
     * @param cliente El cliente que realiza el pago.
     * @param subtotal El subtotal del pago.
     * @return El objeto Pago creado y guardado en el archivo "pagos.txt".
     *
     */
    public static Pago guardarPago(Servicio servicio, Cliente cliente, double subtotal) {

        Pago pago = new Pago(servicio, cliente);

        pago.setSubtotal(subtotal);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

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
