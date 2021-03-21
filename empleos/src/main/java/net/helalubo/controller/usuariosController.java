package net.helalubo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.helalubo.model.Usuario;
import net.helalubo.service.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class usuariosController {

	@Autowired
	private IUsuarioService usuarioServicio;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {

		List<Usuario> listaUsuarios = usuarioServicio.BuscarTodos();

		model.addAttribute("usuarios", listaUsuarios);

		return "usuarios/listUsuarios";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {

		usuarioServicio.eliminar(idUsuario);

		return "redirect:/usuarios/index";
	}

}
