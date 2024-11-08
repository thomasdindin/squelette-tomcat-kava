package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Formateur;
import com.example.wrappers.FormateurWrapper;

@Service
public class FormateurService extends GenericService<Formateur, FormateurWrapper>{


    public List<Formateur> getAll(){
        String sql = "SELECT * FROM Formateurs";
        return jdbcTemplate.query(sql, wrapper);
    }

    public Formateur getByID(int id) {
        String sql = "SELECT * FROM Formateurs WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, wrapper, id);
    }

       public int insert(Formateur formateur){
     String sql = "INSERT INTO Formateurs(Nom,Prenom,Email,Telephone) VALUES (?,?,?,?)";
     return jdbcTemplate.update(sql, formateur.getNom(), formateur.getPrenom(), formateur.getEmail(), formateur.getTelephone());
   }

   public int update(Formateur formateur){
     String sql = "UPDATE Formateurs set Nom=?,Prenom=?,Email=?,Telephone=? WHERE Id=?";
     return jdbcTemplate.update(sql, formateur.getNom(), formateur.getPrenom(), formateur.getEmail(), formateur.getTelephone(), formateur.getId());
   }

   public int delete(int id){
     String sql= "DELETE FROM Formateurs WHERE Id=?";
     return jdbcTemplate.update(sql,id);
   }
}
