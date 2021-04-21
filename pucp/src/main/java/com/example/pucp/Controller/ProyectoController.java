package com.example.pucp.Controller;

import com.example.pucp.Entity.Actividad;
import com.example.pucp.Entity.Proyecto;
import com.example.pucp.Entity.Usuario;
import com.example.pucp.Repository.ActividadRepository;
import com.example.pucp.Repository.AreaRepository;
import com.example.pucp.Repository.ProyectoRepository;
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

import static java.lang.Math.round;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    ActividadRepository actividadRepository;

    @GetMapping("/listar")
    public String listaRepo(Model model) {

        model.addAttribute("listaP", proyectoRepository.findAll());
        return "proyecto/proyecto/lista";
    }

    @GetMapping("/agregar")
    public String agregarProyec(Model model) {

        model.addAttribute("listaUser", usuarioRepository.findAll());
        model.addAttribute("listaP", proyectoRepository.findAll());
        return "proyecto/proyecto/nuevo";
    }


    @GetMapping("/editar")
    public String editarUser(Model model, @RequestParam("id") int id) {
        double porcentaje = 0;
        double suma = 0;
        double suma_if = 0;
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(id);
        if (proyectoOptional.isPresent()) {
            List<Actividad> listaAct = actividadRepository.findporidproyectoq(id);
            model.addAttribute("listaUsuarioa", usuarioRepository.findAll());
            model.addAttribute("listaAct", listaAct);
            model.addAttribute("proyecto", proyectoOptional.get());
            for (Actividad actividad : listaAct) {
                suma = (suma + actividad.getPeso());
                if (actividad.isEstado()) {
                    suma_if = suma_if + actividad.getPeso();

                }
            }
            porcentaje = (suma_if / suma) * 100;
            System.out.println(porcentaje);
            model.addAttribute("porcentaje", porcentaje);
            return "proyecto/proyecto/editar";
        } else {
            return "redirect:/proyecto/listar";
        }

    }

    @PostMapping("/guardar")
    public String guardarP(Proyecto proyecto, RedirectAttributes ra) {
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(proyecto.getIdproyecto());
        if (proyectoOptional.isPresent()) {
            ra.addFlashAttribute("msg", "Proyecto creado exitosamente");
        } else {
            ra.addFlashAttribute("msg", "Proyecto actualizado exitosamente");
        }
        proyectoRepository.save(proyecto);

        return "redirect:/proyecto/listar";
    }

    @GetMapping("/borrar")
    public String borrar(Model model, @RequestParam("id") int id,
                         RedirectAttributes attr) {

        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(id);

        if (proyectoOptional.isPresent()) {
            proyectoRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Area eliminada exitosamente");
        }
        return "redirect:/area/listar";

    }


}
