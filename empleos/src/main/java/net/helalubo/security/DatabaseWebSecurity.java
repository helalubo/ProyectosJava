package net.helalubo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, estatus from Usuarios where username=? ")
				.authoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up "
						+ "inner join Usuarios u on u.id = up.idUsuario "
						+ "inner join Perfiles p on p.id = up.idPerfil " + "where u.username=?");
		
		//Configuro para que en vez de usar la tabla authorities y users que es por defecto en spring segurity podamos usar nuestras propias
		//tablas. pidiendonos de esta manera cual es la tabla user usando usersByUsernameQuery,
		//y obteniendo la autorizacion desde authoritiesByUsernameQuery haciendo una consulta de tipo inner para encontrar el perfil del usuario ingresado
		//siendo en este caso SUPERVISOR, ADMINISTRADOR, O USUARIO
		//de esto dependera que tipo de vista tendra la aplicacion.
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		//Los recursos estaticos no requieren autentificacion
		.antMatchers(
				"/bootstrap/**",
				"/images/**",
				"/tinymce/**",
				"/logos/**"
				
				
				
				).permitAll()
		//Las vistas publicas no requieren autenticacion
		
		
		.antMatchers(
				"/",
				"/signup",
				"/search",
				"/vacantes/view/**"
				
				).permitAll()
		
		//todas las demas URLs de la aplicacion requieren autenticacion
		.anyRequest().authenticated()
		//El formulario de login no requiere autenticacion
		.and().formLogin().permitAll();
		
		
		
	}
	
	
}
