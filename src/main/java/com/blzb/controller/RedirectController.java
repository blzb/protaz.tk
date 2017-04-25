package com.blzb.controller;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.dbo.UrlHit;
import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.data.repository.UrlHitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by apimentel on 4/23/17.
 */
@Controller
public class RedirectController {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    private UrlHitRepository urlHitRepository;

    @RequestMapping(value = "{shortId}")
    public String redirect(@PathVariable String shortId, HttpServletRequest servletRequest) {
        ShortUrl shortUrl;
        if (shortId.length() > 6) {
            shortUrl = shortUrlRepository.findByHashKey(shortId);
        } else {
            shortUrl = shortUrlRepository.findByStringId(shortId);
        }
        if (shortUrl != null) {
            UrlHit urlHit = new UrlHit();
            urlHit.setShortUrlId(shortUrl.getId());
            urlHit.setUserAgent(servletRequest.getHeader("User-Agent"));
            urlHit.setLanguage(servletRequest.getHeader("Accept-Language"));
            urlHit.setClientIp(getRemoteAddress(servletRequest));
            urlHitRepository.save(urlHit);
            return "redirect:" + shortUrl.getUrl();
        } else {
            return "notFound";
        }

    }

    public static String getRemoteAddress(HttpServletRequest req) {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress != null) {
            ipAddress = ipAddress.replaceFirst(",.*", "");  // cares only about the first IP if there is a list
        } else {
            ipAddress = req.getRemoteAddr();
        }
        return ipAddress;
    }
}
