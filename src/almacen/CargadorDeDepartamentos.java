package almacen;

import java.io.*;
import elementos.*;
import java.util.StringTokenizer;

/**

 * Esta clase se encarga de acceder a los ficheros de Cliente

 * @author: Mauricio A. Aguilera Roa

 * @version: 01/12/2018/A

 */

public class CargadorDeDepartamentos{

        private static final String DIRECCION_REGISTROS_DEPARTAMENTOS = "./registros/Departamentos/";
        private static final String SEPARADOR_INFORMACION = ":";
        File registro;
        Usuario usuario;
        Departamento departamento;
	
	public CargadorDeDepartamentos(){
	}
	
	public CargadorDeDepartamentos(String departamento){
                        try{                    
                                registro = new File(DIRECCION_REGISTROS_DEPARTAMENTOS+departamento+".txt");
                                this.departamento = new Departamento(departamento);
                        }catch(Exception e){
                                System.out.println("Archivo de departamento no encontrado");
                        }
        }
	
	public Departamento getDepartamento(){
		return this.departamento;
	}
	
	public Producto getProductoDepartamento(String codigo){
		try{
			leerRegistroDepartamentos();
			int cantidadProductos = this.departamento.getProductos().size();
			for(int i=0;i<cantidadProductos;i++){
				if(this.departamento.getProducto(i).getCodigo().equals(codigo)){
					return this.departamento.getProducto(i);
				}
			}
			return null;
		}
		catch(Exception e){
			return null;

		}
	}	
	
	public void leerRegistroDepartamentos(){

                BufferedReader br;
                try{    
                        br  = new BufferedReader(new FileReader(registro));
                        StringTokenizer sTkn;
                        String[] dato = new String[6];
                        String producto;
                        while((producto = br.readLine()) != null){
				sTkn = new StringTokenizer(producto,SEPARADOR_INFORMACION);
                                for(int i=0;i<6;i++){
                                        dato[i] = sTkn.nextToken();
                                }
                                this.departamento.agregarProducto(new Producto(dato[0],dato[1],Double.parseDouble(dato[2]),dato[3],dato[4],dato[5]));
			}

                }
                catch(Exception e){
                }
        }


}
