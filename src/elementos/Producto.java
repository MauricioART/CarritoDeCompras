package elementos;

/**

 * Esta clase carga de manera dinamica los atributos de un producto (nombre, precio, descripcion, direccion de imagen).

 * @author: Mauricio A. Aguilera Roa

 * @version: 01/12/2018/A

 */

public class Producto{

    private final static String DIRECCION_IMAGENES = "./recursos/imagenes";
    private String codigo;
    private String nombre;
    private double precio;
    private String marca;
    private String descripcion;
    private String direccionImagen;
    
    public Producto(String codigo,String nombre,double precio,String marca,String descripcion,String direccionImagen){
        this.codigo = codigo;
	this.nombre = nombre;
        this.precio = precio;
	this.marca = marca;
        this.descripcion = descripcion;
        this.direccionImagen = DIRECCION_IMAGENES + direccionImagen;
    }
    public String getCodigo(){
	return this.codigo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public double getPrecio(){
        return this.precio;
    }
    public String getMarca(){
	return this.marca;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public String getDireccionImagen(){
        return this.direccionImagen;
    }


