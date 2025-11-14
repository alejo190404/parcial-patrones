package patronbridge;

import java.util.Properties;

import encriptacion.InterfaceEncriptar;
import encriptacion.ProcesoEncriptarAES;
import encriptacion.ProcesoEncriptarCaesar;
import encriptacion.ProcesoEncriptarDES;
import encriptacion.ProcesoSinEncriptar;
import implementacion.InterfaceMensajeEncriptacion;
import implementacion.PuenteMensajeEncriptacion;
import utils.UtilidadesAcceso;

public class PuenteFactory {

    public static InterfaceMensajeEncriptacion createPuenteFromConfig(String configFilePath) throws Exception {
        
        Properties props = UtilidadesAcceso.loadProperty(configFilePath);

        String encryptionType = props.getProperty("encryption.type", "None"); // Default to "None"

        InterfaceEncriptar encriptador;

        switch (encryptionType.toUpperCase()) {
            case "AES":
                System.out.println("Factory: Creating Bridge with AES encryption.");
                encriptador = new ProcesoEncriptarAES();
                break;
            case "DES":
                System.out.println("Factory: Creating Bridge with DES encryption.");
                encriptador = new ProcesoEncriptarDES();
                break;
            case "CAESAR":
                System.out.println("Factory: Creating Bridge with Caesar encryption.");
                encriptador = new ProcesoEncriptarCaesar();
                break;
            case "None":
                System.out.println("Factory: Creating Bridge with no encryption.");
                encriptador = new ProcesoSinEncriptar();
                break;
            default:
                throw new IllegalArgumentException("Unknown encryption type specified in config: " + encryptionType);
        }

        return new PuenteMensajeEncriptacion(encriptador);
    }
}
