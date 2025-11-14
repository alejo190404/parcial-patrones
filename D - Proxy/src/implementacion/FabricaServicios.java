package implementacion;

public class FabricaServicios {
    public static InterfaceProcesos CrearEjecucionProceso(){
        return new ProxyProcesos();
    }

    public static InterfaceProcesos CrearEjecucionProcesoSinAuditar(){
        return new ProxySinAuditar();
    }
}
