package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Formateur;
import com.example.services.FormateurService;

@RestController
@RequestMapping("/api/formateurs")
public class FormateurController extends GenericController<Formateur, FormateurService> {

    @GetMapping
    public ResponseEntity<String> getAll() {
        List<Formateur> formateurList = service.getAll();
        return getJsonData(formateurList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Formateur formateur = service.getByID(id);
        return getJsonData(formateur, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Formateur formateur) {
        service.insert(formateur);
        return getJsonData("Formateur ajouté", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Formateur formateur) {
        Formateur existingEntity = service.getByID(id);
        if( existingEntity == null) {
            return getJsonData("Formateur non trouvé", HttpStatus.NOT_FOUND);
        } else {
            if(formateur.getNom() == null) {
                formateur.setNom(existingEntity.getNom());
            }
            if(formateur.getPrenom() == null) {
                formateur.setPrenom(existingEntity.getPrenom());
            }
            if(formateur.getTelephone() == null) {
                formateur.setTelephone(existingEntity.getTelephone());
            }
            if(formateur.getEmail() == null) {
                formateur.setEmail(existingEntity.getEmail());
            }
        }
        formateur.setId(id);
        service.update(formateur);
        return getJsonData("Formateur modifié", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        service.delete(id);
        return getJsonData("Formateur supprimé", HttpStatus.OK);
    }
}