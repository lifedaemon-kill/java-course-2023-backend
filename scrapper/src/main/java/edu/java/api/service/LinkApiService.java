package edu.java.api.service;

import api.exception.LinkAlreadyAddedException;
import api.exception.NotFoundException;
import dto.request.AddLinkRequest;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import edu.java.entity.Link;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class LinkApiService extends AbstractApiService {
    //Link
    public LinkResponse addLinkTracking(Long id, AddLinkRequest request) {
        URI link = request.link();
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }

        try {
            linkRepository.add(link);
        } catch (DuplicateKeyException ignored) {
        }
        Link linkEntity = linkRepository.findByUrl(link);
        try {
            relationRepository.add(linkEntity.getId(), id);
        } catch (DuplicateKeyException e) {
            throw new LinkAlreadyAddedException();
        }

        return new LinkResponse(id, request.link());
    }

    public LinkResponse deleteLinkTracking(Long id, URI url) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }
        Link linkEntity = linkRepository.findByUrl(url);
        if (linkEntity == null) {
            throw new NotFoundException();
        }

        if (relationRepository.remove(linkEntity.getId(), id) == 0) {
            throw new NotFoundException();
        }
        return new LinkResponse(id, url);
    }

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
