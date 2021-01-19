package net.helalubo.util;


import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

	
	
	public static String guardarArchivo(MultipartFile multiPart,String ruta) {
		
		//Obtenemos el nombre original del archivo
		
		String nombreOriginal = multiPart.getOriginalFilename();
		
		//Agrego guiones reemplazando espacios
		nombreOriginal =nombreOriginal.replace(" ", "-");
		String nombreFinal= randomAlphaNumeric(8) + nombreOriginal;
		
		try {
			
			File imageFile = new File(ruta + nombreFinal);
			System.out.println("Archivo " + imageFile);
			//Guardamos fisicamente el archivo en HD.
			
			multiPart.transferTo(imageFile);
			
			return nombreOriginal; 
			
			
		}catch (IOException e) {
			
			System.out.println("Error: " + e.getMessage() );
			return null;
		}
		
	}
	
	/**
	 * Metodo para generar una cadena aleatoria de longitud N
	 * @param count
	 * @return cadena modificada
	 */
	
	public static String randomAlphaNumeric(int count)
	{
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder();
		
		while(count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			sb.append(CARACTERES.charAt(character));
		}
		
		return sb.toString();
	}
	
	
}
