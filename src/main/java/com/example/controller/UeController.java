package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.UE;
import com.example.services.UEService;

@RestController
@RequestMapping("/api/ue")
public class UeController extends GenericController<UE, UEService> {

    @GetMapping
    public ResponseEntity<String> getAll() {
        List<UE> ueList = service.getAll();
        return getJsonData(ueList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        UE ue = service.getByID(id);
        return getJsonData(ue, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody UE ue) {
        service.insert(ue);
        return getJsonData("UE ajouté", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody UE ue) {
        UE existingEntity = service.getByID(id);
        if( existingEntity == null) {
            return getJsonData("UE non trouvé", HttpStatus.NOT_FOUND);
        } else {
            if(ue.getLibelle() == null) {
                ue.setLibelle(existingEntity.getLibelle());
            }
        }
        ue.setId(id);
        service.update(ue);
        return getJsonData("UE modifié", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        service.delete(id);
        return getJsonData("UE supprimé", HttpStatus.OK);
    }
}