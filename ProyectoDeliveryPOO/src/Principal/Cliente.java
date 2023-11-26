package Principal;

import Principal.enums.TipoEstado;
import Principal.enums.TipoFormaPago;
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
    public Servicios solicitarViajeTaxi(){
        Scanner sc = new Scanner(System.in);
        //CREACION DE LA RUTA
        System.out.println("\nINGRESE DATOS DE LA RUTA");
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        Ruta ruta = new Ruta(origen, destino);
        //CREACION DE LA FECHA
        System.out.println("\nINGRESE DATOS DE LA FECHA DEL VIAJE");
        boolean entradaValida = false;
        int anio = 0;
        int mes = 0;
        int dia = 0;
        do{
            System.out.print("Ingrese el anio: ");
            if (sc.hasNextInt()){
                anio = sc.nextInt();
                if (anio>=2023){
                    entradaValida = true;
                }else{
                    System.out.println("El anio ingresado no esta en un rango valido");
                }
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        entradaValida = false;
        do{
            System.out.print("Ingrese el mes: ");
            if (sc.hasNextInt()){
                mes = sc.nextInt();
                if (mes>=1 && mes<=12){
                    entradaValida = true;
                }else{
                    System.out.println("El mes ingresado no esta en un rango valido");
                }
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        entradaValida = false;
        do{
            System.out.print("Ingrese el dia: ");
            if (sc.hasNextInt()){
                dia = sc.nextInt();
                if (dia>=1 && dia<=31){
                    entradaValida = true;
                }else{
                    System.out.println("El dia ingresado no esta en un rango valido");
                }
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        Date fecha = new Date(anio, mes, dia);
        int hora = 0;
        int minutos = 0;
        entradaValida = false;
        do{
            System.out.print("Ingrese la hora (0-23):");
            if (sc.hasNextInt()){
                hora = sc.nextInt();
                if (hora>=0 && hora<=23){
                    entradaValida = true;
                }else{
                    System.out.println("La hora ingresada no esta en un rango valido");
                }
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        entradaValida = false;
        do{
            System.out.print("Ingrese los minutos (0-59):");
            if (sc.hasNextInt()){
                minutos = sc.nextInt();
                if (minutos>=0 && minutos<=59){
                    entradaValida = true;
                }else{
                    System.out.println("Los minutos ingresados no estan en un rango valido");
                }
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        String horaMinutos = hora+":"+minutos;
        sc.nextLine();
        //INGRESO METODO DE PAGO
        System.out.println("\nINGRESE LA FORMA DE PAGO");
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
        System.out.println("\nINGRESE LA CANTIDAD DE VIAJEROS");
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
            System.out.print("\nEsta seguro de confirmar su viaje? (S/N):");
            opcion2 = sc.nextLine();
            if (opcion2.equalsIgnoreCase("S")){
                System.out.println("Servicio confirmado");
                conductor.setEstado("O");
                Servicios servicioTaxi = new ViajeTaxi(ruta,fecha,horaMinutos,conductor,valorAPagar,cantidadViajeros);
                return servicioTaxi;
            }else if(opcion2.equalsIgnoreCase("N")){
                System.out.println("SERVICIO CANCELADO");
                return null;
            }else{
                System.out.println("La opción ingresada no es correcta");
            }
        }while(!opcion2.equalsIgnoreCase("S")&&!opcion2.equalsIgnoreCase("N"));
        return null;
    }
}
