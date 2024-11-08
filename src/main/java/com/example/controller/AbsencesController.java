package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Absences;
import com.example.services.AbsenceService;

@RestController
@RequestMapping("/api/absences")
public class AbsencesController extends GenericController<Absences, AbsenceService> {

    @GetMapping
    public ResponseEntity<String> getAll() {
        List<Absences> absenceList = service.getAll();
        return getJsonData(absenceList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Absences absence = service.getByID(id);
        return getJsonData(absence, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Absences absence) {
        service.insert(absence);
        return getJsonData("Absence ajoutée", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Absences absence) {
        Absences existingEntity = service.getByID(id);
        if( existingEntity == null) {
            return getJsonData("Absence non trouvée", HttpStatus.NOT_FOUND);
        } else {
            if(absence.getDateDebut() == null) {
                absence.setDateDebut(existingEntity.getDateDebut());
            }
            if(absence.getDateFin() == null) {
                absence.setDateFin(existingEntity.getDateFin());
            }
            if(absence.getEtudiants() == null) {
                absence.setEtudiants(existingEntity.getEtudiants());
            }
            if (absence.getType() == null) {
                absence.setType(existingEntity.getType());
                
            }
        }
        absence.setId(id);
        service.update(absence);
        return getJsonData("Absence modifiée", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        service.delete(id);
        return getJsonData("Absence supprimée", HttpStatus.OK);
    }
}