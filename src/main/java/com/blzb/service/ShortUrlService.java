package com.blzb.service;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by apimentel on 4/23/17.
 */
public interface ShortUrlService {
    ShortUrl saveAndCalculate(ShortUrl shortUrl);
}
