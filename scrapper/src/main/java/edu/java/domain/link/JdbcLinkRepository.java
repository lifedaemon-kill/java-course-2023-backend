package edu.java.domain.link;

import dto.response.LinkResponse;
import java.net.URI;
import java.util.Collection;
import javax.sql.DataSource;
import edu.java.domain.link.LinkRepository;
import edu.java.entity.Link;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcLinkRepository implements LinkRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcLinkRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public void add(URI link) {
        String sql = "INSERT INTO link (url, answers_count, last_update_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
        jdbcTemplate.update(sql, link, 0);
    }

    @Transactional
    @Override
    public void remove(URI link) {
        String sql = "DELETE FROM link WHERE url = ?";
        jdbcTemplate.update(sql, link);
    }

    @Transactional
    @Override
    public Collection<Link> findAll(String condition) {
        String sql = "SELECT * FROM link WHERE " + condition + ";"; //с днем sql injection
//        return jdbcTemplate.query(sql, new Object[]{}, (rs, rowNum)->{
//            new Link();
//        });
        return null;
    }
}
