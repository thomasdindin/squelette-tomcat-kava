package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Cours;
import com.example.services.CoursService;

@RestController
@RequestMapping("/api/cours")
public class CoursController extends GenericController<Cours, CoursService> {

    @GetMapping
    public ResponseEntity<String> getAll() {
        List<Cours> coursList = service.getAll();
        return getJsonData(coursList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Cours cours = service.getByID(id);
        return getJsonData(cours, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Cours cours) {
        service.insert(cours);
        return getJsonData("Cours ajouté", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Cours cours) {
        Cours existingEntity = service.getByID(id);
        if( existingEntity == null) {
            return getJsonData("Cours non trouvé", HttpStatus.NOT_FOUND);
        } else {
            if(cours.getUe() == null) {
                cours.setUe(existingEntity.getUe());
            }
            if(cours.getFormateur() == null) {
                cours.setFormateur(existingEntity.getFormateur());
            }
            if(cours.getDateDebut() == null) {
                cours.setDateDebut(existingEntity.getDateDebut());
            }
            if(cours.getDateFin() == null) {
                cours.setDateFin(existingEntity.getDateFin());
            }
            if(cours.getEtudiants()==null) {
                cours.setEtudiants(existingEntity.getEtudiants());
            }
        }
        cours.setId(id);
        service.update(cours);
        return getJsonData("Cours modifié", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        service.delete(id);
        return getJsonData("Cours supprimé", HttpStatus.OK);
    }
}