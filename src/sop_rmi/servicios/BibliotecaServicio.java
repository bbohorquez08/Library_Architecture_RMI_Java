package sop_rmi.servicios;

import sop_rmi.modelos.Credencial;
import sop_rmi.modelos.Libro;
import sop_rmi.modelos.Prestamo;
import sop_rmi.modelos.Usuario;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BibliotecaServicio extends Remote {
    //METODO QUE ME PERMITE REGISTRAR UN USUARIO
    public boolean registrarUsuario(Usuario objUsuario) throws RemoteException;
    //METODO QUE ME PERMITE REGISTRAR UN LIBRO
    public boolean registrarLibro(Libro objLibro) throws RemoteException;
    //METODO QUE ME PERMITE REALIZAR DEVOLUCION DEL LIBRO
    public String devolucionLibro(Libro objLibro) throws RemoteException;
    //METODO QUE ME PERMITE AUTENTICAR USUARIO EN EL SISTEMA
    public String iniciarSesion(Credencial objCredencial) throws RemoteException;
    //METODO QUE ME PERMITE CONSULTAR USUARIOS
    public Usuario consultarUsuario(int id) throws RemoteException;
    //METODO QUE ME PERMITE CONSULTAR LIBROS
    public Libro consultarLibro(int codigo) throws RemoteException;
    //METODO QUE ME PERMITE CONSULTAR PRESTAMOS
    public Prestamo consultarPrestamo(int codigo) throws RemoteException;
    //METODO QUE ME PERMITE REALIZAR UN PRESTAMO
    public boolean realizarPrestamo(Prestamo objPrestamo) throws RemoteException;
}
