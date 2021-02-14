package almacen;

import java.io.*;
import elementos.*;
import herramientas.*;
import java.util.ArrayList;

public class CargadorDeCarritos{

	private static final String DIRECCION_REGISTRO_CARRITOS = "./registros/Carritos.txt";
	private static final String DIRECCION_REGISTROS_DEPARTAMENTOS = "./registros/Departamentos";
	private static final String SEPARADOR_INFORMACION = ":";	
	private Carrito carrito;
	File registro;

	public CargadorDeCarritos(){
		this.registro = new File(DIRECCION_REGISTRO_CARRITOS);
	}

	public Carrito getCarrito(){
		System.out.println(this.carrito.getProducto().size());
                return this.carrito;
        }

	public boolean cargarCarrito(String llave){
		
		ManejadorDeInformacion mInfo;
		BufferedReader br;
		ArrayList<String> datos = new ArrayList<String>();
                String buffer;
		int numeroProductos;                
		int linea = 0;
		try{
			System.out.println("CARGANDO CARRITO");	
                        br = new BufferedReader(new FileReader(registro));
                        while((buffer = br.readLine()) != null){
				System.out.println("CODIGO DE CARRITO EN LINEA "+ String.valueOf(linea)+" CON CODIGO "+buffer);
                                mInfo = new ManejadorDeInformacion(buffer,SEPARADOR_INFORMACION);
                                datos = mInfo.separarInformacion();
                                if (llave.equals(mInfo.getDato(0))){
					System.out.println("CARRITO DEL USUARIO CON LLAVE "+ llave +" ENCONTRADO");
                                        this.carrito = new Carrito(llave);
					CargadorDeDepartamentos cdd;
					numeroProductos = (datos.size() - 1);
					for(int i=0;i<numeroProductos;i=i+2){
						cdd = new CargadorDeDepartamentos(evaluarDepartamento(Integer.parseInt(datos.get(i+1))));
						carrito.agregarProducto(cdd.getProductoDepartamento(datos.get(i+1)),Integer.parseInt(datos.get(i+2)));
						System.out.println("CARGANDO " + cdd.getProductoDepartamento(datos.get(i+1)).getNombre()+" DEL CARRITO");
					}
					return true;	
                                }
				linea++;
                        }
			System.out.println("CARRITO NO ENCONTRADO");
			return false;

                }catch(Exception e){
        		return false;
		}
	}

	public void guardarCarrito(Usuario usuario){
		
		ArrayList<String> carritos = new ArrayList<String>();
		ArrayList<String> productos = new ArrayList<String>();
		BufferedWriter bw;
		BufferedReader br;
		String buffer;
		ManejadorDeInformacion manejador;
		int posicionEnArchivo = 0;
		int posicionCarrito = 0;
		boolean encontrado = false;
		try{
			br = new BufferedReader(new FileReader(registro));
			while((buffer = br.readLine()) != null){
				carritos.add(buffer);
				manejador = new ManejadorDeInformacion(buffer,SEPARADOR_INFORMACION);
				manejador.separarInformacion();
				System.out.println("BUSCANDO CARRITO DE USUARIO EN ARCHIVO");
				if(usuario.getLlaveCarrito().equals(manejador.getDato(0))){
					posicionCarrito = posicionEnArchivo;
					System.out.println("CARRITO ENCONTRADO!!!"+"EN LA LINEA "+String.valueOf(posicionCarrito)+" DEL ARCHIVO");	
					encontrado = true;
				}
				posicionEnArchivo++;
				
			}
			br.close();
			if (carritos.isEmpty()){
				System.out.println("ARCHIVO DE CARRITOS VACIO");
                                bw = new BufferedWriter(new FileWriter(registro,true));
                                int numeroProductos = usuario.getCarrito().getProducto().size();
                                productos.add(usuario.getLlaveCarrito());
                                for(int i = 0;i<numeroProductos;i++){
                                        productos.add(String.valueOf(usuario.getCarrito().getProducto(i).getCodigo()));
                                        productos.add(String.valueOf(usuario.getCarrito().getNumeroElementos(i)));
                                	System.out.println("AGREGANDO "+usuario.getCarrito().getProducto(i).getNombre()+" AL BUFFER DEL CARRITO");
				}
                                manejador = new ManejadorDeInformacion(productos,SEPARADOR_INFORMACION);
				carritos.add(manejador.unirInformacion());
                                bw.write(carritos.get(0));
                                System.out.println("AGREGANDO CARRITO AL ARCHIVO CON CODIGO"+carritos.get(0));
				bw.newLine();
                                bw.flush();
                                bw.close();
			}
			else{
				if(encontrado){
					System.out.println("REMOVIENDO CARRITO CON CODIGO "+ carritos.get(posicionCarrito));
					carritos.remove(posicionCarrito);
					System.out.println("CARRITOS RESTANTES");
					for (int i=0;i<carritos.size();i++){
						System.out.println(carritos.get(i));
					}
				}
				bw = new BufferedWriter(new FileWriter(registro));
				int numeroProductos = usuario.getCarrito().getProducto().size();
				productos.add(usuario.getLlaveCarrito());
				for(int i = 0;i<numeroProductos;i++){
					productos.add(String.valueOf(usuario.getCarrito().getProducto(i).getCodigo()));
					productos.add(String.valueOf(usuario.getCarrito().getNumeroElementos(i)));
					System.out.println("AGREGANDO PRODUCTO CON CODIGO "+usuario.getCarrito().getProducto(i).getCodigo()+" Y NOMBRE "+ usuario.getCarrito().getProducto(i).getNombre()+" AL CARRITO\n");
				}
				manejador = new ManejadorDeInformacion(productos,SEPARADOR_INFORMACION);
				carritos.add(manejador.unirInformacion());
				for(int i=0;i<numeroProductos;i++){
					System.out.println("AGREGANDO A ARCHIVO CARRITO CON CODIGO "+ carritos.get(i)+"\n");
					bw.write(carritos.get(i));
					bw.newLine();
					bw.flush();
				}
				bw.close();
			}
		}
		catch(Exception e){
			
		}
	}

	public String evaluarDepartamento(int codigo){
		String departamento = "";;
		if (codigo>=0 && codigo<100){
                	departamento = "Electronica"; 
		}
                if(codigo>=100 && codigo<200){
			departamento = "Juguetes";
                }
                if (codigo>=200 && codigo<300){
                	departamento = "Ropa";
		}
                if (codigo>=300 && codigo<400){
                	departamento = "Videojuegos";
		}
		return departamento;
	}
}
