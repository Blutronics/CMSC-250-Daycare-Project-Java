/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.daycare.data;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Khanh Toan
 */
public class ChildRowMapper implements RowMapper<Child> {
    @Override
    public Child mapRow(ResultSet row, int rowNum) throws SQLException {
        Child c = new Child();
        c.setId(row.getInt("id"));
        c.setName(row.getString("name"));
        c.setParentID(row.getInt("parent"));
        c.setBirthday(row.getDate("birthday"));
        return c;
    }
}
