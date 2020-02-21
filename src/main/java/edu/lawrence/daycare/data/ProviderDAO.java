package edu.lawrence.daycare.data;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Date;

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

    public List<Provider> findByDate(Date start, Date end,int id) {
        RowMapper<Provider> rowMapper = new ProviderRowMapper();
        String sql1 = "set @start = ?";
        jdbcTemplate.update(sql1, start);
        String sql2 = "set @birth = (select birthday from children where id = ?)";
        String sql3 = "set @age = timestampdiff(MONTH,@birth,@start)";
        String sql4 = "create table tmp select provider, count(provider) as c from registration where start <= @start and end >= @start group by provider";
        String sql5 = "create table enrolled select id, name, capacity, minAge, maxAge, ifnull(c,0) as e from providers left join tmp on providers.id = tmp.provider";
        String sql6 = "drop table tmp";
        String sql7 = "select * from enrolled where e < capacity and minAge <= @age;\n";
        List<Provider> result = jdbcTemplate.query(sql7, rowMapper);
        String sql8 = "drop table enrolled";
        return result;
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
