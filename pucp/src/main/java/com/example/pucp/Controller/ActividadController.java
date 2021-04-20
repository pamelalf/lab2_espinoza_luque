package com.example.pucp.Controller;

import com.example.pucp.Entity.Actividad;
import com.example.pucp.Entity.Usuario;
import com.example.pucp.Repository.ActividadRepository;
import com.example.pucp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/actividad")
public class ActividadController {
    @Autowired
    ActividadRepository actividadRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public String lista(Model model){
        model.addAttribute("listaAc", actividadRepository.findAll());

        return "proyecto/actividades/lista";
    }

    @GetMapping("/agregar")
    public String nuevo(Model model){
        model.addAttribute("listaActividades",actividadRepository.findAll());
        model.addAttribute("listaUsers",usuarioRepository.findAll());
        return "proyecto/actividades/nuevo";
    }

    @GetMapping("/editar")
    public String editar(Model model, @RequestParam("id") int id){
        System.out.println("El id es : " + id);
        Optional<Actividad> actividadOptional=actividadRepository.findById(id);
        if(actividadOptional.isPresent()){
            Actividad actividad= actividadOptional.get();
            System.out.println(actividad.getNombreactividad());
            List<Usuario> usuarioList=usuarioRepository.findAll();
            System.out.println(actividad.getNombreactividad());
            model.addAttribute("listaUsuarios",usuarioRepository.findAll());
            model.addAttribute("actividad",actividadOptional.get());

            return "proyecto/actividades/editar";
        }else{
            return "redirect:/actividad/listar";
        }


    }
    @PostMapping("/guardar")
    public String guardar( Actividad actividad){


        actividadRepository.save(actividad);
        return "redirect:/actividad/listar";

    }

}
