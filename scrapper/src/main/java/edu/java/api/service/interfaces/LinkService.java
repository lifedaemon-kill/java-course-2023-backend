package edu.java.api.service.interfaces;

import dto.request.AddLinkRequest;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import java.net.URI;

public interface LinkService {
    LinkResponse addLinkTracking(Long id, AddLinkRequest request);

    LinkResponse deleteLinkTracking(Long id, URI url);

    ListLinksResponse getLinks(Long id);

}
