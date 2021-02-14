package elementos;

import java.util.ArrayList;

/**

 * Esta clase almacena de manera dinamica los atributos del cliente registrado en la tienda

 * @author: Mauricio A. Aguilera Roa

 * @version: 01/12/2018/A

 */

public class Usuario{

    private final static int INTENTOS_POSIBLES = 5;
    private String usuario;
    private String nombre;
    private String contrasenia;
    private Carrito carrito;
    private int intentosRestantes;
    private String llaveCarrito;

    public Usuario(String usuario,String nombre,String contrasenia){
        this.usuario = usuario;
	this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.carrito = new Carrito("0");
        this.intentosRestantes = INTENTOS_POSIBLES;
    }
    public Usuario(String usuario,String nombre,String contrasenia,int intentosRestantes,String llaveCarrito){
	this.usuario = usuario;
	this.nombre = nombre;
        this.contrasenia = contrasenia;
	this.carrito = new Carrito(llaveCarrito);
        this.intentosRestantes = intentosRestantes;
	this.llaveCarrito = llaveCarrito;
    }
    public String getUsuario(){
    	return this.usuario;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getContrasenia(){
        return this.contrasenia;
    }
    public Carrito getCarrito(){
        return this.carrito;
    }
    public int getIntentosRestantes(){
        return this.intentosRestantes;
    }
    public String getLlaveCarrito(){
	return this.llaveCarrito;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    public void setIntentosRestantes(int intentosRestantes){
        this.intentosRestantes = intentosRestantes;
    }
    public void setCarrito(Carrito carrito){
	this.carrito = carrito;
    }
    public boolean validarCuentaActiva(){
        if(this.intentosRestantes>0)
            return true;
        else{
            return false;
        }
    }
    public ArrayList<String> getDatosUsuario(){
	ArrayList<String> datos = new ArrayList<String>();
	datos.add(this.usuario);
	datos.add(this.nombre);
	datos.add(this.contrasenia);
	datos.add(String.valueOf(this.intentosRestantes));
	datos.add(this.llaveCarrito);
	return datos;
    }
    public void restarIntentos(){
	if(this.intentosRestantes>0){
        	this.intentosRestantes = this.intentosRestantes - 1;
	}
    }
}
