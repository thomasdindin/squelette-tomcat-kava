package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.model.Cours;
import com.example.wrappers.CoursWrapper;

@Service
public class CoursService extends GenericService<Cours, CoursWrapper>{


    public List<Cours> getAll(){
        String sql = "SELECT * FROM Cours";
        return jdbcTemplate.query(sql, wrapper);
    }

    public Cours getByID(int id) {
        String sql = "SELECT * FROM Cours WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, wrapper, id);
    }

    public int insert(Cours cours){
        String sql = "INSERT INTO Cours(Date_Debut, Date_Fin, FK_UE, FK_Formateur) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, cours.getDateDebut(), cours.getDateFin(), cours.getUe().getId(), cours.getFormateur().getId());
    }

    public int update(Cours cours){
        String sql = "UPDATE Cours set Date_Debut =?, Date_Fin=?, FK_UE=?, FK_Formateur=? WHERE Id=?";
        return jdbcTemplate.update(sql, cours.getDateDebut(), cours.getDateFin(), cours.getUe().getId(), cours.getFormateur().getId(), cours.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM Cours WHERE Id= ?";
        return jdbcTemplate.update(sql, id);
    }
}
