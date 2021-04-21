package com.example.pucp.Controller;

import com.example.pucp.Entity.Proyecto;
import com.example.pucp.Entity.Usuario;
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

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProyectoRepository proyectoRepository;

    @GetMapping("/listar")
    public String listaRepo(Model model){

        model.addAttribute("listaP",proyectoRepository.findAll());
        return "proyecto/proyecto/lista";
    }
    @GetMapping("/agregar")
    public String agregarProyec(Model model){

        model.addAttribute("listaUser",usuarioRepository.findAll());
        model.addAttribute("listaP",proyectoRepository.findAll());
        return "proyecto/proyecto/nuevo";
    }


    @GetMapping("/editar")
    public String editarUser(Model model, @RequestParam("id") int id){
        Optional<Proyecto> proyectoOptional= proyectoRepository.findById(id);
        if(proyectoOptional.isPresent()){
            model.addAttribute("listaUsuarioa",usuarioRepository.findAll());
            model.addAttribute("proyecto",proyectoOptional.get());

            return "proyecto/proyecto/editar";
        }else{
            return "redirect:/proyecto/listar";
        }

    }

    @PostMapping("/guardar")
    public String guardarP(Proyecto proyecto, RedirectAttributes ra){
        Optional<Proyecto> proyectoOptional= proyectoRepository.findById(proyecto.getIdproyecto());
        if(proyectoOptional.isPresent()){
            ra.addFlashAttribute("msg","Proyecto creado exitosamente");
        }else {
            ra.addFlashAttribute("msg","Proyecto actualizado exitosamente");
        }
        proyectoRepository.save(proyecto);

        return "redirect:/usuario/listar";
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
