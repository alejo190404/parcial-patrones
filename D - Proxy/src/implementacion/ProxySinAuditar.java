package implementacion;

import servicios.Seguridad;

public class ProxySinAuditar implements InterfaceProcesos {

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        Seguridad securityService = new Seguridad();
        if(!securityService.Autorizacion(Usuario, Password)){
            throw new Exception("El usuario '"+Usuario
                    +"' no tiene privilegios para ejecutar el proceso");
        }
        
        
        ProcesoDefecto ejecutorProcess = new ProcesoDefecto();
        ejecutorProcess.EjecutarProcesos(IdProceso, Usuario, Password);
    }
    
}