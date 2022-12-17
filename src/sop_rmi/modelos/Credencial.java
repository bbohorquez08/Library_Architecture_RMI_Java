package sop_rmi.modelos;

import java.io.Serializable;

public class Credencial implements Serializable {
    private String usuario;
    private String clave;

    public Credencial(String usuario, String clave){
        this.usuario=usuario;
        this.clave=clave;
    }

    public Credencial(){}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
