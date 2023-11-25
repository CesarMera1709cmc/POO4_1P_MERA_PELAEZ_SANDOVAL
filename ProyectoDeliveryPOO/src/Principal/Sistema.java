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

            System.out.println("CONTRASEÑA: ");
            usuarioContrasena = scanner.nextLine();

            if (!validarUsuarioSistema(usuarioConsola, usuarioContrasena)) {
                System.out.println("EL USUARIO O CONTRASEÑA INGRESADOS SON INCORRECTOS");
                System.out.println("VUELVA A INGRESAR USUARIO Y CONTRASEÑA");
            }
        } while (!validarUsuarioSistema(usuarioConsola, usuarioContrasena));

        System.out.println("INICIO DE SESION CORRECTO.");
        System.out.println("BIENVENID@ : " + usuarioConsola);

        // UNA VEZ VALIDADO EL USUARIO; ES NECESARIO UN METODO QUE CONTRUYA UNA LISTA DE    
        // USUARIOS PARA SEGUIR CON EL PROGRAMA 
        ArrayList <Usuario> usuariosSistema = crearUsuariosDelSistema();
        
        Cliente cliente = identificarCliente(usuarioConsola, usuariosSistema);
        
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
        }
        return usuariosSistema;
    }

    public static Cliente identificarCliente(String usuario, ArrayList<Usuario> listaUsuariosSistema) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < listaUsuariosSistema.size(); i++) {
            Usuario usuarioSistema = listaUsuariosSistema.get(i);

            if (usuarioSistema instanceof Cliente && usuarioSistema.getUser().equals(usuario)) {
                Cliente cliente = (Cliente) usuarioSistema;

                // Preguntar al Cliente la edad y Tarjeta de crédito
                System.out.println("");
                System.out.println("ANTES DE CONTINUAR  ");

                // Preguntar y establecer la edad del cliente
                System.out.println("Ingrese su edad:  ");
                int edadCliente = scanner.nextInt();
                cliente.setEdad(edadCliente);

                // Preguntar y establecer el número de tarjeta de crédito del cliente
                System.out.println("Ingresar el número de su tarjeta de Crédito/Debito: ");
                scanner.nextLine(); // Limpiar el buffer de entrada antes de leer la línea
                String numeroTarjeta = scanner.nextLine();
                cliente.setNumTarjetaCredito(numeroTarjeta);

                System.out.println("GRACIAS POR COMPLETAR LOS DATOS FALTANTES.");
                System.out.println("");

                return cliente; // Devuelve el cliente identificado

            }
        } return null;
    }
}
