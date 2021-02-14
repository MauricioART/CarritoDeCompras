package excepciones;

public class TooManyAtempsException extends Exception {
    public TooManyAtempsException() { 
        super("Error: Maximo numero de intentos, intente mas tarde");
    }
}
