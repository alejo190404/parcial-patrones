package patronbridge;

import encriptacion.ProcesoEncriptarAES;
import encriptacion.ProcesoEncriptarCaesar;
import encriptacion.ProcesoEncriptarDES;
import encriptacion.ProcesoSinEncriptar;
import implementacion.InterfaceMensajeEncriptacion;
import implementacion.PuenteMensajeEncriptacion;

public class PatronBridgeMain {

    public static void main(String[] args) {
        InterfaceMensajeEncriptacion FormatoAES = new PuenteMensajeEncriptacion(new ProcesoEncriptarAES());
        InterfaceMensajeEncriptacion FormatoDES = new PuenteMensajeEncriptacion(new ProcesoEncriptarDES());
        InterfaceMensajeEncriptacion FormatoCaesar = new PuenteMensajeEncriptacion(new ProcesoEncriptarCaesar());
        InterfaceMensajeEncriptacion SinFormato = new PuenteMensajeEncriptacion(new ProcesoSinEncriptar());
        try {
            final String message = "<Curso><Nombre>Patrones de Diseño de Software</Nombre></Curso>";
            String messageAES = FormatoAES.EncryptarMensaje(message, "HG58YZ3CR9123456");
            System.out.println("Formato AES > " + messageAES + "\n");

            String messageDES = FormatoDES.EncryptarMensaje(message, "XMzDdG4D03CKm2Ix");
            System.out.println("Formato DES > " + messageDES + "\n");

            String messageCaesar = FormatoCaesar.EncryptarMensaje(message, "1234567890");
            System.out.println("Formato Caesar > " + messageCaesar + "\n");

            String messageNO = SinFormato.EncryptarMensaje(message, null);
            System.out.println("Sin Formato > " + messageNO + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
