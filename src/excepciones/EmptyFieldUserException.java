package excepciones;

public class EmptyFieldUserException extends Exception{

	public EmptyFieldUserException(){
		super("Campo de Usuario Vacio");
	}
}
