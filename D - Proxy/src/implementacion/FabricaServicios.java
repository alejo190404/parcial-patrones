package implementacion;

public class FabricaServicios {
     public static InterfaceProcesos CrearEjecucionProceso(){
        return new ProxyProcesos();
    }
}
