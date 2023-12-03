package Principal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Principal.enums.TipoEstado;
import Principal.enums.TipoVehiculo;
import Principal.lecturaArchivos.ManejoArchivos;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author frank
 */
public class Sistema {

    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Servicio> servicios = new ArrayList<>();

    //CREACION DE USUARIOS
    public static void crearUsuarios() {

        ArrayList<Usuario> usuariosSistema = crearUsuariosDelSistema();

        for (Usuario usuario : usuariosSistema) {

            usuarios.add(usuario);

        }

    }

    //SELECIONAR CONDUCTOR DISPONIBLE
    public static Conductor seleccionarConductorDisponible(Servicio servicio) {

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Conductor) {
                Conductor conductor = (Conductor) usuario;
                if (servicio instanceof ViajeTaxi) {
                    if (conductor.getEstado().equals(TipoEstado.D) && conductor.getVehiculo().getTipoVehiculo().equals(TipoVehiculo.A)) {
                        return conductor;
                    }
                } else if (servicio instanceof Encomienda) {
                    if (conductor.getEstado() == TipoEstado.D && conductor.getVehiculo().getTipoVehiculo() == TipoVehiculo.M) {
                        return conductor;
                    }
                }
            }
        }

        return null;
    }

    //CREACION DE SERVICIOS
    public static void crearServicios() {

        ArrayList<String> lineasServicios = ManejoArchivos.LeeFichero("servicios.txt");

        lineasServicios.remove(0);

        if (!lineasServicios.isEmpty()) {

            for (String linea : lineasServicios) {

                String[] datos = linea.split(",");
                Ruta ruta = new Ruta(datos[4], datos[5]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(datos[6], formatter);
                Date fecha = Date.from(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

                Conductor conductorServicio = new Conductor();

                for (Usuario usuario : usuarios) {
                    if (usuario instanceof Conductor) {
                        Conductor conductor = (Conductor) usuario;
                        if (datos[3].equals(conductor.getNombre() + " " + conductor.getApellido())) {
                            conductorServicio = conductor;
                        }
                    }
                }

                switch (datos[1]) {

                    case "T" -> {

                        ArrayList<String> lineasViajes = ManejoArchivos.LeeFichero("viajes.txt");
                        lineasViajes.remove(0);

                        for (String linea2 : lineasViajes) {

                            String[] datos2 = linea2.split(",");

                            if (datos[0].equals(datos2[0])) {

                                ViajeTaxi viaje = new ViajeTaxi(Integer.parseInt(datos[0]), datos[2], conductorServicio, ruta, fecha, datos[7], datos2[1], datos2[2]);
                                servicios.add(viaje);

                            }
                        }
                    }
                    case "E" -> {

                        ArrayList<String> lineasEncomiendas = ManejoArchivos.LeeFichero("encomiendas.txt");
                        lineasEncomiendas.remove(0);

                        for (String linea2 : lineasEncomiendas) {

                            String[] datos2 = linea2.split(",");

                            if (datos[0].equals(datos2[0])) {

                                Encomienda encomienda = new Encomienda(Integer.parseInt(datos[0]), datos[2], conductorServicio, ruta, fecha, datos[7], datos2[1], datos2[2], datos2[3]);
                                encomienda.setNumeroServicio(Integer.parseInt(datos[0]));
                                encomienda.setRuta(ruta);
                                encomienda.setFecha(fecha);
                                encomienda.setHora(datos[7]);
                                encomienda.setConductor(conductorServicio);
                                encomienda.setCedulaCliente(datos[2]);

                                servicios.add(encomienda);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        crearUsuarios();
        crearServicios();
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
        Usuario usuarioSistema = identificarClienteConductor(usuarioConsola, usuarios);

        ejecutarMenu(usuarioSistema);
    }

    /**
     *
     * Este metodo valida el usuario y contraseña ingresado por el usuario de la
     * consola
     *
     * @param usuario Usuario en formato String requerido para iniciar sesion
     * @param contrasena Contraseña en formato String requerida para iniciar
     * sesion
     * @return Retorna una valor booleano True se se inicio sesion correctamente
     */
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

    /**
     * Este metodo crea un ArrayList de vehiculos a partir de un archivo de
     * texto
     *
     * @return Retorna el ArrayList de vehiculos creado
     */
    public static ArrayList<Vehiculo> crearVehiculos() {

        ArrayList<String> datosVehiculos = ManejoArchivos.LeeFichero("vehiculos.txt");

        datosVehiculos.remove(0);

        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        for (String lineaVehiculos : datosVehiculos) {

            String[] datos = lineaVehiculos.split(",");

            Vehiculo vehiculo = new Vehiculo(datos[0], datos[1], datos[2], datos[3], datos[4]);

            vehiculos.add(vehiculo);

        }

        return vehiculos;
    }

    /**
     * Este metodo crea un ArrayList de tipo Usuario a partir de un archivo de
     * texto con el formato de usuarios en especifico.
     *
     * @return Retorna el ArrayList de Usuarios creados
     */
    //METODO PARA CREAR USUARIOS DEL SISTEMA
    public static ArrayList<Usuario> crearUsuariosDelSistema() {

        ArrayList<String> usuarios = ManejoArchivos.LeeFichero("usuarios.txt");

        usuarios.remove(0);

        ArrayList<Usuario> usuariosSistema = new ArrayList<>();

        for (String lineaUsuarios : usuarios) {

            String[] partes = lineaUsuarios.split(",");

            if (partes[6].equals("C")) {

                Cliente usuario = new Cliente(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);

                usuariosSistema.add(usuario);

            } // Redudancia en PDF : EDAD CONDUCTOR? CEDULA CODUCTOR? LICENCIA?

            if (partes[6].equals("R")) {

                ArrayList<String> lineasConductores = ManejoArchivos.LeeFichero("conductores.txt");
                int codigoVehiculo = 0;

                Conductor usuario = new Conductor(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);

                for (String linea : lineasConductores) {
                    String[] datosConductores = linea.split(",");
                    if (partes[0].equals(datosConductores[0])) {
                        usuario.setEstado(datosConductores[1]);
                        codigoVehiculo = Integer.parseInt(datosConductores[2]);
                    }
                }

                ArrayList<Vehiculo> vehiculos = crearVehiculos();

                for (Vehiculo vehiculo : vehiculos) {
                    if (vehiculo.getCodigoVehiculo() == codigoVehiculo) {
                        usuario.setVehiculo(vehiculo);
                        break;
                    }
                }

                usuariosSistema.add(usuario);
            }

        }

        return usuariosSistema;
    }

    /**
     * Este metodo solicita la edad al usuario de la consola, siempre y cuando
     * sea un entero y se encuentre en un rango valido.
     *
     * @return Retorna el valor entero de la edad.
     */
    public static int ingresarEdad() {
        Scanner scanner = new Scanner(System.in);
        int edad = 0;
        boolean entradaValida = false;

        do {
            System.out.println("Ingrese su edad: ");
            if (scanner.hasNextInt()) { // VALIDA QUE LA ENTRADA SEA UN NUMERO ENTERO
                edad = scanner.nextInt();
                if (edad >= 18 && edad <= 100) {  // VALIDA LA EDAD EN UN RANGO CORRECTO Y ADECUADO
                    entradaValida = true;
                } else {
                    System.out.println("La edad ingresada no es esta en un rango valido (APLICACION SOLO PARA +18)");
                }
            } else {
                System.out.println("Por favor, ingrese un numero entero");
                scanner.next();
            }
        } while (!entradaValida);

        return edad;
    }

    /**
     * Este metodo es utilizado para identificar el tipo de usuario que se ha
     * ingresado por la consola (No valida)
     *
     * @param usuario String usuario ingresado por la consola previamente
     * @param listaUsuariosSistema ArrayList de Usuarios previamente creados a
     * partir del archivo de texto
     * @return Retorna un objeto de tipo Usuario el cual sera utilizado para
     * correr la aplicacion.
     */
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

                String numeroTarjeta;
                do {
                    System.out.println("Ingresar el numero de su tarjeta de Credito/Debito ** FORMATO 1234 1234 1234 1234");
                    numeroTarjeta = scanner.nextLine();
                    if (!validarFormatoTarjetaCredito(numeroTarjeta)) {
                        System.out.println("Formato de tarjeta no valido. Debe tener 16 dígitos.");
                    }
                } while (!validarFormatoTarjetaCredito(numeroTarjeta));

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

    public static boolean validarFormatoTarjetaCredito(String numeroTarjeta) {

        String tarjetaSinEspacios = numeroTarjeta.replaceAll("\\s", "");
        if (tarjetaSinEspacios.length() != 16) {
            return false;
        }

        for (char c : tarjetaSinEspacios.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Este metodo ejecuta el Menu, la interfaz depende si el usuario ingresado
     * como parametro es de tipo Cliente o conductor.
     *
     * @param usuario El parametro es una instancia de tipo Usuario, creada con
     * los metodos previamente.
     */
    public static void ejecutarMenu(Usuario usuario) {

        if (usuario instanceof Cliente) {
            menuCliente((Cliente) usuario); //DOWNCASTNG

        } else if (usuario instanceof Conductor) {
            menuConductor((Conductor) usuario); //DOWNCASTING
        }
    }

    /**
     * Este metodo abre la interfaz de el menu para el cliente
     *
     * @param cliente Recibe como parametro una instancia de Cliente
     */
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
                case 1 -> {
                    // METODO DE SOLICITAR SERVICIO TAXI
                    Servicio viajeTaxi = cliente.solicitarViajeTaxi();
                    if (viajeTaxi != null) {
                        guardarServicio(viajeTaxi, cliente);
                    }
                }
                case 2 -> {
                    // METODO DE SOLICITAR SERVICIO ENCOMIENDA
                    Servicio encomienda = cliente.solicitarEntregaEncomiendas();
                    if (encomienda != null) {
                        guardarServicio(encomienda, cliente);
                    }
                }
                case 3 ->
                    cliente.consultarServicios();
                case 4 -> {
                    System.out.println("Gracias por usar nuestrar aplicacion! G8");
                    System.exit(0);
                }
                default ->
                    System.out.println("La opcion ingresada no es valida");
            }

        } while (true);
    }

    /**
     * Este metodo abre la interfaz del menu para el Conductor
     *
     * @param conductor Recibe como parametro una instancia de Condcutor
     *
     */
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
                case 1 ->
                    conductor.consultarServicios();
                case 2 ->
                    System.out.println(conductor.getVehiculo() + "\n");
                case 3 -> {
                    System.out.println("Gracias por usar nuestrar aplicacion! G8");
                    System.exit(0);
                }
                default ->
                    System.out.println("La opcion ingresada no es valida");
            }

        } while (true);

    }

    /**
     * Este metodo guarda los servicio creados por el cliente en un archivo de
     * texto
     *
     * @param servicio Recibe como parametro el objeto de tipo Servicio creado
     * @param cliente Recibe como parametro el objeto de tipo Cliente, el cual
     * crea el servicio
     */
    public static void guardarServicio(Servicio servicio, Cliente cliente) {
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

    public static void guardarServicioTaxi(ViajeTaxi viaje, Pago pago) {

        String linea = viaje.getNumeroServicio() + ","
                + viaje.getNumPersonas() + ","
                + viaje.getDistancia() + ","
                + viaje.getDistancia() + ","
                + pago.getSubtotal();

        ManejoArchivos.EscribirArchivo("viajes.txt", linea);
    }

    public static void guardarServicioEncomienda(Encomienda encomienda, Pago pago) {

        String linea = encomienda.getNumeroServicio() + ","
                + encomienda.getTipoEncomienda() + ","
                + encomienda.getCantProductos() + ","
                + encomienda.getPesoKG() + ","
                + pago.getSubtotal();

        ManejoArchivos.EscribirArchivo("encomiendas.txt", linea);
    }
}
