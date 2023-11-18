package Principal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Principal.lecturaArchivos.manejoArchivos;
import java.util.ArrayList;

/**
 *
 * @author frank
 */
public class Sistema {
    public ArrayList<Vehiculo> crearVehiculos(){
        String nombrearchivo = "";//AUN NO SE TIENE ARCHIVO DE VEHICULOS
        ArrayList<String> datosVehiculos = manejoArchivos.LeeFichero(nombrearchivo);
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        for (String linea: datosVehiculos){
            String[] datos = linea.split(",");
            Vehiculo vehiculo = new Vehiculo(datos[0], datos[1], datos[2], datos[3], datos[4]);
            vehiculos.add(vehiculo);
        }
        return vehiculos;
    }
    
    public static void main(String[] args) {
        
        
    }
    
}
