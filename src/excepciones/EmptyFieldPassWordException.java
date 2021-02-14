package excepciones;

public class EmptyFieldPassWordException extends Exception {
    public EmptyFieldPassWordException() {
        super("Error: El campo password esta vacio, favor de verificar");
    }
}
