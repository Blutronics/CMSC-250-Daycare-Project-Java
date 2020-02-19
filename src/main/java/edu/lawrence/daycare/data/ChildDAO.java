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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Khanh Toan
 */
@Repository
public class ChildDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Child findByParentId(int id) {
        String sql = "SELECT * FROM children WHERE parent=?";
        RowMapper<Child> rowMapper = new ChildRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int save(Child c) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSQL = "INSERT INTO children (parent, name, birthday) values (?, ?, ?)";
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(2, c.getName());
                    ps.setDate(3, convertJavaDateToSqlDate(c.getBirthday()));
                    ps.setInt(1, c.getParentID());
                    return ps;
                }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
        public int update(Child c) {
        String updatesql = "UPDATE children SET parent=?, name=?, birthday=? where id=?";
                this.jdbcTemplate.update(updatesql,c.getParentID(),c.getName(),convertJavaDateToSqlDate(c.getBirthday()),c.getId());
        return c.getId();
    }
}
