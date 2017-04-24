package com.blzb.controller;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by apimentel on 4/23/17.
 */
@Controller
public class RedirectController {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @RequestMapping(value = "{shortId}")
    public String redirect(@PathVariable String shortId) {
        ShortUrl shortUrl;
        if (shortId.length() > 6) {
            shortUrl = shortUrlRepository.findByHashKey(shortId);
        } else {
            shortUrl = shortUrlRepository.findByStringId(shortId);
        }
        if (shortUrl != null) {
            return "redirect:"+shortUrl.getUrl();
        } else {
            return "notFound";
        }

    }
}
