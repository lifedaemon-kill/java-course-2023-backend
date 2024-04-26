package edu.java.domain.jdbc;

import edu.java.domain.interfaces.ChatRepository;
import edu.java.entity.Chat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class JdbcChatRepository implements ChatRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcChatRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Long id) {
        String sql = "INSERT INTO chat (tg_chat_id, state, last_update_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, 0, OffsetDateTime.now());
    }

    @Override
    public void update(Long id, int state) {
        String sql = "UPDATE chat SET state = ?, last_update_at = ? WHERE tg_chat_id = ?";
        jdbcTemplate.update(sql, state, OffsetDateTime.now(), id);
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM chat WHERE tg_chat_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Collection<Chat> findAll() {
        String query = "SELECT * FROM chat";
        return jdbcTemplate.query(query, new ChatRowMapper());
    }

    @Override
    public Chat findById(Long id) {
        String sql = "SELECT * From chat WHERE tg_chat_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] {id}, new ChatRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class ChatRowMapper implements RowMapper<Chat> {
        @Override
        public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
            Chat chat = new Chat();
            chat.setId(rs.getLong("tg_chat_id"));
            chat.setState(rs.getInt("state"));
            chat.setLastUpdateAt(rs.getObject("last_update_at", OffsetDateTime.class));
            return chat;
        }
    }
}
