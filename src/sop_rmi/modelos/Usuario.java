package sop_rmi.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {
    //ATRIBUTOS
    private int id;
    private String nombre;
    private String apellido;
    private String ocupacion;
    private Credencial credencial;
    private float deuda;

    //CONSTRUCTORES

    public Usuario(int id, String nombre, String apellido,
                   String ocupacion, Credencial credencial,
                   float deuda) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ocupacion = ocupacion;
        this.credencial = credencial;
        this.deuda = deuda;
    }

    public Usuario(){}

    //GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public float getDeuda() {
        return deuda;
    }

    public void setDeuda(float deuda) {
        this.deuda = deuda;
    }
}
