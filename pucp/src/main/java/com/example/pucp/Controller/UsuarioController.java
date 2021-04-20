package com.example.pucp.Controller;

import com.example.pucp.Entity.Area;
import com.example.pucp.Entity.Usuario;
import com.example.pucp.Repository.AreaRepository;
import com.example.pucp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AreaRepository areaRepository;

   @GetMapping("/listar")
    public String listaUser(Model model){
       List<Usuario> usuarioList=usuarioRepository.findAll();
       model.addAttribute("listaUser",usuarioRepository.findAll());
       return "proyecto/usuario/lista";
   }
    @GetMapping("/agregar")
    public String agregarUser(Model model){

        model.addAttribute("listaUser",usuarioRepository.findAll());
        model.addAttribute("listaAreas",areaRepository.findAll());
        return "proyecto/usuario/nuevo";
    }


    @GetMapping("/editar")
    public String editarUser(Model model, @RequestParam("id") String id){
       Optional<Usuario> usuarioOptional= usuarioRepository.findById(id);
       if(usuarioOptional.isPresent()){
            model.addAttribute("listaAreas",areaRepository.findAll());
            model.addAttribute("usuario",usuarioOptional.get());

           return "proyecto/usuario/editar";
       }else{
           return "redirect:/usuario/listar";
       }

   }

   @PostMapping("/guardar")
    public String guardarUser(Usuario usuario,  RedirectAttributes ra){


        usuarioRepository.save(usuario);
       Optional<Usuario> usuarioOptional= usuarioRepository.findById(usuario.getCorreo());
       if(usuarioOptional.isPresent()){
           ra.addFlashAttribute("msgCreate","Usuario creado exitosamente");
       }else {
           ra.addFlashAttribute("msgEdit","Usuario actualizado exitosamente");
       }
       usuarioRepository.save(usuario);

       return "redirect:/usuario/listar";
   }




}
