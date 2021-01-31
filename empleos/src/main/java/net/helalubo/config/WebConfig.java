package net.helalubo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 
 * Creo clase de configuracion para poder cambiar la ruta de recursos estaticos,
 * es este caso la ruta es una creada en d/empleos
 * la cual esta ligada a la subida de imagenes
 * 
 * luego lo unico que se debe haces es usar el alias (en este  caso /logos/) 
 * para poder acceder a las imagenes agregandole el nombre,
 * 
 * 
 * */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	   @Value("${empleosapp.ruta.imagenes}")
		private String rutaImagenes;

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/logos/**").addResourceLocations("file:
		// /empleos/img-vacantes/"); // Linux
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:d:/empleos/img-vacantes/"); // Windows
		registry.addResourceHandler("/logos/**").addResourceLocations("file:" +  rutaImagenes); // Windows
	}

}
