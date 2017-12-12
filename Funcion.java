
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.omg.Messaging.SyncScopeHelper;

public class Funcion {

	private ArrayList<String> parametrosEntrada;
	private String nombre;
	private String cuerpo;
	
	public Funcion() {

	}

	// entra una cadena en el siguiente formato sonIguales(cadena1, cadena2)
	public Funcion(String entradaSinFormato) { 
		
		this.parametrosEntrada = new  ArrayList<>();
		
		entradaSinFormato = entradaSinFormato.replace("function ", "");
		entradaSinFormato = entradaSinFormato.replace("{", "");		
		
		this.nombre = entradaSinFormato.substring(0,entradaSinFormato.indexOf("("));
		
		String parametrosEntrada = "";
		 parametrosEntrada = entradaSinFormato.substring(entradaSinFormato.indexOf("(")+1, entradaSinFormato.indexOf(")"));
		
		 StringTokenizer tokens = new StringTokenizer(parametrosEntrada, ",");
		
		String cadaLinea = "";
		while (tokens.hasMoreTokens()) {
			cadaLinea = tokens.nextToken();
			cadaLinea = cadaLinea.trim();
			this.parametrosEntrada.add(cadaLinea);
		}
				
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getParametrosEntrada() {
		return parametrosEntrada;
	}

	public void setParametrosEntrada(ArrayList<String> parametrosEntrada) {
		this.parametrosEntrada = parametrosEntrada;
	}

	
	
}
