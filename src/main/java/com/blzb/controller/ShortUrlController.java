package com.blzb.controller;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.data.vo.CustomerShortUrlVo;
import com.blzb.data.vo.ShortUrlVo;
import com.blzb.data.vo.TotalsVo;
import com.blzb.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import javax.servlet.http.HttpServletRequest;

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
    public ShortUrlVo postCollectionResource(
            @RequestBody CustomerShortUrlVo shortUrl, PersistentEntityResourceAssembler assembler, HttpServletRequest request
    )
            throws HttpRequestMethodNotSupportedException {


        return shortUrlService.saveAndCalculate(shortUrl, getHost(request));
    }

    private String getHost(HttpServletRequest request) {
        StringBuilder host = new StringBuilder(request.getScheme()).append("://").append(request.getServerName());
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            host.append(":").append(request.getServerPort());
        }
        return host.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/shortUrls/total", method = RequestMethod.GET)
    public TotalsVo total() {
        return shortUrlService.getTotal();
    }


    @RequestMapping(value = "/shortUrls", method = RequestMethod.GET)
    @ResponseBody
    Page<ShortUrlVo> list(Pageable pageable, HttpServletRequest request) {
        return shortUrlService.listAllByPage(pageable, getHost(request));
    }
}
