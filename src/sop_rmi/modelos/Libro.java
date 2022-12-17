package sop_rmi.modelos;

import java.io.Serializable;

public class Libro implements Serializable {
    private int codigo;
    private String nombre;
    private String areaConocimiento;
    private String autor;
    private String editorial;

    public Libro(int codigo, String nombre, String areaConocimiento,
                 String autor, String editorial) {
        this.codigo =codigo;
        this.nombre = nombre;
        this.areaConocimiento = areaConocimiento;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libro(){}
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }


}
