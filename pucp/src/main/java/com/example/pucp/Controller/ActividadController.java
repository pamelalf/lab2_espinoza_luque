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

    @GetMapping("/listar")
    public String lista(Model model) {
        model.addAttribute("listaAc", actividadRepository.findAll());

        return "proyecto/actividades/lista";
    }

    @GetMapping("/agregar")
    public String nuevo(Model model) {
        model.addAttribute("listaActividades", actividadRepository.findAll());
        model.addAttribute("listaUsers", usuarioRepository.findAll());
        return "proyecto/actividades/nuevo";
    }

    @GetMapping("/editar")
    public String editar(Model model, @RequestParam("idact") int idact, @RequestParam("idpro") int idpro) {


        Optional<Actividad> optionalActividad = actividadRepository.findById(idact);
        if(optionalActividad.isPresent()){
            Actividad actividad = optionalActividad.get();
            model.addAttribute("actividad", actividad);
            return "proyecto/actividades/editar";
        }else{
            System.out.println(idpro);
            return "redirect:/proyecto/editar?id="+idpro;
        }
    }


    @PostMapping("/guardar")
    public String guardar(Actividad actividad, RedirectAttributes ra,@RequestParam("idact") int idact, @RequestParam("idpro") int idpro) {

        if(actividad.getIdactividad()==0){
            ra.addFlashAttribute("msg", "Actividad creada exitosamente");
        }else{
            ra.addFlashAttribute("msg", "Actividad actualizada exitosamente");
        }
        actividadRepository.save(actividad);
        return "redirect:/proyecto/editar?id="+idpro;

    }
    @GetMapping("/enviar")
    public String editar(Model model, @RequestParam("idpro") int idpro) {
            return "redirect:/proyecto/editar?id="+idpro;
    }
}
