package com.blzb.controller;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.mapping.ResourceType;
import org.springframework.data.rest.webmvc.*;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by apimentel on 4/23/17.
 */
@RepositoryRestController
public class ShortUrlController {

    @Autowired
    private HttpHeadersPreparer headersPreparer;

    @Autowired
    private ShortUrlService shortUrlService;

    @ResponseBody
    @RequestMapping(value = "/shortUrls", method = RequestMethod.POST)
    public ResponseEntity<ResourceSupport> postCollectionResource(
            @RequestBody ShortUrl shortUrl, PersistentEntityResourceAssembler assembler
    )
            throws HttpRequestMethodNotSupportedException {
        shortUrl = shortUrlService.saveAndCalculate(shortUrl);
        PersistentEntityResource resource = assembler.toFullResource(shortUrl);
        HttpHeaders headers = headersPreparer.prepareHeaders(resource);
        addLocationHeader(headers, assembler, shortUrl);
        return ControllerUtils.toResponseEntity(HttpStatus.CREATED, headers, resource);
    }

    private void addLocationHeader(HttpHeaders headers, PersistentEntityResourceAssembler assembler, Object source) {
        String selfLink = assembler.getSelfLinkFor(source).getHref();
        headers.setLocation(new UriTemplate(selfLink).expand());
    }

}
