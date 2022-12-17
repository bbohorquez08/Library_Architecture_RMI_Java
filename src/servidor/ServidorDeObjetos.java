package servidor;

import sop_rmi.controladores.BibliotecaController;
import sop_rmi.servicios.BibliotecaServicioImpl;

import java.rmi.RemoteException;

public class ServidorDeObjetos {
    public static void main(String args[]) throws RemoteException{
        int numPuertoRMIRegistry=0;
        String direccionIpRMIRegistry="";

        System.out.println("Cual es la direccion ip donde se encuentra el rmiregistry");
        direccionIpRMIRegistry=servidor.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual se escucha rmiregistry");
        numPuertoRMIRegistry=servidor.utilidades.UtilidadesConsola.leerEntero();

        BibliotecaServicioImpl bibliotecaServicio = new BibliotecaServicioImpl();
        BibliotecaController bibliotecaController = new BibliotecaController(bibliotecaServicio);

        try{
            servidor.utilidades.UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            servidor.utilidades.UtilidadesRegistroS.RegistrarObjetoRemoto(bibliotecaController,direccionIpRMIRegistry,
                    numPuertoRMIRegistry,"objServicioBiblioteca");
        }catch (Exception e){
            System.err.println("No fue posible el NS o registrtar el objeto remoto");
        }
    }
}
