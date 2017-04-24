package com.blzb.service.impl;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by apimentel on 4/23/17.
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Override
    public ShortUrl saveAndCalculate(ShortUrl shortUrl) {
        String md5Url = DigestUtils.md5Hex(shortUrl.getUrl());
        ShortUrl existing = shortUrlRepository.findByHashKey(md5Url);
        if (existing == null) {
            shortUrl.setHashKey(md5Url);
            shortUrl = shortUrlRepository.save(shortUrl);
            shortUrl.setStringId(Long.toString(Integer.MAX_VALUE - shortUrl.getId(), Character.MAX_RADIX));
            shortUrl = shortUrlRepository.save(shortUrl);
            return shortUrl;
        } else {
            return existing;
        }
    }
}
