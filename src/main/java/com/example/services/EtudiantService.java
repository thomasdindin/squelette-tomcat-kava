package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Etudiant;
import com.example.wrappers.EtudiantWrapper;

@Service
public class EtudiantService extends GenericService<Etudiant, EtudiantWrapper>{

   public List<Etudiant> getAll(){
        String sql = "SELECT * FROM Etudiants;";
        return jdbcTemplate.query(sql, wrapper);
   } 

   public Etudiant getByID(int id){
        String sql = "SELECT * FROM Etudiants WHERE Id= ?";
        return jdbcTemplate.queryForObject(sql, wrapper,id);
   }

   public List<Etudiant> getByCourID(int id){
        String sql = "SELECT ET.* FROM Etudiants ET INNER JOIN SUIVRE SU ON SU.FK_Etudiant = ET.Id WHERE FK_Cour=?;";
        return jdbcTemplate.query(sql, new EtudiantWrapper(),id);
   }

   public int insert(Etudiant etudiant){
     String sql = "INSERT INTO Etudiants(Nom,Prenom,Email,Telephone) VALUES (?,?,?,?)";
     return jdbcTemplate.update(sql, etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail(), etudiant.getTelephone());
   }

   public int update(Etudiant etudiant){
     String sql = "UPDATE Etudiants set Nom=?,Prenom=?,Email=?,Telephone=? WHERE Id=?";
     return jdbcTemplate.update(sql, etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail(), etudiant.getTelephone(), etudiant.getId());
   }

   public int delete(int id){
     String sql= "DELETE FROM Etudiants WHERE Id=?";
     return jdbcTemplate.update(sql,id);
   }
   
}
