package Principal;

import static Principal.Sistema.crearUsuariosDelSistema;
import Principal.enums.TipoEstado;
import Principal.enums.TipoFormaPago;
import Principal.enums.TipoServicio;
import Principal.enums.TipoVehiculo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author frank
 */
public class Cliente extends Usuario {

    private String numTarjetaCredito;

    public Cliente() {
    }

    public Cliente(String numCedula, String nombre, String apellido, String user, String contrasena,String celular) {
        super(numCedula, nombre, apellido, user, contrasena,celular);
    }

    public String getNumTarjetaCredito() {
        return numTarjetaCredito;
    }

    public void setNumTarjetaCredito(String numTarjetaCredito) {
        this.numTarjetaCredito = numTarjetaCredito;
    }
    
    
    //METODO SOLICITAR SERVICIO TAXI
    public void servicioViajeTaxi(){
        Scanner sc = new Scanner(System.in);
        //CREACION DE LA RUTA
        System.out.println("INGRESE DATOS DE LA RUTA");
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        Ruta ruta = new Ruta(origen, destino);
        //CREACION DE LA FECHA
        System.out.println("INGRESE DATOS DE LA FECHA DEL VIAJE");
        System.out.print("Ingrese el año: ");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el mes: ");
        int mes = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el dia: ");
        int dia = sc.nextInt();
        sc.nextLine();
        Date fecha = new Date(anio, mes, dia);
        System.out.println("Ingrese la hora con el formato HH:mm");
        String hora = sc.nextLine();
        //INGRESO METODO DE PAGO
        System.out.println("INGRESE LA FORMA DE PAGO");
        String opcion;
        TipoFormaPago formaPago = TipoFormaPago.E;
        do{
            System.out.print("Desea pagar con Tarjeta de Credito? (S/N): ");
            opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("S")){
                System.out.println("Eligio TARJETA DE CREDITO como metodo de pago");
                formaPago = TipoFormaPago.TC;
            }else if(opcion.equalsIgnoreCase("N")){
                System.out.println("Se selecciono EFECTIVO como metodo de pago");
                formaPago = TipoFormaPago.E;
            }else{
                System.out.println("La opcion ingresada no es correcta");
            }
        }while(!opcion.equalsIgnoreCase("S")&&!opcion.equalsIgnoreCase("N"));
        //INGRESO NUMERO DE VIAJEROS
        System.out.println("INGRESE LA CANTIDAD DE VIAJEROS");
        int cantidadViajeros = sc.nextInt();
        sc.nextLine();
        //CALCULO DEL VALOR A PAGAR
        Random rd = new Random();
        int kms = rd.nextInt(41)+5;
        double valorAPagar = kms*0.5;
        if (formaPago == TipoFormaPago.TC){
            valorAPagar += valorAPagar*0.15;
        }
        //SELECCIÓN DE CONDUCTOR DISPONIBLE
        Conductor conductor = new Conductor();
        ArrayList<Usuario> usuariosSistema = Sistema.crearUsuariosDelSistema();
        for (Usuario usuario: usuariosSistema){
            if (usuario instanceof Conductor){
                Conductor conductorDC = (Conductor)usuario;
                if (conductorDC.getEstado()==TipoEstado.D && conductorDC.getVehiculo().getTipoVehiculo()==TipoVehiculo.A){
                    conductor = conductorDC;
                    break;
                }
            }
        }
        //CONFIRMACIÓN
        String opcion2;
        do{
            System.out.print("Está seguro de confirmar su viaje? (S/N):");
            opcion2 = sc.nextLine();
            switch (opcion2) {
                case "S" -> {
                    System.out.println("Servicio confirmado");
                    conductor.setEstado("O");
                    ViajeTaxi servicioTaxi = new ViajeTaxi(ruta,fecha,hora,conductor,TipoServicio.T,valorAPagar,cantidadViajeros);
                }
                case "N" -> System.out.println("Servicio cancelado");
                default -> System.out.println("La opción ingresada no es correcta");
            }
        }while(!opcion2.equalsIgnoreCase("S")&&!opcion2.equalsIgnoreCase("N"));
        
    }
}
