package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Etudiant;
import com.example.services.EtudiantService;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController extends GenericController<Etudiant, EtudiantService> {

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Etudiant etudiant) {
        service.insert(etudiant);
        return getJsonData("Etudiant ajouté", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<String> getAll() {
        List<Etudiant> coursList = service.getAll();
        return getJsonData(coursList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Etudiant etudiant = service.getByID(id);
        return getJsonData(etudiant, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Etudiant etudiant) {
        // On récupère l'entité existante :
        Etudiant existingEntity = service.getByID(id);
        if( existingEntity == null) {
            return getJsonData("Etudiant non trouvé", HttpStatus.NOT_FOUND);
        } else {
            // On met à jour les champs de l'entité existante avec les nouvelles valeurs :
            if(etudiant.getNom() == null) {
                etudiant.setNom(existingEntity.getNom());
            }
            if(etudiant.getPrenom() == null) {
                etudiant.setPrenom(existingEntity.getPrenom());
            }
            if(etudiant.getTelephone() == null) {
                etudiant.setTelephone(existingEntity.getTelephone());
            }
            if(etudiant.getEmail() == null) {
                etudiant.setEmail(existingEntity.getEmail());
            }
        }
        
        // On met à jour l'entité existante :
        etudiant.setId(id);
        service.update(etudiant);
        return getJsonData("Etudiant modifié", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        service.delete(id);
        return getJsonData("Etudiant supprimé", HttpStatus.OK);
    }
}
