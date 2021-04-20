package com.example.pucp.Controller;

import com.example.pucp.Entity.Area;
import com.example.pucp.Entity.Usuario;
import com.example.pucp.Repository.AreaRepository;
import com.example.pucp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AreaRepository areaRepository;

   @GetMapping("/lista")
    public String lista(Model model){
       model.addAttribute("listaUser",usuarioRepository.findAll());
       return "proyecto/usuario/lista";
   }

   @GetMapping("/editar")
    public String editar(Model model, @RequestParam("id") String id){
       Optional<Usuario> usuarioOptional= usuarioRepository.findById(id);
       if(usuarioOptional.isPresent()){
            model.addAttribute("listaAreas",areaRepository.findAll());
            model.addAttribute("usuario",usuarioOptional.get());

           return "proyecto/usuario/editar";
       }else{
           return "redirect:/usuario/lista";
       }

   }

   @PostMapping("/guardar")
    public String guardar(Usuario usuario, Area area){
        usuarioRepository.save(usuario);


       return "redirect:/usuario/lista";
   }




}
