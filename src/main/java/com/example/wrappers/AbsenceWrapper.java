package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.model.Absences;
import com.example.services.EtudiantService;

@Component
public class AbsenceWrapper implements RowMapper<Absences> {
    @Autowired
    private EtudiantService etudiantService;

    @Override
    public Absences mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Absences(rs.getInt("id"),
         rs.getDate("Date_Debut"), 
         rs.getDate("Date_Fin"), 
         rs.getString("Type"),
         this.etudiantService.getByID(rs.getInt("FK_Etudiant"))
         );
    }
    
}
