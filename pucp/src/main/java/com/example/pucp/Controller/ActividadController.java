package com.example.pucp.Controller;

import com.example.pucp.Entity.Actividad;
import com.example.pucp.Entity.Usuario;
import com.example.pucp.Repository.ActividadRepository;
import com.example.pucp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/actividad")
public class ActividadController {
    @Autowired
    ActividadRepository actividadRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String lista(Model model){
        model.addAttribute("listaActividades",actividadRepository.findAll());

        return "proyecto/actividades/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("listaActividades",actividadRepository.findAll());
        model.addAttribute("listaUsers",usuarioRepository.findAll());
        return "proyecto/actividad/nuevo";
    }

    @GetMapping("/editar")
    public String editar(Model model, @RequestParam("id") int id){
        Optional<Actividad> actividadOptional=actividadRepository.findById(id);
        if(actividadOptional.isPresent()){
            model.addAttribute("listaUsers",usuarioRepository.findAll());
            model.addAttribute("actividad",actividadOptional.get());

            return "proyecto/actividades/editar";
        }else{
            return "redirect:/actividad/lista";
        }


    }
    @PostMapping("guardar")
    public String guardar( Actividad actividad){
        actividadRepository.save(actividad);
        return "redirect:/actividad/lista";

    }

}
