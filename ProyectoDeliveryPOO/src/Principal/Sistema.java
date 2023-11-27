package Principal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Principal.enums.TipoEstado;
import Principal.enums.TipoVehiculo;
import Principal.lecturaArchivos.ManejoArchivos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author frank
 */
public class Sistema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(" ++++++++++++++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("         BIENVENIDO AL SISTEMA                       ");
        System.out.println("");
        System.out.println(" ++++++++++++++++++++++++++++++++++++");

        String usuarioConsola;
        String usuarioContrasena;

        do {
            System.out.println("");
            System.out.println("USUARIO: ");
            usuarioConsola = scanner.nextLine();

            System.out.println("CONTRASENA: ");
            usuarioContrasena = scanner.nextLine();

            if (!validarUsuarioSistema(usuarioConsola, usuarioContrasena)) {
                System.out.println("EL USUARIO O CONTRASEÑA INGRESADOS SON INCORRECTOS");
                System.out.println("VUELVA A INGRESAR USUARIO Y CONTRASEÑA");
            }
        } while (!validarUsuarioSistema(usuarioConsola, usuarioContrasena));

        System.out.println("INICIO DE SESION CORRECTO.");
        System.out.println("BIENVENID@ : " + usuarioConsola);

        // UNA VEZ VALIDADO EL USUARIO; ES NECESARIO UN METODO QUE CONSTRUYA UNA LISTA DE    
        // USUARIOS PARA SEGUIR CON EL PROGRAMA 
        ArrayList<Usuario> usuariosSistema = crearUsuariosDelSistema();

        Usuario usuarioSistema = identificarClienteConductor(usuarioConsola, usuariosSistema);

        ejecutarMenu(usuarioSistema);
    }

    // ESTE METODO SOLO VALIDA QUE EL USUARIO QUE ES INGRESADO  POR CONSOLA SEA
    // VALIDO Y CORRECTO 
    public static boolean validarUsuarioSistema(String usuario, String contrasena) {
        ArrayList<String> usuarios = ManejoArchivos.LeeFichero("usuarios.txt");

        for (String lineaUsuarios : usuarios) {

            String[] partes = lineaUsuarios.split(",");

            if (partes[3].equals(usuario) && partes[4].equals(contrasena)) {

                return true;
            }
        }
        return false;

    }

    //METODO CREAR VEHICULOS
    public static ArrayList<Vehiculo> crearVehiculos() {

        ArrayList<String> datosVehiculos = ManejoArchivos.LeeFichero("vehiculos.txt");

        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        for (String lineaVehiculos : datosVehiculos) {

            String[] datos = lineaVehiculos.split(",");

            Vehiculo vehiculo = new Vehiculo(datos[0], datos[1], datos[2], datos[3], datos[4]);

            vehiculos.add(vehiculo);

        } //NO ME DEJA

        return vehiculos;
    }

    //METODO PARA CREAR USUARIOS DEL SISTEMA
    public static ArrayList<Usuario> crearUsuariosDelSistema() {

        ArrayList<String> usuarios = ManejoArchivos.LeeFichero("usuarios.txt");

        ArrayList<Usuario> usuariosSistema = new ArrayList();

        for (String lineaUsuarios : usuarios) {

            String[] partes = lineaUsuarios.split(",");

            if (partes[6].equals("C")) {

                Cliente usuario = new Cliente(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);

                usuariosSistema.add(usuario);

            } // PREGUNTA : EDAD CONDUCTOR? CEDULA CODUCTOR? LICENCIA?

            if (partes[6].equals("R")) {

                ArrayList<String> lineasConductores = ManejoArchivos.LeeFichero("conductores.txt");
                String codigoVehiculo = "";

                Conductor usuario = new Conductor(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);

                for (String linea : lineasConductores) {
                    String[] datosConductores = linea.split(",");
                    if (partes[0].equals(datosConductores[0])) {
                        usuario.setEstado(datosConductores[1]);
                        codigoVehiculo = datosConductores[2];
                    }
                }

                ArrayList<Vehiculo> vehiculos = crearVehiculos();

                for (Vehiculo vehiculo : vehiculos) {
                    if (vehiculo.getCodigoVehiculo().equals(codigoVehiculo)) {
                        usuario.setVehiculo(vehiculo);
                    }
                }

                usuariosSistema.add(usuario);
            }

        }

        return usuariosSistema;
    }

    public static int ingresarEdad() {
        Scanner scanner = new Scanner(System.in);
        int edad = 0;
        boolean entradaValida = false;

        do {
            System.out.println("Ingrese su edad: ");
            if (scanner.hasNextInt()) { // VALIDA QUE LA ENTRADA SEA UN NUMERO ENTERO
                edad = scanner.nextInt();
                if (edad >= 15 && edad <= 100) {  // VALIDA LA EDAD EN UN RANGO CORRECTO Y ADECUADO
                    entradaValida = true;
                } else {
                    System.out.println("La edad ingresada no es esta en un rango valido");
                }
            } else {
                System.out.println("Por favor, ingrese un numero entero");
                scanner.next();
            }
        } while (!entradaValida);

        return edad;
    }

    public static Usuario identificarClienteConductor(String usuario, ArrayList<Usuario> listaUsuariosSistema) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < listaUsuariosSistema.size(); i++) {
            Usuario usuarioSistema = listaUsuariosSistema.get(i);

            if (usuarioSistema instanceof Cliente && usuarioSistema.getUser().equals(usuario)) {
                Cliente cliente = (Cliente) usuarioSistema;

                System.out.println("");
                System.out.println("ANTES DE CONTINUAR  ");

                int edadCliente = ingresarEdad();
                cliente.setEdad(edadCliente);

                System.out.println("Ingresar el numero de su tarjeta de Credito/Debito: ");
                scanner.nextLine();
                String numeroTarjeta = scanner.nextLine();
                cliente.setNumTarjetaCredito(numeroTarjeta);

                System.out.println("GRACIAS POR COMPLETAR LOS DATOS FALTANTES.");
                System.out.println("");

                return cliente;

            } else if (usuarioSistema instanceof Conductor && usuarioSistema.getUser().equals(usuario)) {

                Conductor conductor = (Conductor) usuarioSistema;
                // DECIDIMOS PREGUNTAR POR LA EDAD AL CONDUCTOR, YA QUE EL ARCHIVO PDF NO ESPECIFICA. 
                System.out.println("");
                System.out.println("ANTES DE CONTINUAR  ");

                int edadCliente = ingresarEdad();
                conductor.setEdad(edadCliente);

                return conductor;
            }
        }
        return null;
    }

    //CONFIGURACION DE MENU PARA EL SISTEMA    
    public static void ejecutarMenu(Usuario usuario) {

        if (usuario instanceof Cliente) {
            menuCliente((Cliente) usuario); //DOWNCASTNG

        } else if (usuario instanceof Conductor) {
            menuConductor((Conductor) usuario); //DOWNCASTING
        }
    }

    private static void menuCliente(Cliente cliente) {
        ArrayList<Servicios> servicios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("/****************MENU****************/");
            System.out.println("/*                                  */");
            System.out.println("/************************************/");
            System.out.println("1. Solicitar servicio de taxi");
            System.out.println("2. Solicitar entrega de encomiendas");
            System.out.println("3. Consultar servicios");
            System.out.println("4. Salir");
            System.out.println("");

            System.out.print("Elija una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // METODO DE SOLICITAR SERVICIO
                    Servicios viajeTaxi = cliente.solicitarViajeTaxi();
                    if (viajeTaxi != null) {
                        servicios.add(viajeTaxi);
                        viajeTaxi.setNumeroServicio(generarNumeroServicioUnico());
                        
                        guardarServicio(viajeTaxi, cliente);
                    }
                    break;
                case 2:
                    // METODO DE SOLICITAR ENTREGA
                    break;
                case 3:
                    // METODO DE CONSULTAR SERVICIOS
                    break;
                case 4:
                    System.out.println("Gracias por usar nuestrar aplicacion! G8");
                    System.exit(0);
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida");
            }

        } while (true);
    }

    private static void menuConductor(Conductor conductor) {

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("/***********MENU CONDUCTOR***********/");
            System.out.println("/*                                  */");
            System.out.println("/************************************/");
            System.out.println("1. Consultar servicios asignados");
            System.out.println("2. Datos de su vehiculo");
            System.out.println("3. Salir");
            System.out.println("");

            System.out.print("Elija una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // METODO CONSULTAR SERVICIO CONDUCTOR

                    break;
                case 2:
                    // METODO DATOS DE VEHICULO
                    System.out.println(conductor.getVehiculo() + "\n");
                    ;
                    break;
                case 3:
                    System.out.println("Gracias por usar nuestrar aplicacion! G8");
                    System.exit(0);
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida");
            }

        } while (true);

    }

    public static void guardarServicio(Servicios servicio, Cliente cliente) {
        String tipoServicio = "";
        if (servicio instanceof ViajeTaxi) {
            tipoServicio = "T";
        } else if (servicio instanceof Encomienda) {
            tipoServicio = "E";
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        String linea = servicio.getNumeroServicio() + ","
                + tipoServicio + ","
                + cliente.getNumCedula() + ","
                + servicio.getConductor().getNombre() + " " + servicio.conductor.getApellido() + ","
                + servicio.getRuta().getOrigen() + ","
                + servicio.getRuta().getDestino() + ","
                + formatoFecha.format(servicio.getFecha()) + ","
                + servicio.getHora();

        ManejoArchivos.EscribirArchivo("servicios.txt", linea);
    }

    //METODO PARA GENERAR NUMERO SERVICIO UNICO
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
