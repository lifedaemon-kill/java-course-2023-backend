package edu.java.domain.link;

import edu.java.entity.Link;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("MultipleStringLiterals")
@Repository
public class JdbcLinkRepository implements LinkRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcLinkRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(URI link) {
        String sql = "INSERT INTO Link (url, answers_count, last_update_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, link.toString(), 0, OffsetDateTime.now());
    }

    @Override
    public void remove(URI link) {
        String sql = "DELETE FROM Link WHERE url = ?";
        jdbcTemplate.update(sql, link);
    }

    @Override
    public Collection<Link> findAll() {
        return findByThreshold(OffsetDateTime.now());
    }

    @Override
    public Collection<Link> findByThreshold(OffsetDateTime thresholdTime) {
        String sql = "SELECT * FROM Link WHERE last_update_at < ?";
        Object[] params = {thresholdTime};

        try {
            return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
                Link link = new Link();
                link.setId(rs.getLong("id"));
                try {
                    link.setUrl(new URI(rs.getString("url")));
                } catch (URISyntaxException e) {
                    throw new RuntimeException();
                }
                link.setAnswersCount(rs.getInt("answers_count"));
                link.setLastUpdateAt(rs.getObject("last_update_at", OffsetDateTime.class));
                return link;
            });
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Link findByUrl(URI url) {
        String query = "SELECT * FROM Link WHERE url = ?";

        return jdbcTemplate.queryForObject(query, new Object[] {url.toString()}, (rs, rowNum) -> {
            Link link = new Link();
            link.setId(rs.getLong("id"));
            try {
                link.setUrl(new URI(rs.getString("url")));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            link.setAnswersCount(rs.getInt("answers_count"));
            link.setLastUpdateAt(rs.getObject("last_update_at", OffsetDateTime.class));

            return link;
        });
    }

}
