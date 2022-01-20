package com.example.account.controller;

import com.example.account.facades.*;
import com.example.account.model.Compte;
import com.example.account.service.CompteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/comptes")
public class CompteController implements Save<Compte>, Delete<String, Compte>, FindAll<List<Compte>>, Update<Compte>, FindById<Compte, String> {
    final CompteService compteService;

    @DeleteMapping("/{id}")
    @Override
    public Compte delete(@PathVariable String id) {
        return compteService.delete(id);
    }

    @GetMapping("/")
    @Override
    public List<Compte> findAll() {
        return compteService.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Compte findById(@PathVariable String id) {
        return compteService.findById(id);
    }

    @PostMapping("/")
    @Override
    public Compte save(@RequestBody Compte compte) {
        return compteService.save(compte);
    }

    @PutMapping("/")
    @Override
    public Compte update(@RequestBody Compte compte) {
        return compteService.update(compte);
    }

    @GetMapping("/saveDefaultAccount/")
    public Compte saveDefaultAccount() {
        return compteService.saveDefaultAccount();
    }

    @PutMapping("/{rib}")
    public Compte updateByRib(@PathVariable String rib,@RequestBody Double solde) {
        return compteService.updateByRib(rib,solde);
    }
}
