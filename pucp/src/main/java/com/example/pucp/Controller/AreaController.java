package com.example.pucp.Controller;

import com.example.pucp.Entity.Area;
import com.example.pucp.Repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @GetMapping(value = {"/lista", ""})
    public String listarAreas(Model model) {

        List<Area> lista = areaRepository.findAll();
        model.addAttribute("arealista", lista);

        return "area/lista";
    }
}
