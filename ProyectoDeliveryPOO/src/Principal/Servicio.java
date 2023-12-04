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
import java.util.Scanner;

/**
 *
 * @author cesar
 */
public abstract class Servicio {

    protected int numeroServicio;
    protected Ruta ruta;
    protected Date fecha;
    protected String hora;
    protected Conductor conductor;
    protected double valorPagar;
    protected TipoFormaPago tipoFormaPago;
    protected String cedulaCliente;

    //CONSTRUCTOR
    public Servicio() {
        Scanner sc = new Scanner(System.in);
        
        //CREACION DE LA RUTA
        System.out.println("\nINGRESE DATOS DE LA RUTA");
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        Ruta rutaIngresada = new Ruta(origen, destino);
        ruta = rutaIngresada;
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
            System.out.print("Ingrese el mes (1-12): ");
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
            System.out.print("Ingrese el dia (1-31): ");
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
        
        Date fechaIngresada = new Date(anio, mes, dia);
        
        int horaIngresada = 0;
        int minutos = 0;
        entradaValida = false;
        
        do{
            System.out.print("Ingrese la hora (0-23): ");
            if (sc.hasNextInt()){
                horaIngresada = sc.nextInt();
                if (horaIngresada>=0 && horaIngresada<=23){
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
            System.out.print("Ingrese los minutos (0-59): ");
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
        
        String horaMinutos = horaIngresada+":"+minutos;
        sc.nextLine();
        
        fecha = fechaIngresada;
        hora = horaMinutos;
    }
 
    public Servicio(int numeroServicio, String cedulaCliente, Conductor conductor, Ruta ruta, Date fecha, String hora){
        this.numeroServicio = numeroServicio;
        this.cedulaCliente = cedulaCliente;
        this.conductor = conductor;
        this.ruta = ruta;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    //GETTERS Y SETTERS
    public Date getFecha() {
        return fecha;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public String getHora() {
        return hora;
    }

    public int getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(int numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public TipoFormaPago getTipoFormaPago() {
        return tipoFormaPago;
    }

    public void setTipoFormaPago(TipoFormaPago tipoFormaPago) {
        this.tipoFormaPago = tipoFormaPago;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }
    
    

    //METODOS
    
    @Override
    public String toString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return "\nFecha: " + formatoFecha.format(fecha) + "\nHora: " + hora + ruta;
    }
    
    
    //METODO ABSTRACTO PARA CALCULAR VALOR A PAGAR
    public abstract double[] calcularValorAPagar();
    
    /**
     * Este metodo genera un Identificador Unico para cada objeto que lo necesite (Ingresa al archivo de texto y ve que no se repita)
     * @return Retorna un entero, el cual es el Identificador Unico.
    */
    

    public static int generarNumeroServicioUnico() {

        ArrayList<String> datosServicios = ManejoArchivos.LeeFichero("servicios.txt");

        ArrayList<Integer> numeros = new ArrayList<>();

        datosServicios.remove(0);

        for (String lineaServicios : datosServicios) {

            String[] datos = lineaServicios.split(",");

            numeros.add(Integer.valueOf(datos[0]));

        }

        boolean validez = true;

        do {
            Random rd = new Random();
            int numServicio = rd.nextInt(89999) + 10000;

            if (!numeros.contains(numServicio)) {
                
                validez = false;
                return numServicio;
                
            }
        } while (validez);

        return 0;
    }
    
}
