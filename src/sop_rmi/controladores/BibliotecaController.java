package sop_rmi.controladores;

import sop_rmi.modelos.Credencial;
import sop_rmi.modelos.Libro;
import sop_rmi.modelos.Prestamo;
import sop_rmi.modelos.Usuario;
import sop_rmi.servicios.BibliotecaServicio;
import sop_rmi.servicios.BibliotecaServicioImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BibliotecaController extends UnicastRemoteObject implements BibliotecaServicio {

    //APLICAMOS INYECCION DE DEPENDENCIAS
    private final BibliotecaServicioImpl bibliotecaServicio;

    public BibliotecaController(BibliotecaServicioImpl bibliotecaServicio) throws RemoteException {
        super();
        this.bibliotecaServicio = bibliotecaServicio;
    }

    @Override
    public boolean registrarUsuario(Usuario objUsuario) throws RemoteException {
        return this.bibliotecaServicio.registrarUsuario(objUsuario);
    }

    @Override
    public boolean registrarLibro(Libro objLibro) throws RemoteException {
        return this.bibliotecaServicio.registrarLibro(objLibro);
    }

    @Override
    public String devolucionLibro(Libro objLibro) throws RemoteException {
        return this.bibliotecaServicio.devolucionLibro(objLibro);
    }

    @Override
    public String iniciarSesion(Credencial objCredencial) throws RemoteException {
        return this.bibliotecaServicio.iniciarSesion(objCredencial);
    }

    @Override
    public Usuario consultarUsuario(int id) throws RemoteException {
        return this.bibliotecaServicio.consultarUsuario(id);
    }

    @Override
    public Libro consultarLibro(int codigo) throws RemoteException {
        return this.bibliotecaServicio.consultarLibro(codigo);
    }

    @Override
    public Prestamo consultarPrestamo(int codigo) throws RemoteException {
        return this.bibliotecaServicio.consultarPrestamo(codigo);
    }

    @Override
    public boolean realizarPrestamo(Prestamo objPrestamo) throws RemoteException {
        return this.bibliotecaServicio.realizarPrestamo(objPrestamo);
    }
}
