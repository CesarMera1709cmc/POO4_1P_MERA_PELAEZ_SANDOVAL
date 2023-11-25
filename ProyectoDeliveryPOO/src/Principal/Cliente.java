package Principal;

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

}
