package implementacion;
import java.util.HashMap;
import java.util.Map;
import servicios.Auditoria;
import servicios.Seguridad;

public class ProxyProcesos implements InterfaceProcesos {

    // Map to track execution counts per process ID
    // Key: processId, Value: execution count
    private static final Map<Integer, Integer> executionCounts = new HashMap<>();

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        Seguridad securityService = new Seguridad();
        if(!securityService.Autorizacion(Usuario, Password)){
            throw new Exception("El usuario '"+Usuario
                    +"' no tiene privilegios para ejecutar el proceso");
        }
        
        // Count execution attempts after validation
        int currentCount = executionCounts.getOrDefault(IdProceso, 0);
        int newCount = currentCount + 1;
        
        // Check if execution count exceeds 3
        if (newCount > 3) {
            throw new Exception("[ERROR] El proceso " + IdProceso 
                    + " ha sido ejecutado más de 3 veces. Ejecuciones actuales: " + currentCount);
        }
        
        // Update execution count
        executionCounts.put(IdProceso, newCount);
        
        ProcesoDefecto ejecutorProcess = new ProcesoDefecto();
        ejecutorProcess.EjecutarProcesos(IdProceso, Usuario, Password);
        
        Auditoria audit = new Auditoria();
        audit.AuditoriaServicioUsado(Usuario, ProcesoDefecto.class.getName());
    }
    
}
