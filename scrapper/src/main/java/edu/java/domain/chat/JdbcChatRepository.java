package edu.java.domain.chat;

import edu.java.entity.Chat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcChatRepository implements ChatRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcChatRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Long id, int state) {
        String sql = "INSERT INTO chat (state, last_update_at) VALUES (state, CURRENT_TIMESTAMP)";
        jdbcTemplate.update(sql);
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM chat WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Collection<Chat> findAll() {
        String query = "SELECT * FROM chat";
        return jdbcTemplate.query(query, new ChatRowMapper());
    }

    @Override
    public Chat findById(Long id) {
        String sql = "SELECT * From chat WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, new ChatRowMapper());
    }

    private static class ChatRowMapper implements RowMapper<Chat> {
        @Override
        public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
            Chat chat = new Chat();
            chat.setId(rs.getLong("id"));
            chat.setState(rs.getInt("state"));
            chat.setLastUpdateAt(rs.getObject("updateAt", OffsetDateTime.class));
            return chat;
        }
    }
}
