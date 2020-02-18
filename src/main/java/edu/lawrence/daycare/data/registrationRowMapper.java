/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.daycare.data;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Khanh Toan
 */
public class registrationRowMapper implements RowMapper<registration> {
    @Override
    public registration mapRow(ResultSet row, int rowNum) throws SQLException {
        registration r = new registration();
        r.setId(row.getInt("id"));
        r.setChildID(row.getInt("child"));
        r.setProviderID(row.getInt("provider"));
        r.setStart(row.getDate("start"));
        r.setEnd(row.getDate("end"));
        r.setStatus(row.getInt("status"));
        return r;
    }
}
