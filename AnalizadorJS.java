
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AnalizadorJS {

	private String js;

	public AnalizadorJS(String cadena) {
		js = cadena;
	}

	public ArrayList<Funcion> devolverFunciones() {
		ArrayList<Funcion> funciones = new ArrayList<>();
		StringTokenizer tokens = new StringTokenizer(this.js, "\n");
		
		String cadaLinea = "";
		while (tokens.hasMoreTokens()) {
			cadaLinea = tokens.nextToken();
			if(cadaLinea.contains("function")) { //es una linea que contiene la cabecera de una funcion
				Funcion funcion = new Funcion(cadaLinea);
				funciones.add(funcion);
			}	
		}

		return funciones;
	}
	
	
	

}
