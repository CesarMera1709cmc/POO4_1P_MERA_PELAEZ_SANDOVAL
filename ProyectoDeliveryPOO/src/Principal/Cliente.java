package Principal;

import Principal.enums.TipoEncomienda;
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
    
    /**
     * Este metodo es utilizado por el cliente para Solicitar un servicio de tipo ViajeTaxi 
     * @return Retorna un Objeto de tipo ViajeTaxi.
     */
    //METODO SOLICITAR SERVICIO TAXI
    
    public Servicio solicitarViajeTaxi(){
        ViajeTaxi servicioTaxi = new ViajeTaxi();
        Scanner sc = new Scanner(System.in);
        
        //INGRESO METODO DE PAGO
        System.out.println("\nINGRESE LA FORMA DE PAGO");
        double[] valorAPagar = new double[2];
        String opcion;
        do{
            System.out.print("Desea pagar con Tarjeta de Credito? (S/N): ");
            opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("S")){
                System.out.println("Eligio TARJETA DE CREDITO como metodo de pago");
                valorAPagar = servicioTaxi.calcularValorAPagar(this.getNumTarjetaCredito());
                servicioTaxi.setValorPagar(valorAPagar[1]); 
            }else if(opcion.equalsIgnoreCase("N")){
                System.out.println("Se selecciono EFECTIVO como metodo de pago");
                valorAPagar = servicioTaxi.calcularValorAPagar();
                servicioTaxi.setValorPagar(valorAPagar[1]);
            }else{
                System.out.println("La opcion ingresada no es correcta");
            }
        }while(!opcion.equalsIgnoreCase("S")&&!opcion.equalsIgnoreCase("N"));
        
        //INGRESO NUMERO DE VIAJEROS
        System.out.println("\nINGRESE LA CANTIDAD DE VIAJEROS");
        boolean entradaValida = false;
        int cantidadViajeros = 0;
        do{
            System.out.print("Cantidad de viajeros: ");
            if (sc.hasNextInt()){
                cantidadViajeros = sc.nextInt();
                sc.nextLine();
                entradaValida = true;
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        
        servicioTaxi.setNumPersonas(cantidadViajeros);
        
        //SELECCIÓN DE CONDUCTOR DISPONIBLE
        Conductor conductor = new Conductor();
        for (Conductor conductorSeleccionado: Sistema.conductores){
            if (conductorSeleccionado.getEstado()==TipoEstado.D && conductorSeleccionado.getVehiculo().getTipoVehiculo()==TipoVehiculo.A){
                conductor = conductorSeleccionado;
                break;
            }
        }
        
        //GENERACION DE NUMERO DE SERVICIO UNICO
        servicioTaxi.setNumeroServicio(Servicio.generarNumeroServicioUnico());
        
        
        //CONFIRMACIÓN
        String opcion2;
        do{
            System.out.print("\nEsta seguro de confirmar su viaje? (S/N): ");
            opcion2 = sc.nextLine();
            if (opcion2.equalsIgnoreCase("S")){
                System.out.println("Servicio confirmado");
                conductor.setEstado("O");
                servicioTaxi.setConductor(conductor);
                return servicioTaxi;
            }else if(opcion2.equalsIgnoreCase("N")){
                System.out.println("SERVICIO CANCELADO");
                return null;
            }else{
                System.out.println("La opcion ingresada no es correcta");
            }
        }while(!opcion2.equalsIgnoreCase("S")&&!opcion2.equalsIgnoreCase("N"));
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //METODO ENTREGA DE ENCOMIENDA
    public Encomienda solicitarEntregaEncomiendas(){
        Encomienda servicioEncomienda = new Encomienda();
        Scanner sc=new Scanner (System.in);
        
        //INGRESO METODO DE PAGO
        System.out.println("\nINGRESE LA FORMA DE PAGO");
        double[] valorAPagar = new double[2];
        String opcion;
        do{
            System.out.print("Desea pagar con Tarjeta de Credito? (S/N): ");
            opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("S")){
                System.out.println("Eligio TARJETA DE CREDITO como metodo de pago");
                valorAPagar = servicioEncomienda.calcularValorAPagar(this.getNumTarjetaCredito());
                servicioEncomienda.setValorPagar(valorAPagar[1]); 
            }else if(opcion.equalsIgnoreCase("N")){
                System.out.println("Se selecciono EFECTIVO como metodo de pago");
                valorAPagar = servicioEncomienda.calcularValorAPagar();
                servicioEncomienda.setValorPagar(valorAPagar[1]);
            }else{
                System.out.println("La opcion ingresada no es correcta");
            }
        }while(!opcion.equalsIgnoreCase("S")&&!opcion.equalsIgnoreCase("N"));
        
        //INGRESO TIPO DE ENCOMIENDA
        System.out.println("\nINGRESE EL TIPO DE ENCOMIENDA");
        boolean entradaValida = false;
        int opcionEncomienda;
        do{
            System.out.println("\t1. Medicamentos\n\t2. Documentos\n\t3. Ropa");
            System.out.print("Ingrese la opcion: ");
            if (sc.hasNextInt()){
                opcionEncomienda = sc.nextInt();
                switch (opcionEncomienda){
                    case 1 -> {
                        servicioEncomienda.setTipoEncomienda(TipoEncomienda.MEDICAMENTOS);
                        entradaValida = true;
                    }
                    case 2 -> {
                        servicioEncomienda.setTipoEncomienda(TipoEncomienda.DOCUMENTOS);
                        entradaValida = true;
                    }
                    case 3 -> {
                        servicioEncomienda.setTipoEncomienda(TipoEncomienda.ROPA);
                        entradaValida = true;
                    }
                    default -> System.out.println("La opcion ingresada no es valida");
                }
                sc.nextLine();
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        
        //INGRESO DATOS DEL ENVIO
        entradaValida = false;
        int cantidadProductos = 0;
        double pesoKGProductos = 0;
        
        System.out.println("INGRESE LOS DATOS DE LOS PRODUCTOS");
        //Ingreso cantidad de productos
        do{
            System.out.print("Ingrese la cantidad de productos: ");
            if (sc.hasNextInt()){
                cantidadProductos = sc.nextInt();
                if (cantidadProductos>0){
                    sc.nextLine();
                    entradaValida = true;
                }else{
                    System.out.println("La cantidad ingresada no es valida");
                }
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        //Ingreso peso de productos
        entradaValida = false;
        do{
            System.out.print("Ingrese el peso en KG del envio: ");
            if (sc.hasNextDouble()){
                pesoKGProductos = sc.nextDouble();
                if (pesoKGProductos>0){
                    sc.nextLine();
                    entradaValida = true;
                }else{
                    System.out.println("La cantidad ingresada no es valida");
                }
            }else{
                System.out.println("Por favor, ingrese un numero entero");
                sc.next();
            }
        }while(!entradaValida);
        servicioEncomienda.setCantProductos(cantidadProductos);
        servicioEncomienda.setPesoKG(pesoKGProductos);
        
        //SELECCIÓN DE CONDUCTOR DISPONIBLE
        Conductor conductor = new Conductor();
        for (Conductor conductorSeleccionado: Sistema.conductores){
            if (conductorSeleccionado.getEstado()==TipoEstado.D && conductorSeleccionado.getVehiculo().getTipoVehiculo()==TipoVehiculo.M){
                conductor = conductorSeleccionado;
                break;
            }
        }
        
        //GENERACION DE NUMERO DE SERVICIO UNICO
        servicioEncomienda.setNumeroServicio(Servicio.generarNumeroServicioUnico());
        
        //CONFIRMACIÓN
        String opcion2;
        do{
            System.out.print("\nEsta seguro de confirmar su viaje? (S/N): ");
            opcion2 = sc.nextLine();
            if (opcion2.equalsIgnoreCase("S")){
                System.out.println("Servicio confirmado");
                conductor.setEstado("O");
                servicioEncomienda.setConductor(conductor);
                return servicioEncomienda;
            }else if(opcion2.equalsIgnoreCase("N")){
                System.out.println("SERVICIO CANCELADO");
                return null;
            }else{
                System.out.println("La opcion ingresada no es correcta");
            }
        }while(!opcion2.equalsIgnoreCase("S")&&!opcion2.equalsIgnoreCase("N"));
        return null; 
    }
      
}
