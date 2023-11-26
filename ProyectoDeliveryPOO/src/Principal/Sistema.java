package Principal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Principal.lecturaArchivos.ManejoArchivos;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author frank
 */
public class Sistema {

    /**
     * COMENTO PARA PROBAR
     *
     * public ArrayList<Vehiculo> crearVehiculos() { String nombrearchivo =
     * "";//AUN NO SE TIENE ARCHIVO DE VEHICULOS ArrayList<String>
     * datosVehiculos = manejoArchivos.LeeFichero(nombrearchivo);
     * ArrayList<Vehiculo> vehiculos = new ArrayList<>(); for (String linea :
     * datosVehiculos) { String[] datos = linea.split(","); Vehiculo vehiculo =
     * new Vehiculo(datos[0], datos[1], datos[2], datos[3], datos[4]);
     * vehiculos.add(vehiculo); } return vehiculos; }
     *
     *
     */
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

    public static ArrayList<Usuario> crearUsuariosDelSistema() {

        ArrayList<String> usuarios = ManejoArchivos.LeeFichero("usuarios.txt");

        ArrayList<Usuario> usuariosSistema = new ArrayList();

        for (String lineaUsuarios : usuarios) {

            String[] partes = lineaUsuarios.split(",");

            if (partes[6].equals("C")) {

                Usuario usuario = new Cliente(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);

                usuariosSistema.add(usuario);

            } // PREGUNTA : EDAD CONDUCTOR? CEDULA CODUCTOR? LICENCIA?

            if (partes[6].equals("R")) {

                Usuario usuario = new Conductor(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
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

            }

            if (usuarioSistema instanceof Conductor && usuarioSistema.getUser().equals(usuario)) {

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

}
