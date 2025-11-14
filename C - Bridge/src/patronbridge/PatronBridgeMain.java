package patronbridge;

import implementacion.InterfaceMensajeEncriptacion;

public class PatronBridgeMain {

    public static void main(String[] args) {
        try {
            // Create the bridge using the factory and the config file
            InterfaceMensajeEncriptacion puente = PuenteFactory.createPuenteFromConfig("patronbridge/config.properties");

            final String message = "<Curso><Nombre>Patrones de Diseño de Software</Nombre></Curso>";
            
            String password = "HG58YZ3CR9123456";

            String encryptedMessage = puente.EncryptarMensaje(message, password);
            
            System.out.println("Original Message > " + message);
            System.out.println("Encrypted Message > " + encryptedMessage + "\n");

            // To test with other configurations, you would change the 'encryption.type'
            // in config.properties and rerun the application.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}