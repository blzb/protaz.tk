package com.blzb.service.impl;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.data.repository.UrlHitRepository;
import com.blzb.data.vo.ShortUrlVo;
import com.blzb.data.vo.TotalsVo;
import com.blzb.service.ShortUrlService;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

/**
 * Created by apimentel on 4/23/17.
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    private UrlHitRepository urlHitRepository;

    @Override
    public ShortUrlVo saveAndCalculate(ShortUrl shortUrl, String hostname) {
        cleanUrl(shortUrl);
        String md5Url = DigestUtils.md5Hex(shortUrl.getUrl());
        ShortUrl existing = shortUrlRepository.findByHashKey(md5Url);
        if (existing == null) {
            shortUrl.setHashKey(md5Url);
            shortUrl = shortUrlRepository.save(shortUrl);
            shortUrl.setStringId(Long.toString(Integer.MAX_VALUE - shortUrl.getId(), Character.MAX_RADIX));
            shortUrl = shortUrlRepository.save(shortUrl);
            existing = shortUrl;
        }
        ShortUrlVo shortUrlVo = new ShortUrlVo();
        shortUrlVo.setOriginalUrl(existing.getUrl());
        shortUrlVo.setShortUrl(hostname + "/" + existing.getStringId());
        shortUrlVo.setLargerUrl(hostname + "/" + existing.getHashKey());
        shortUrlVo.setPageName("Name");
        shortUrlVo.setVisits(urlHitRepository.countByShortUrlId(existing.getId()));
        PrettyTime prettyTime = new PrettyTime();
        shortUrlVo.setCreatedAt(prettyTime.format(existing.getCreatedAt()));
        return shortUrlVo;
    }

    private void cleanUrl(ShortUrl shortUrl) {
        String url = shortUrl.getUrl();
        if(!(url.startsWith("http://") || url.startsWith("https://"))){
            shortUrl.setUrl("http://"+url);
        }
    }

    @Override
    public Page<ShortUrlVo> list() {
        return new PageImpl<ShortUrlVo>(new ArrayList<>());
    }

    @Override
    public TotalsVo getTotal() {
        Long urlCount = shortUrlRepository.count();
        TotalsVo totalsVo = new TotalsVo();
        totalsVo.setTotalLinks(urlCount);
        return totalsVo;
    }
}
