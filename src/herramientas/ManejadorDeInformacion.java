package herramientas;

import java.util.StringTokenizer;
import java.util.ArrayList;

public class ManejadorDeInformacion{
	private StringTokenizer strTkn;
	private ArrayList<String> informacionSeparada;
	private String informacionUnida;
	private String separador;

	public ManejadorDeInformacion(ArrayList<String> informacionSeparada,String separador){
		this.informacionSeparada = informacionSeparada;
		this.informacionUnida = "";
		unirInformacion();
		this.strTkn = new StringTokenizer(informacionUnida,separador);
                this.separador = separador;
	}
	public ManejadorDeInformacion(String informacionUnida,String separador){
		this.strTkn = new StringTokenizer(informacionUnida,separador);
		this.informacionSeparada = new ArrayList<String>();
		this.informacionUnida = "";
		this.separador = separador;
	}
        
	public ArrayList<String> separarInformacion(){
		String dato;
		int numeroDatos = strTkn.countTokens();
		for(int i=0;i<numeroDatos;i++){
			this.informacionSeparada.add(strTkn.nextToken());
		}
		return informacionSeparada;
	}
	public String unirInformacion(){
		int numeroDatos = this.informacionSeparada.size();
		for(int i=0;i<numeroDatos;i++){
			if (i==0)
				informacionUnida = informacionSeparada.get(i);
			else
				informacionUnida = informacionUnida + separador + informacionSeparada.get(i);
		}
		return informacionUnida;
	}
	public void setInformacionSeparada(ArrayList<String> informacionSeparada){
		this.informacionSeparada = informacionSeparada;
	}
	public void setInformacionUnida(String informacionUnida){
		this.informacionUnida = informacionUnida;
	}
	public String getDato(int indice){
		return informacionSeparada.get(indice);
	}
}
