package cliente;

import cliente.vista.Menu;
import sop_rmi.controladores.BibliotecaController;
import sop_rmi.servicios.BibliotecaServicio;

import java.rmi.RemoteException;

public class ClienteDeObjetos {

    private static BibliotecaServicio objRemoto;

    public static void main(String[] args) throws RemoteException {
        int numPuertoRMIRegistry=0;
        String direccionIpRMIRegistry="";

        System.out.println("Cual es la direccion ip donde se encuentra el rmiregistry");
        direccionIpRMIRegistry=cliente.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiregistry");
        numPuertoRMIRegistry=cliente.utilidades.UtilidadesConsola.leerEntero();

        objRemoto=(BibliotecaServicio) cliente.utilidades.UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,
                numPuertoRMIRegistry,"objServicioBiblioteca");
        Menu objMenu = new Menu(objRemoto);
        objMenu.menuPrincipal();
    }

    /*
    private static void MenuPrincipal()
    {
        int opcion = 0;
        do
        {
            System.out.println("==Menu==");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Consultar usuario");
            System.out.println("3. Salir");

            opcion =  cliente.utilidades.UtilidadesConsola.leerEntero();

            switch(opcion)
            {
                case 1:
                    registrarPersonal();
                    break;
                case 2:
                    consultarPersonal();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        }while(opcion != 3);
    }

    private static void registrarPersonal() {
        try {
            System.out.println("==Registro del Cliente==");
            System.out.println("Ingrese tipo de identificacion (CC,TI,PP)");
            String tipoId=cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Ingrese la identificacion");
            int id = cliente.utilidades.UtilidadesConsola.leerEntero();
            System.out.println("Ingrese el nombre completo ");
            String nombre = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Ingrese ocupacion ");
            String ocupacion = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Ingrese Usuario ");
            String usuario = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Ingrese clave");
            String clave= cliente.utilidades.UtilidadesConsola.leerCadena();

            PersonalDTO objUsuario= new PersonalDTO(tipoId,id,nombre,ocupacion,usuario,clave);

            boolean valor = objRemoto.registrarPersonal(objUsuario);//invocación al método remoto
            if(valor)
                System.out.println("Registro realizado satisfactoriamente...");
            else
                System.out.println("no se pudo realizar el registro...");
        }
        catch(RemoteException e)
        {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }
    private static void consultarPersonal()
    {
        try
        {
            System.out.println("==Consulta de un Cliente==");
            System.out.println("Ingrese la identificacion");
            int id = cliente.utilidades.UtilidadesConsola.leerEntero();

            PersonalDTO objUsuario= objRemoto.consultarPersonal(id);
            if(objUsuario!=null)
            {
                System.out.println("Tipo identificacion: "+objUsuario.getTipoId());
                System.out.println("id: "+objUsuario.getId());
                System.out.println("Nombre: " + objUsuario.getNombre());
                System.out.println("Ocupacion: " + objUsuario.getOcupacion());
                System.out.println("Usuario: "+objUsuario.getUsuario());
            }
            else
                System.out.println("Usuario no encontrado");
        }
        catch(RemoteException e)
        {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

     */
}
