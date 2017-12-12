
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerFichero {

	public int numeroLineas;
	public String ruta;
	public FileReader f ;
	public BufferedReader b;
	
	
	public LeerFichero(String ruta) {
		this.ruta = ruta;
		try {
			this.f = new FileReader(this.ruta);
			this.b = new BufferedReader(f);
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo \n");
			e.printStackTrace();
		}

	}
	
	public  String leer() {
		String contenido = "";
		
		String cadaLinea ="";
		while( cadaLinea  != null) {
			cadaLinea = leerSiguiente();
			if (cadaLinea != null) {
				contenido += cadaLinea +"\n";
			}
		}
				
		return contenido;
	}
	
	public String leerSiguiente() {
		try {
			return b.readLine(); //devolvemos la linea			
		}catch (Exception e) {
			return null; //devolvemos null cuando no haya nada mas que devolver
		}
	}

	public void muestraContenido() throws FileNotFoundException, IOException {
		String cadena;

		while ((cadena = b.readLine()) != null) {
			this.numeroLineas++;
			System.out.println(cadena);
		}

		b.close();
	}
}
