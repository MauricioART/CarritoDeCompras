package excepciones;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("El usuario no es correcto, favor de verificar");
    }
}
