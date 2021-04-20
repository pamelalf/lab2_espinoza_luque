package com.example.pucp.Controller;

import com.example.pucp.Entity.Actividad;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = {"/lista", ""})
    public String lista(Model model) {

        List<Area> lista = areaRepository.findAll();
        model.addAttribute("arealista", lista);

        return "proyecto/area/lista";
    }


    @GetMapping("/nuevo")
    public String nuevo() {

        return "proyecto/area/nuevo";
    }

    @GetMapping("/editar")
    public String editar(Model model, @RequestParam("id") int id) {
        Optional<Area> areaOptional = areaRepository.findById(id);
        if (areaOptional.isPresent()) {
            List<Usuario> listau = usuarioRepository.findByIdarea(id);
            model.addAttribute("listausuarios",listau);
            Area area = areaOptional.get();
            model.addAttribute("area", area);
            return "proyecto/area/editar";
        } else {
            return "redirect:proyecto/area/lista";
        }
    }

    @PostMapping("guardar")
    public String guardar(Area area, RedirectAttributes attr) {
        if (area.getIdArea() == 0) {
            attr.addFlashAttribute("msg", "Usuario creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Usuario actualizado exitosamente");
        }
        areaRepository.save(area);
        return "redirect:/area/lista";

    }

    @GetMapping("/borrar")
    public String borrar(Model model, @RequestParam("id") int id,
                         RedirectAttributes attr) {

        Optional<Area> areaOptional = areaRepository.findById(id);

        if (areaOptional.isPresent()) {
            areaRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Area eliminada exitosamente");
        }
        return "redirect:/area/lista";

    }
}
