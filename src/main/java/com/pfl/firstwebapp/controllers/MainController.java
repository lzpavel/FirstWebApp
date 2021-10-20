package com.pfl.firstwebapp.controllers;

import com.pfl.firstwebapp.models.FwaTable;
import com.pfl.firstwebapp.repo.FwaTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private FwaTableRepository fwaTableRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("active_tab", 1);
        return "home";
    }

    @GetMapping("/database")
    public String database(Model model) {
        model.addAttribute("active_tab", 2);
        Iterable<FwaTable> fwaTableItems = fwaTableRepository.findAll();
        model.addAttribute("fwaTableItems", fwaTableItems);
        return "database";
    }

    @PostMapping("/database/add")
    public String databaseAdd(@RequestParam String data, Model model){
        FwaTable fwaTable = new FwaTable(data);
        fwaTableRepository.save(fwaTable);
        return "redirect:/database";
    }

    @GetMapping("/database/delete/{id}")
    public String databaseDelete(@PathVariable(value = "id") long id, Model model){
        if(fwaTableRepository.existsById(id)){
            FwaTable fwaTable = fwaTableRepository.findById(id).orElseThrow();
            fwaTableRepository.delete(fwaTable);
        }
        return "redirect:/database";
    }

    @GetMapping("/database/edit/{id}")
    public String databaseEdit(@PathVariable(value = "id") long id, Model model){
        if(fwaTableRepository.existsById(id)){
            Iterable<FwaTable> fwaTableItems = fwaTableRepository.findAll();
            model.addAttribute("fwaTableItems", fwaTableItems);
            model.addAttribute("editId", id);
        }
        return "database";
    }

    @PostMapping("/database/edit/{id}")
    public String databaseEditApply(@PathVariable(value = "id") long id, @RequestParam String data, Model model){
        if(fwaTableRepository.existsById(id)){
            //Optional<FwaTable> fwaElement = fwaTableRepository.findById(id);
            FwaTable fwaTable = fwaTableRepository.findById(id).orElseThrow();
            fwaTable.setData(data);
            fwaTableRepository.save(fwaTable);
        }

        //FwaTable fwaTable = new FwaTable(data);
        //fwaTableRepository.save(fwaTable);
        return "redirect:/database";
    }



}