package sop_rmi.modelos;

import java.io.Serializable;

public class Prestamo implements Serializable {
    private int codigo;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private Libro libroPrestado;
    private Usuario usuarioResponsable;

    public Prestamo(int codigo, String fechaPrestamo, String fechaDevolucion,
                    Libro libroPrestado, Usuario usuarioResponsable) {
        this.codigo = codigo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libroPrestado = libroPrestado;
        this.usuarioResponsable = usuarioResponsable;
    }

    public Prestamo(){}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(Libro libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }
}
