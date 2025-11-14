package patronproxy;
import implementacion.FabricaServicios;
import implementacion.InterfaceProcesos;

public class PatronProxyMain {
    public static void main(String[] args) {
        //Se queman las variables/parámetros necesarios para consultar y escoger el proxy
        String usuario = "fbolano";
        String password = "pds";
        int proceso = 1;
        String parametro = "SIN";

        if (parametro.equals("SIN")){
            InterfaceProcesos ProcesoActivo = FabricaServicios.CrearEjecucionProceso();

            try {
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                ProcesoActivo.EjecutarProcesos(2, usuario, password);
                ProcesoActivo.EjecutarProcesos(2, usuario, password);
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                // ProcesoActivo.EjecutarProcesos(2, usuario, "pds");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            InterfaceProcesos ProcesoActivo = FabricaServicios.CrearEjecucionProcesoSinAuditar();

            try {
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                ProcesoActivo.EjecutarProcesos(2, usuario, password);
                ProcesoActivo.EjecutarProcesos(2, usuario, password);
                ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
                // ProcesoActivo.EjecutarProcesos(2, usuario, "pds");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}
