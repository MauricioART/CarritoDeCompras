package almacen;

import java.io.*;
import java.util.*;
import elementos.*;
import herramientas.ManejadorDeInformacion;

/**

 * Esta clase almacena de manera dinamica los productos que el usuario esté por comprar, además de realizar los calculos asociados al precio total

 * (subtotal, impuestos y total)  de los productos agregados.

 * @author: Mauricio A. Aguilera Roa

 * @version: 01/12/2018/A

 */

public class CargadorDeUsuarios{

	private static final String DIRECCION_REGISTROS_USUARIOS = "./registros/Usuarios.txt";
        private static final String SEPARADOR_INFORMACION = ":";
        private Usuario usuario;
	private int usuarioPosicion;
        File registro;

	public CargadorDeUsuarios(){
		this.registro = new File(DIRECCION_REGISTROS_USUARIOS);
		this.usuarioPosicion = -1;
	}

	public Usuario getUsuario(){
                return this.usuario;
        }

	public boolean cargarUsuario(String usuario){
		ManejadorDeInformacion mInfo;
		BufferedReader br;
                try{    
                        String usuarioDatos;
                        br = new BufferedReader(new FileReader(registro));
			while((usuarioDatos = br.readLine()) != null){
				mInfo = new ManejadorDeInformacion(usuarioDatos,SEPARADOR_INFORMACION);
				mInfo.separarInformacion();
                                this.usuarioPosicion = this.usuarioPosicion + 1;
				if (usuario.equals(mInfo.getDato(0))){
                                      	this.usuario = new Usuario(mInfo.getDato(0),mInfo.getDato(1),mInfo.getDato(2),Integer.parseInt(mInfo.getDato(3)),mInfo.getDato(4));
					return true;
                                }
                        }
                        return false;
                        
                }catch(NullPointerException npe){
                        return false;
                }catch(Exception e){
                        return false;
                }
        }

	public void actualizarIntentos(){
		ArrayList<String> usuarios = new ArrayList<String>();
		String usuario;
		BufferedReader br;
		BufferedWriter bw;
		ManejadorDeInformacion manejadorInfo = new ManejadorDeInformacion(this.usuario.getDatosUsuario(),SEPARADOR_INFORMACION);
		try{
			br = new BufferedReader(new FileReader(registro));
			while((usuario = br.readLine()) != null){
				usuarios.add(usuario);
			}
			br.close();
			usuarios.remove(this.usuarioPosicion);
			usuarios.add(manejadorInfo.unirInformacion());
			bw = new BufferedWriter(new FileWriter(registro));
			int numeroUsu = usuarios.size();
			for(int i =0;i<numeroUsu;i++){
				bw.write(usuarios.get(i));
				bw.newLine();
				bw.flush();
			}	
			bw.close();
			
		}
		catch(IndexOutOfBoundsException i){
			System.out.println("IndexOutOfBounds");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
