
import java.util.ArrayList;
import java.util.Calendar;

public class GeneradorTestJS {

	private ArrayList<Funcion> funciones ;
	
	public GeneradorTestJS(ArrayList<Funcion> funciones) {
		this.funciones = funciones;
	}
	
	public String generarJS(String ruta) {
		String contenidoFichero = "";
		
		
		Calendar c1 = Calendar.getInstance();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		
		contenidoFichero += "// " +dia + "-" + mes + "-" + annio + "\n";		
		contenidoFichero += "// Archivo generado automaticamente \n";
		contenidoFichero += "// Departamento de Front-End CoreBanking\n\n";
		
		contenidoFichero += "// *******************************\n";
		contenidoFichero += "// ********** T E S T S **********\n";
		contenidoFichero += "// *******************************\n\n";
		
		//Linea para importar la libreria
		contenidoFichero += "//Importa la libreria para poder hacer test \n";
		contenidoFichero += "var assert = require(\'assert\'); \n\n";
		
		
		//Llamamos a cada test
		contenidoFichero += "//Ejecucion de test \n";
		for(Funcion cadaFuncion : this.funciones) {
			contenidoFichero += "test" + cadaFuncion.getNombre().substring(0,1).toUpperCase() + cadaFuncion.getNombre().substring(1,cadaFuncion.getNombre().length())+"(); \n"; 
		}
		contenidoFichero += "\n\n";
		
		
		//Escribimos los test vacios
		String nombreTest = "";
		for(Funcion cadaFuncion : this.funciones) {
			contenidoFichero += "function "; 
			
			//nombre del test con la primera letra del nombre de la funcion en mayuscula
			//por ejemplo:  function sonIguales()  -->   function testSonIguales()
			nombreTest  = "test" + cadaFuncion.getNombre().substring(0,1).toUpperCase() + cadaFuncion.getNombre().substring(1,cadaFuncion.getNombre().length());
			
			//Aniadimos los parametros
			nombreTest += "(";
			/* Para poner parametros de entrada
			for(String cadaParametroEntrada : cadaFuncion.getParametrosEntrada()) {
				nombreTest += cadaParametroEntrada + "," ; 
			}
			*/
			//nombreTest = nombreTest.substring(0, nombreTest.length() - 1); //elimino el ultimo caracter porque sera siempre un ,
			 
			nombreTest += "){ \n";
			
			for(String cadaParametroEntrada : cadaFuncion.getParametrosEntrada()) {
				nombreTest += "\tvar "+ cadaParametroEntrada +  "= \"\";" + "\n" ; 
			}			
			
			nombreTest += "\t"+ cadaFuncion.getNombre();
			nombreTest += "(";
			// Para poner parametros de entrada
			for(String cadaParametroEntrada : cadaFuncion.getParametrosEntrada()) {
				nombreTest += cadaParametroEntrada + "," ; 
			}
			
			nombreTest = nombreTest.substring(0, nombreTest.length() - 1); //elimino el ultimo caracter porque sera siempre un ,
			 
			nombreTest += ");";			
			
			nombreTest += "\n\n\tassert(false);";
			
			nombreTest += "\n}; \n \n";
			
			contenidoFichero += nombreTest;
		}
		
		return contenidoFichero;
		
	}

	
}
