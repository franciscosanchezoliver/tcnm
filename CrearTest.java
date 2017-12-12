
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CrearTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {		
		//si los argumentos no son correctos entonces el programa pararia aqui
		//comprobarArgumentosDeEntrada(args); 
		
		
		File archivo = new File(args[0]); 
		
		System.out.println(archivo.getAbsolutePath());
		
		System.out.println("Argumento 0 :" + args[0]);
		
		System.out.println( System.getProperty("user.dir") );
		
		if (args.length == 1 ) {
			generaCon1argumento(args);
		}
		else if (args.length ==2) {
			generaCon2argumento(args);
		}else {
			String mensajeError = "ERROR: El numero de argumentos pasados no es correcto. \n" +
								  "Opciones disponibles: \n" +
								  "\tUn parametro: ruta del archivo js del que queremos crear los test, los test se generaran en el mismo directorio \n" +
								  "\tDos parametros: El primer parametro indica la ruta del archivo js fuente y el segundo parametro la ruta donde se generara el test";
			System.out.println(mensajeError);
		}
	
		//fin del programa
		System.exit(0);
	}


	/**
	 * Genera el test en el mismo directorio que el archivo de entrada
	 * @param args
	 */
	private static void generaCon1argumento(String[] args) {
		File fichero = new File(args[0]);
		
		//cambiamos las barras / por 2 barras invertidas para poder leer y escribir correctamente
		args[0] = args[0].replace("/", "\\");
		
		String rutaArchivoEntrada = args[0];
		
		//Cogemos el nombre del archivo JS
		String nombreArchivoJS = getNombreArchivo(rutaArchivoEntrada);
		
		System.out.println("\n\nGenerando test...");
		
		//leemos el fichero de entrada
		LeerFichero leerFichero = new LeerFichero(rutaArchivoEntrada);
		String contenidoFichero = leerFichero.leer();
		
		System.out.println("\tFichero leido");

		//Analizamos el fichero de entrada JS
		AnalizadorJS analizadorJS = new AnalizadorJS(contenidoFichero);
		ArrayList<Funcion> funciones =  analizadorJS.devolverFunciones();
		
		System.out.println("\tFichero analizado");
		
		//Generamos los test
		GeneradorTestJS generadorTestJS = new GeneradorTestJS(funciones);		
		String js =  generadorTestJS.generarJS(rutaArchivoEntrada);
		
		js += "// ***************************************\n";
		js += "// ********** F U N C I O N E S **********\n";
		js += "// ***************************************\n\n";
		js += contenidoFichero; //anniadimos al final las funciones que utilizara el test
		
		System.out.println("\tCasos de prueba generados");
		
		//le ponemos el nombre del archivo precedido de la palabra Test
		//Por ejemplo si el archivo de entrada es funciones.js entonces el archivo de salida es TestFunciones.js
		nombreArchivoJS = "\\Test" + nombreArchivoJS.substring(0,1).toUpperCase() + nombreArchivoJS.substring(1, nombreArchivoJS.length());
		
		String nuevaRuta = generarRutaSalida(rutaArchivoEntrada, nombreArchivoJS);
		
		//Guardamos el caso de test generado
		EscritorFichero.escribir(js, nuevaRuta);
		System.out.println("\tFichero escrito en: " + nuevaRuta);
		
	}	
	



	/**
	 * Recibe 2 argumentos
	 * 1º argumento: la direccion del fichero js del que queremos crear los test
	 * 2º argumento: la direccion donde queremos generar los test
	 * @param args
	 */
	private static void generaCon2argumento(String[] args) {
		
		//cambiamos las barras / por 2 barras invertidas para poder leer y escribir correctamente
		args[0] = args[0].replace("/", "\\");
		args[1] = args[1].replace("/", "\\");		
		
		String rutaArchivoEntrada = args[0];
		String rutaArchivoSalida = args[1];
		
		System.out.println("\n\nGenerando test...");
		
		//leemos el fichero de entrada
		LeerFichero leerFichero = new LeerFichero(rutaArchivoEntrada);
		String contenidoFichero = leerFichero.leer();
		
		System.out.println("\tFichero leido");

		//Analizamos el fichero de entrada JS
		AnalizadorJS analizadorJS = new AnalizadorJS(contenidoFichero);
		ArrayList<Funcion> funciones =  analizadorJS.devolverFunciones();
		
		System.out.println("\tFichero analizado");
		
		//Generamos los test
		GeneradorTestJS generadorTestJS = new GeneradorTestJS(funciones);		
		String js =  generadorTestJS.generarJS(rutaArchivoEntrada);
		
		js += "// ***************************************\n";
		js += "// ********** F U N C I O N E S **********\n";
		js += "// ***************************************\n\n";
		js += contenidoFichero; //anniadimos al final las funciones que utilizara el test
		
		System.out.println("\tCasos de prueba generados");
		
		//Escribimos el fichero
		EscritorFichero.escribir(js, rutaArchivoSalida);
		System.out.println("\tFichero escrito en: " + rutaArchivoSalida);
		
	}
	
	public static String getNombreArchivo(String ruta) {
		int slashIndex = ruta.lastIndexOf('\\');
		int dotIndex = ruta.lastIndexOf('.', slashIndex);
		String nombreArchivoJS;
		if (dotIndex == -1){
		  nombreArchivoJS = ruta.substring(slashIndex + 1);
		}
		else{
		  nombreArchivoJS = ruta.substring(slashIndex + 1, dotIndex);
		}		
		return nombreArchivoJS;
	}
	
	private static String generarRutaSalida(String rutaArchivoEntrada, String nombreArchivoJS) {
		//Generamos la misma ruta que donde esta el archivo de entrada
		int slashIndex = rutaArchivoEntrada.lastIndexOf('\\');
		String nuevaRuta = rutaArchivoEntrada.substring(0, slashIndex)+ nombreArchivoJS;
		nombreArchivoJS = rutaArchivoEntrada.substring(slashIndex + 1);
		return nuevaRuta;
	}	


}
