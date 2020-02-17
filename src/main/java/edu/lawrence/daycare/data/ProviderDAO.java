package edu.lawrence.daycare.data;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProviderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Provider> findAll() {
        String sql = "SELECT * FROM providers";
        RowMapper<Provider> rowMapper = new ProviderRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Provider> findByLocation(double lat, double lgt) {
        String sql = "SELECT id, name, address, city, capacity, minAge, maxAge, gc_dist(?,?,lat,lgt) AS dist FROM providers ORDER BY dist";
        RowMapper<Provider> rowMapper = new ProviderRowMapper();
        return jdbcTemplate.query(sql, rowMapper, lat, lgt);
    }

    public Provider findById(int id) {
        String sql = "SELECT id, , name, address, city, capacity, minAge, maxAge FROM providers WHERE id=?";
        RowMapper<Provider> rowMapper = new ProviderRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(Provider p) {
        String sql = "INSERT INTO providers (name, address, city, capacity, minAge, maxAge) values (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, p.getName(), p.getAddress(), p.getCity(), p.getCapacity(), p.getMinAge(), p.getMaxAge());
    }

    public void remove(int id) {
        String sql = "DELETE FROM providers WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
