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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/actividad")
public class ActividadController {
    @Autowired
    ActividadRepository actividadRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

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
            List<Usuario> usuarioList = usuarioRepository.findAll();
            System.out.println(actividad.getNombreactividad());
            model.addAttribute("listaUsuarios",usuarioList);
            model.addAttribute("actividad",actividadOptional.get().getIdactividad());
            return "proyecto/actividades/editar";

        }else{
            return "redirect:/proyecto/listar";
        }
    }


    @PostMapping("/guardar")
    public String guardar(Actividad actividad, RedirectAttributes ra){


        Optional<Actividad> actividadOptional= actividadRepository.findById(actividad.getIdactividad());
        if(actividadOptional.isPresent()){
            ra.addFlashAttribute("msgCreate","Actividad creada exitosamente");
        }else {
            ra.addFlashAttribute("msgEdit","a+Actividad actualizada exitosamente");
        }

        actividadRepository.save(actividad);
        return "redirect:/proyecto/listar";

    }

}
