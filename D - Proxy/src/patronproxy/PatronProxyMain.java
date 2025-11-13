package patronproxy;
import implementacion.FabricaServicios;
import implementacion.InterfaceProcesos;

public class PatronProxyMain {
    public static void main(String[] args) {
        
        String usuario = "fbolano";
        String password = "pds";
        int proceso = 1;
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
    
}
