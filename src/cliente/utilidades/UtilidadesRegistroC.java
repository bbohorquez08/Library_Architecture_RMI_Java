package cliente.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class UtilidadesRegistroC
{   
    
    public static Remote obtenerObjRemoto(String dirIP,int puerto, String nameObjReg)
    {
        String URLRegistro;
        URLRegistro  = "rmi://" + dirIP + ":" + puerto + "/"+nameObjReg;
        try
        {
            return Naming.lookup(URLRegistro);
        }
        catch (Exception e)
        {
            System.out.println("Excepcion en obtencion del objeto remoto"+ e);
            return null;
        }
    }
    
}