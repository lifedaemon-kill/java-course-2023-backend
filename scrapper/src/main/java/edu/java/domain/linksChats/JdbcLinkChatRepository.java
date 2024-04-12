package edu.java.domain.linksChats;

import edu.java.entity.LinkChat;
import java.net.URI;
import java.util.Collection;
import javax.sql.DataSource;
import model.LinkUpdate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@SuppressWarnings("MultipleStringLiterals")
@Repository
public class JdbcLinkChatRepository implements LinkChatRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcLinkChatRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Long urlId, Long chatId) {
        String sql = "INSERT INTO LinksChats (url_id, tg_chat_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, urlId, chatId);
    }

    @Override
    public void remove(Long urlId, Long chatId) {
        String sql = "DELETE FROM LinksChats WHERE (url_id = ? AND tg_chat_id = ?)";
        jdbcTemplate.update(sql, urlId, chatId);
    }

    @Override
    public void remove(Long chatId) {
        String sql = "DELETE FROM LinksChats WHERE tg_chat_id = ?";
        jdbcTemplate.update(sql, chatId);
    }

    @Override
    public Collection<URI> findAll() {
        String sql = "SELECT l.url FROM link l "
                     + "JOIN linkschats r ON l.id = r.url_id ";
        RowMapper<URI> rowMapper = (rs, rowNum) -> URI.create(rs.getString("url"));
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<URI> findAllById(Long chatId) {
        String sql = "SELECT l.url FROM link l "
                     + "JOIN linkschats r ON l.id = r.url_id "
                     + "WHERE tg_chat_id = ?";
        RowMapper<URI> rowMapper = (rs, rowNum) -> URI.create(rs.getString("url"));
        Object[] params = {chatId};
        try {
            return jdbcTemplate.query(sql, params, rowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<Long> findAllByUrlId(Long urlId) {
        String sql = "SELECT l.tg_chat_id FROM linkschats l WHERE l.url_id = ?";

        return jdbcTemplate.query(sql, new Object[]{urlId}, (rs, rowNum) -> rs.getLong("tg_chat_id"));
    }

    @Override
    public LinkChat findOne(Long url, Long id) {
        String sql = "SELECT * FROM LinksChats WHERE (tg_chat_id = ? AND url_id = ?)";
        Object[] params = {id, url};

        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            LinkChat linkChat = new LinkChat();
            linkChat.setId(rs.getLong("id"));
            linkChat.setUrlId(rs.getLong("url_id"));
            linkChat.setTgChatId(rs.getLong("tg_chat_id"));
            return linkChat;
        });
    }
}
