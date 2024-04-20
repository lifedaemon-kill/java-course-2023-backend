package edu.java.api.service.jpa;

import api.exception.DataBaseNoConnectedException;
import api.exception.LinkAlreadyAddedException;
import api.exception.NotFoundException;
import dto.request.AddLinkRequest;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import edu.java.api.service.interfaces.LinkService;
import edu.java.domain.jpa.JpaLinkChatRepository;
import edu.java.domain.jpa.JpaLinkRepository;
import edu.java.entity.Link;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JpaLinkService extends JpaUtilityService implements LinkService {
    @Autowired
    protected JpaLinkRepository linkRepository;
    @Autowired
    protected JpaLinkChatRepository relationRepository;

    @Override
    public LinkResponse addLinkTracking(Long id, AddLinkRequest request) {
        URI link = request.link();
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }

        try {
            linkRepository.add(link);
        } catch (DataAccessException ignored) {
            log.info("duplicated url");
        }

        Link linkEntity;
        try {
            linkEntity = linkRepository.findByUrl(link);
        } catch (DataAccessException e) {
            log.error("Не нашлось по url");
            throw new DataBaseNoConnectedException();
        }

        try {
            log.info(linkEntity.getId() + " " + linkEntity.getUrl() + "   " + id);
            relationRepository.findOne(linkEntity.getId(), id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Не нашел в relation findone");
            try {
                relationRepository.add(linkEntity.getId(), id);
            } catch (DataAccessException ignored) {
                log.error("duplicated relation");
                throw new LinkAlreadyAddedException();
            }
        }

        return new LinkResponse(id, request.link());
    }

    @Override
    public LinkResponse deleteLinkTracking(Long id, URI url) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }

        Link linkEntity = linkRepository.findByUrl(url);
        if (linkEntity == null) {
            throw new NotFoundException();
        }

        if (relationRepository.findOne(linkEntity.getId(), id) == null) {
            throw new NotFoundException();
        }

        try {
            relationRepository.remove(linkEntity.getId(), id);

        } catch (Exception e) {

            throw new NotFoundException();
        }

        return new LinkResponse(id, url);
    }

    @Override
    public ListLinksResponse getLinks(Long id) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }

        List<URI> links = relationRepository.findAllById(id).stream().toList();
        List<LinkResponse> linkResponses = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            linkResponses.add(new LinkResponse(Integer.toUnsignedLong(i), links.get(i)));
        }
        return new ListLinksResponse(linkResponses, linkResponses.size());
    }
}
