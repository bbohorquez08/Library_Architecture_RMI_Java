package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import sop_rmi.modelos.Credencial;
import sop_rmi.modelos.Libro;
import sop_rmi.modelos.Prestamo;
import sop_rmi.modelos.Usuario;
import sop_rmi.controladores.BibliotecaController;
import sop_rmi.servicios.BibliotecaServicio;

import java.rmi.RemoteException;
import java.util.Objects;

public class Menu {

    private final BibliotecaServicio bibliotecaControllerRemoto;

    public Menu(BibliotecaServicio bibliotecaControllerRemoto) {
        this.bibliotecaControllerRemoto = bibliotecaControllerRemoto;
    }

    public void menuPrincipal() throws RemoteException {
        int opc=0;
        do {
            System.out.println("==================================================");
            System.out.println("========Bienvenido, inicia sesion por favor====== ");
            System.out.println("| 1. Iniciar sesion |");
            System.out.println("| 2. Salir |");
            System.out.println("==================================================");
            opc= UtilidadesConsola.leerEntero();

            switch (opc) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    System.out.println("Hasta pronto!!!");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }


        }while(opc!=2);


    }

    private void iniciarSesion() throws RemoteException {

        System.out.println("Digite usuario: ");
        String usuario= UtilidadesConsola.leerCadena();
        System.out.println("Digite clave: ");
        String clave= UtilidadesConsola.leerCadena();

        String resIniciarSesion= this.bibliotecaControllerRemoto.iniciarSesion(new Credencial(usuario,clave));

        switch (resIniciarSesion.toUpperCase()){
            case "A":
                privilegiosAdministrador();
                break;
            case "U":
                //FALTAAAAAAAAAA
                break;
            case "N":
                System.out.println("Usuario no valido, verifique usuario y clave");
                break;
        }

    }

    private void privilegiosAdministrador() throws RemoteException {
        int opc=0;
        System.out.println("Inicio de sesion valido, sesion con privilegios de administrador");
        do {
            System.out.println("========Menu Administrador========");
            System.out.println("| 1. Registrar usuario |");
            System.out.println("| 2. Registrar libro |");
            System.out.println("| 3. Consultar Libros |");
            System.out.println("| 4. Consultar prestamos |");
            System.out.println("| 5. Iniciar proceso devolucion libro |");
            System.out.println("| 6. Salir |");
            System.out.println("==================================================");
            opc=UtilidadesConsola.leerEntero();

            switch (opc){
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    registrarLibro();
                    break;
                case 3:
                    consultarLibros();
                    break;
                case 4:
                    consultarPrestamos();
                    break;
                case 5:
                    devolucionLibro();
                    break;
                case 6:
                    System.out.println("Hasta pronto!!!!");
                    break;
            }
        }while(opc!=6);
    }

    private void registrarUsuario() throws RemoteException {
        System.out.println("Digite identificacion: ");
        int id=UtilidadesConsola.leerEntero();
        System.out.println("Digite nombre: ");
        String nombre = UtilidadesConsola.leerCadena();
        System.out.println("Digite apellido: ");
        String apellido= UtilidadesConsola.leerCadena();
        System.out.println("Digite ocupacion: ");
        String ocupacion= UtilidadesConsola.leerCadena();
        System.out.println("Digite usuario: ");
        String usuario= UtilidadesConsola.leerCadena();
        System.out.println("Digite clave: ");
        String clave= UtilidadesConsola.leerCadena();
        float deuda=0;

        if(this.bibliotecaControllerRemoto.
                registrarUsuario(new Usuario(id,nombre,apellido,ocupacion,
                        new Credencial(usuario,clave),deuda))){
            System.out.println("Usuario registrado correctamente");
        }else{
            System.out.println("Error, verifique informacion");
        }

    }

    private void registrarLibro() throws RemoteException {
        System.out.println("Digite codigo: ");
        int codigo= UtilidadesConsola.leerEntero();
        System.out.println("Digite nombre: ");
        String nombre=UtilidadesConsola.leerCadena();
        System.out.println("Digite area de conocimiento: ");
        String area=UtilidadesConsola.leerCadena();
        System.out.println("Digite autor: ");
        String autor=UtilidadesConsola.leerCadena();
        System.out.println("Digite editorial: ");
        String editorial= UtilidadesConsola.leerCadena();

        if(this.bibliotecaControllerRemoto.registrarLibro(new Libro(codigo,nombre,area,autor,editorial))){
            System.out.println("Libro registrado correctamente");
        }else{
            System.out.println("Error, verifique informacion");
        }
    }

    private void consultarLibros() throws RemoteException {

        int i =0;
        while (true){
            Libro objLibro=this.bibliotecaControllerRemoto.consultarLibro(i);
            if(Objects.isNull(objLibro)){
                break;
            }else{
                System.out.println("=====================");
                System.out.println("Nombre: "+objLibro.getNombre());
                System.out.println("Autor: "+objLibro.getAutor());
                System.out.println("Editorial: "+objLibro.getEditorial());
                System.out.println("Area conocimiento: "+objLibro.getAreaConocimiento());
            }
            i=i+1;
        }
    }

    private void consultarPrestamos() throws RemoteException {
        System.out.println("Digite identificacion del usuario: ");
        int id= UtilidadesConsola.leerEntero();
        int i =0;
        while (true){
            Prestamo objPrestamo=this.bibliotecaControllerRemoto.consultarPrestamo(i);
            if(Objects.isNull(objPrestamo)){
                break;
            }else{
                if(objPrestamo.getUsuarioResponsable().getId()==id){
                    System.out.println("=====================");
                    System.out.println("Libro: "+objPrestamo.getLibroPrestado().getNombre());
                    System.out.println("Fecha prestamo: "+objPrestamo.getFechaPrestamo());
                    System.out.println("Fecha devolucion: "+objPrestamo.getFechaDevolucion());
                }
            }
            i=i+1;
        }
    }

    private void devolucionLibro() throws RemoteException {
        System.out.println("Digite nombre del libro: ");
        String nombre= UtilidadesConsola.leerCadena();
        Libro objLibro = null;
        int i =0;
        while (true){
            Prestamo objPrestamo=this.bibliotecaControllerRemoto.consultarPrestamo(i);
            if(Objects.isNull(objPrestamo)){
                System.out.println("El libro no se encuentra prestado");
                break;
            }else{
                if(objPrestamo.getLibroPrestado().getNombre().equals(nombre)){
                    objLibro=objPrestamo.getLibroPrestado();
                }
            }
            i=i+1;
        }

        if(!Objects.isNull(objLibro)){
            String resDevolucion=this.bibliotecaControllerRemoto.devolucionLibro(objLibro);
            switch (resDevolucion.toUpperCase()){
                case "D":
                    System.out.println("El libro no fue reservado");
                    break;
                case "E":
                    System.out.println("Libro devuelto exitosamente");
                    System.out.println("Se ha generado una multa por retraso de 1-3 dias");
                    System.out.println("Valor a pagar : 10000");
                    break;
                case "F":
                    System.out.println("Libro devuelto exitosamente");
                    System.out.println("Se ha generado una multa por retraso de 4-8 dias");
                    System.out.println("Valor a pagar : 10000 por los primeros 3 dias y 1000 por cada dia adicional");
                    break;
                case "G":
                    System.out.println("Libro devuelto exitosamente");
                    System.out.println("Se ha generado una multa por retraso de mas de 8 dias");
                    System.out.println("Valor a pagar : Multa de 2000 por dia");
                    break;
            }

        }


    }
}
