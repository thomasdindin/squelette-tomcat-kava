package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.UE;
import com.example.wrappers.UEWrapper;

@Service
public class UEService  extends GenericService<UE, UEWrapper>{

    public List<UE> getAll(){
        String sql = "SELECT * FROM UE";
        return jdbcTemplate.query(sql, wrapper);
    }

    public UE getByID(int id){
        String sql = "SELECT * FROM UE WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, wrapper, id);
    }

    public int insert(UE ue){
        String sql = "INSERT INTO UE(Libelle) VALUES (?)";
        return jdbcTemplate.update(sql, ue.getLibelle());
    }

    public int update(UE ue){
        String sql = "UPDATE UE SET Libelle=? WHERE Id=?";
        return jdbcTemplate.update(sql, ue.getLibelle(), ue.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM UE WHERE Id=?";
        return jdbcTemplate.update(sql, id);
    }
}
