/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.daycare.data;

import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Khanh Toan
 */
@Repository
public class registrationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(registration r) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSQL = "INSERT INTO registration (child, provider, start, end, status) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, r.getChildID());
                    ps.setInt(2, r.getParentID());
                    ps.setDate(3,convertJavaDateToSqlDate(r.getStart()));
                    ps.setDate(4,convertJavaDateToSqlDate(r.getEnd()));
                    ps.setInt(5, r.getStatus());
                    return ps;
                }, keyHolder);
        return keyHolder.getKey().intValue();
    }
    

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
