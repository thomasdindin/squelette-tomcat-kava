package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.model.UE;

@Component
public class UEWrapper implements RowMapper<UE> {

    @Override
    public UE mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new UE(rs.getInt("Id"), rs.getString("Libelle"));
    }
}
