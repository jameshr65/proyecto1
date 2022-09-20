package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/getUsers")
	public ArrayList<UsuarioModel> obtenerUsuarios(){
		return usuarioService.obtenerUsuarios();
	}
	
	@PostMapping("/guardarUsuario")
	public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
		return this.usuarioService.guardarUsuario(usuario);
	}
	
	@GetMapping("/obtenerUsuario/{id}")
	public 	Optional<UsuarioModel> obtenerUsuario(@PathVariable Long id) {
		return usuarioService.obtenerUsuarioPorId(id);
	}
	
	@PutMapping("/actualizarUsuario/{id}")
	public String  actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
		
		if(id == null) {
			return "No se envio un id";
		}
		
		if(!usuarioRepository.existsById(id)) {
			return "No existe un usuario con id"+id;
		}
		
		usuarioService.guardarUsuario(usuario);
		return "Usuaro con id "+id+" actualizado con exito";
	}
		
	@DeleteMapping("/eliminarUsuario/{id}")
	public String elimnarPorId(@PathVariable("id") Long id) {
		boolean ok = this.usuarioService.eliminarUsuario(id);
		if (ok) {
			return "Se elimino el usuario con id " + id;
		}else {
				return "No pudo eliminar el usuario con id " + id;
		}	
	}

}
