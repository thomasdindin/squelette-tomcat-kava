package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.model.Etudiant;

@Component
public class EtudiantWrapper implements RowMapper<Etudiant> {

    @Override
    public Etudiant mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Etudiant(
            rs.getInt("Id"), 
            rs.getString("Nom"), 
            rs.getString("Prenom"), 
            rs.getString("Email"), 
            rs.getString("Telephone")
        );
    }
    
}
