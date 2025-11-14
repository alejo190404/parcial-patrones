package encriptacion;

public class ProcesoEncriptarCaesar implements InterfaceEncriptar {

    @Override
    public String encryptar(String message, String password) throws Exception {
        // Usar la longitud de la contraseña como el desplazamiento
        int shift = password.length() % 27; // Limitar a 27 para incluir la ñ
        
        StringBuilder encrypted = new StringBuilder();
        
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c) || c == 'ñ' || c == 'Ñ') {
                if (c == 'ñ') {
                    int newPosition = (14 + shift) % 27; // ñ está en posición 14
                    encrypted.append(newPosition == 14 ? 'ñ' : (char) ('a' + newPosition));
                } else if (c == 'Ñ') {
                    int newPosition = (14 + shift) % 27; // Ñ está en posición 14
                    encrypted.append(newPosition == 14 ? 'Ñ' : (char) ('A' + newPosition));
                } else {
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    int originalPosition = c - base;
                    
                    // Ajustar posición para incluir ñ después de la n
                    if (originalPosition >= 14) { // Después de la 'n'
                        originalPosition++; // Desplazar una posición para hacer espacio a ñ
                    }
                    
                    int newPosition = (originalPosition + shift) % 27;
                    
                    // Manejar el caso especial de ñ en la nueva posición
                    if (newPosition == 14) {
                        encrypted.append(Character.isUpperCase(c) ? 'Ñ' : 'ñ');
                    } else if (newPosition > 14) {
                        newPosition--; // Ajustar de vuelta para el alfabeto normal
                        encrypted.append((char) (base + newPosition));
                    } else {
                        encrypted.append((char) (base + newPosition));
                    }
                }
            } else {
                // Mantener caracteres que no son letras sin cambios
                encrypted.append(c);
            }
        }
        
        return encrypted.toString();
    }
    
}
