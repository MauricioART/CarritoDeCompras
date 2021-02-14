package elementos;

import java.util.ArrayList;

/**

 * Esta clase almacena de manera dinamica los productos que el usuario esté por comprar, además de realizar los calculos asociados al precio total 

 * (subtotal, impuestos y total)  de los productos agregados. 

 * @author: Mauricio A. Aguilera Roa

 * @version: 01/12/2018/A

 */

public class Carrito {
    
    private final static double PORCENTAJE_IMPUESTOS = 0.15;
    private String llave;
    private ArrayList<Producto> producto;
    private ArrayList<Integer> numeroElementos;
    private double subtotal;
    private double total;
    private double impuestos;

    public Carrito(String llave){
	this.llave = llave;
        this.producto = new ArrayList<Producto>();
        this.numeroElementos = new ArrayList<Integer>();
        this.subtotal = 0.00;
        this.total = 0.00;
        this.impuestos = 0.00;
    }
    public String getLlave(){
	return this.llave;
    }
    public ArrayList<Producto> getProducto(){
	return this.producto;
    }
    public Producto getProducto(int index){
	return this.producto.get(index);
    }
    public ArrayList<Integer> getNumeroElementos(){
	return this.numeroElementos;
    }
    public int getNumeroElementos(int index){
	return this.numeroElementos.get(index);
    }
    public int getNumeroProductos() {
	int numeroProductos = 0;
	for(int i=0;i<numeroElementos.size();i++){
		numeroProductos += numeroElementos.get(i);
	}
    	return numeroProductos; 
    }
    public void setCantidadProducto(int index,int cantidad){
	this.numeroElementos.add(index,cantidad);
    }
    public double getSubtotal(){
        return this.subtotal;
    }
    public double  getTotal(){
        return this.total;
    }
    public double getImpuestos(){
        return this.impuestos;
    }
    public void agregarProducto(Producto productoAgregado, int numeroElementos){
        if (this.producto.contains(productoAgregado)){
            int posicionProducto,numeroElementosAnt,numeroElementosDes;
            posicionProducto = this.producto.indexOf(productoAgregado);
            numeroElementosAnt = this.numeroElementos.get(posicionProducto);
            numeroElementosDes = numeroElementosAnt + numeroElementos;
            this.numeroElementos.set(posicionProducto,numeroElementosDes);
        }
        else{
            this.producto.add(productoAgregado);
            this.numeroElementos.add(numeroElementos);
        }
    }
    public void eliminarProducto(Producto producto){
        int posicionProducto;
        posicionProducto = this.producto.indexOf(producto);
        this.producto.remove(producto);
        this.numeroElementos.remove(posicionProducto);
    }
    public void eliminarProducto(int index){
	this.producto.remove(index);
	this.numeroElementos.remove(index);	
    }
    public void sumarSubtotal(){
        int i ;
	this.subtotal = 0.00;
        for(i=0;i<this.producto.size();i++){
            this.subtotal = this.subtotal + (this.producto.get(i).getPrecio() * this.numeroElementos.get(i));
        }
    }
    public void calcularTotal(){
        this.impuestos = this.subtotal * PORCENTAJE_IMPUESTOS;
        this.total = this.subtotal + this.impuestos;
    }
}
