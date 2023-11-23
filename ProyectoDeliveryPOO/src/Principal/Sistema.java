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

        System.out.println("INICIO DE SESION CORRECTO." );
        System.out.println("BIENVENID@ : " + usuarioConsola);

     // UNA VEZ VALIDADO EL USUARIO; ES NECESARIO UN METODO QUE CONTRUYA UNA LISTA DE    
     // USUARIOS PARA SEGUIR CON EL PROGRAMA 
      
     
        
        
        
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

}
