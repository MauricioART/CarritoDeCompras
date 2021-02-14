package elementos;

import java.util.ArrayList;

/**

 * Esta clase almacena los productos asociados a un departamento de manera dinamica en un ArrayList<Producto>.

 * @author: Mauricio A. Aguilera Roa

 * @version: 01/12/2018/A

 */


public class Departamento{

    private String nombre;
    private ArrayList<Producto> productos;
    
    public Departamento(String nombre){
        this.nombre = nombre;
        this.productos = new ArrayList<Producto>();
    }
    public String getNombre(){
        return this.nombre;
    }
    public ArrayList<Producto> getProductos(){
	return this.productos;
    }
    public Producto getProducto(int index){
        if(this.productos.isEmpty()){
            return null;
        }
        else{
            return this.productos.get(index);
        }
    }
    public void agregarProducto(Producto producto){
	this.productos.add(producto);
    }
}
