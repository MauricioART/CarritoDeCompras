package excepciones;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(int intentos) { 
        super("Error: Contraseña incorrecta, favor de verificar\n Intentos restantes: " + intentos);
    }
}
