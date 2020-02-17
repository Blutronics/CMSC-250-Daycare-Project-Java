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
public class ParentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Parent findById(int id) {
        String sql = "SELECT * FROM parents WHERE iduser=?";
        RowMapper<Parent> rowMapper = new ParentRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int save(Parent p) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSQL = "INSERT INTO parents (name, phone, address, city, email, iduser) values (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, p.getName());
                    ps.setString(2, p.getPhone());
                    ps.setString(3, p.getAddress());
                    ps.setString(4, p.getCity());
                    ps.setString(5, p.getEmail());
                    ps.setInt(6, p.getUser());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public int update(Parent p) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String updatesql = "UPDATE parents SET name=?, phone=?, address=?, city=?, email=? where idparent=?";
        /** You had
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(updatesql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, p.getName());
                    ps.setString(2, p.getPhone());
                    ps.setString(3, p.getAddress());
                    ps.setString(4, p.getCity());
                    ps.setString(5, p.getEmail());
                    ps.setInt(6, p.getUser());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
        * This only makes sense for inserts, not for updates. I corrected this below. **/
        this.jdbcTemplate.update(updatesql,p.getName(),p.getPhone(),p.getAddress(),p.getCity(),p.getEmail(),p.getId());
        return p.getId();
    }
}
