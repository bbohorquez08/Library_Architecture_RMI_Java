package sop_rmi.servicios;

import sop_rmi.modelos.Credencial;
import sop_rmi.modelos.Libro;
import sop_rmi.modelos.Prestamo;
import sop_rmi.modelos.Usuario;

import java.time.LocalDate;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

public class BibliotecaServicioImpl extends UnicastRemoteObject implements BibliotecaServicio{
    //LISTA DE USUARIOS
    private ArrayList<Usuario> lstUsuarios;
    //LISTA DE LIBROS
    private ArrayList<Libro> lstLibros;
    //LISTA DE PRESTAMOS
    private ArrayList<Prestamo> lstPrestamos;

    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public ArrayList<Libro> getLstLibros() {
        return lstLibros;
    }

    public ArrayList<Prestamo> getLstPrestamos() {
        return lstPrestamos;
    }

    public BibliotecaServicioImpl() throws RemoteException {
        this.lstUsuarios= new ArrayList<Usuario>();
        this.lstLibros= new ArrayList<Libro>();
        this.lstPrestamos= new ArrayList<Prestamo>();
    }

    @Override
    public boolean registrarUsuario(Usuario objUsuario) throws RemoteException {
        if(!Objects.isNull(objUsuario)){
            this.lstUsuarios.add(objUsuario);
            //System.out.println("Usuario agregado exitosamente");
            return true;
        }else{
            System.out.println("Usuario no agregado, verificar informacion");
            return false;
        }
    }

    @Override
    public boolean registrarLibro(Libro objLibro) throws RemoteException {
        if(!Objects.isNull(objLibro)){
            this.lstLibros.add(objLibro);
            //System.out.println("Libro agregado exitosamente");
            return true;
        }else{
            System.out.println("Libro no agregado, verificar informacion");
            return false;
        }
    }

    @Override
    public String devolucionLibro(Libro objLibro) throws RemoteException {
        String Result = "D";
        Prestamo objPrestamo = new Prestamo();
        int posUsuario=0;
        int posPrestamo=0;
        //int bandera=0;

        for (int i = 0; i < lstPrestamos.size(); i++) {
            if (lstPrestamos.get(i).getLibroPrestado().getNombre().equals(objLibro.getNombre())){
                objPrestamo = lstPrestamos.get(i);
                posPrestamo=i;
            }
        }
        for (int i = 0; i < lstUsuarios.size(); i++) {
            if (objPrestamo.getUsuarioResponsable().getId()==lstUsuarios.get(i).getId()){
                posUsuario=i;
            }
        }
        System.out.println("fecha de devolucion: "+objPrestamo.getFechaDevolucion());
        int diasRetraso = validarFecha(objPrestamo.getFechaDevolucion());
        System.out.println("Dias de retraso: "+diasRetraso);
        if(diasRetraso>=1 && diasRetraso<=3){
            System.out.println("Multa de 10000");
            lstUsuarios.get(posUsuario).setDeuda(lstUsuarios.get(posUsuario).getDeuda()+10000);
            Result="E";
            lstPrestamos.remove(posPrestamo);
            //bandera=1;
            return Result;
        } else if (diasRetraso>=4 && diasRetraso<=8) {
            System.out.println("Multa de 10000 por los primeros 3 dias y 1000 por cada dia adicional");
            lstUsuarios.get(posUsuario).setDeuda(lstUsuarios.get(posUsuario).getDeuda()+10000);
            int dia=diasRetraso-3;
            lstUsuarios.get(posUsuario).setDeuda(lstUsuarios.get(posUsuario).getDeuda()+(dia*1000));
            lstPrestamos.remove(posPrestamo);
            Result="F";
            //bandera=1;
            return  Result;
        } else if (diasRetraso>=8) {
            System.out.println("Multa de 2000 por dia");
            lstUsuarios.get(posUsuario).setDeuda(lstUsuarios.get(posUsuario).getDeuda()+(diasRetraso*2000));
            lstPrestamos.remove(posPrestamo);
            Result="G";
            //bandera=1;
            return Result;
        }
        /*
        if(bandera==1){
            System.out.println("Devolucion de libro exitosa");
            lstPrestamos.remove(posPrestamo);
            Result="C";
        }else{
            System.out.println("Ha ocurrido un error");
            Result="D";
        }
        */



        return Result;
    }

    @Override
    public String iniciarSesion(Credencial objCredencial) throws RemoteException {

        String privilegios="";
        System.out.println("Invocando a iniciar sesion");
        String admin = "admin";
        String clave = "1234";

        if (objCredencial.getUsuario().equals(admin) && objCredencial.getClave().equals(clave)){
            privilegios="A";
            System.out.println("Sesion abierta como admin");
        }else{
            for (int i = 0; i < lstUsuarios.size(); i++) {
                if (lstUsuarios.get(i).getCredencial().getUsuario().equals(objCredencial.getUsuario()) &&
                    lstUsuarios.get(i).getCredencial().getClave().equals(objCredencial.getClave())){
                    privilegios="U";
                    System.out.println("Sesion abierta como usuario");
                    for (int j = 0; j < lstPrestamos.size(); j++) {
                            if (lstPrestamos.get(j).getUsuarioResponsable().getCredencial().getUsuario().equals(objCredencial.getUsuario())){
                                int diasRetraso= validarFecha(lstPrestamos.get(j).getFechaDevolucion());
                                if(diasRetraso==0){
                                    System.out.println("Hoy es la entrega del libro "+lstPrestamos.get(j).getLibroPrestado().getNombre());
                                } else if (diasRetraso<0) {
                                    System.out.println("Le faltan "+diasRetraso+" para la entrega del libro "+
                                            lstPrestamos.get(j).getLibroPrestado().getNombre());
                                } else if (diasRetraso>0) {
                                    System.out.println("Se paso "+diasRetraso+" para la entrega del libro "+
                                            lstPrestamos.get(j).getLibroPrestado().getNombre());
                                }
                            }
                    }
                }else{
                    privilegios="N";
                }
            }
        }
        return privilegios;
    }

    @Override
    public Usuario consultarUsuarioPorId(int id) throws RemoteException {
        Usuario objUsuario = null;
        System.out.println("Invocando a consultar usuario");
        if(lstUsuarios.isEmpty()){
            System.out.println("No hay usuarios");
        }else {
            int pos=consultaUsuario(id);
            if(pos!=-1){
                objUsuario=this.lstUsuarios.get(pos);
            }
        }
        return objUsuario;
    }

    @Override
    public Libro consultarLibros(int codigo) throws RemoteException {

        Libro objLibro = null;
        System.out.println("Invocando a consultar libro");
        if(lstLibros.isEmpty()){
            System.out.println("No hay libros en el sistema");
        }else{
            if (codigo!=this.lstLibros.size() && lstLibros.get(codigo)!=null ){
             objLibro=lstLibros.get(codigo);
            }
        }
        return objLibro;
    }

    @Override
    public Prestamo consultarPrestamos(int codigo) throws RemoteException {
        Prestamo objPrestamo = null;
        System.out.println("Invocando a consultar prestamo");
        if (lstPrestamos.isEmpty()){
            System.out.println("No hay prestamos en el sistema");
        }else {
            if(codigo!=this.lstPrestamos.size() && lstPrestamos.get(codigo)!=null){
                objPrestamo=lstPrestamos.get(codigo);
            }
        }
        return objPrestamo;
    }

    @Override
    public boolean realizarPrestamo(Prestamo objPrestamo) throws RemoteException {
        if(!Objects.isNull(objPrestamo)){
            this.lstPrestamos.add(objPrestamo);
            //System.out.println("Prestamo agregado exitosamente");
            return true;
        }else{
            System.out.println("Prestamo no agregado, verificar informacion");
            return false;
        }
    }

    @Override
    public Libro consultarLibroPorCodigo(int codigo) throws RemoteException {
        Libro objLibro = null;
        System.out.println("Invocando a consultar libro");
        if(lstLibros.isEmpty()){
            System.out.println("No hay libros");
        }else {
            int pos=consultaLibro(codigo);
            if(pos!=-1){
                objLibro=this.lstLibros.get(pos);
            }
        }
        return objLibro;
    }

    public int validarFecha(String fecha){
        LocalDate fechaDevolucion = LocalDate.parse(fecha);
        LocalDate fechaActual = LocalDate.now();
        long diasRetraso = ChronoUnit.DAYS.between(fechaDevolucion,fechaActual);
        int diastotales= (int) diasRetraso;
        return diastotales;
    }

    private int consultaUsuario(int id){
        int resultado=-1;
        for (int i = 0; i < this.lstUsuarios.size(); i++) {
            if(this.lstUsuarios.get(i).getId()==id){
                resultado=i;
                break;
            }
        }
        return resultado;
    }

    private int consultaLibro(int codigo){
        int resultado=-1;
        for (int i = 0; i < this.lstLibros.size(); i++) {
            if(this.lstLibros.get(i).getCodigo()==codigo){
                resultado=i;
                break;
            }
        }
        return resultado;
    }
}
