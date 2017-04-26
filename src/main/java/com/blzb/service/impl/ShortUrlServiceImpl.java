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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

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
        ShortUrlVo shortUrlVo = getShortUrlVo(hostname, existing);
        return shortUrlVo;
    }

    @Override
    public Page<ShortUrlVo> listAllByPage(Pageable pageable, String hostName) {
        Page<ShortUrl> results = shortUrlRepository.findAll(pageable);
        return results.map(source -> getShortUrlVo(hostName, source));
    }

    private ShortUrlVo getShortUrlVo(String hostname, ShortUrl shortUrl) {
        ShortUrlVo shortUrlVo = new ShortUrlVo();
        shortUrlVo.setOriginalUrl(shortUrl.getUrl());
        shortUrlVo.setShortUrl(hostname + "/" + shortUrl.getStringId());
        shortUrlVo.setLargerUrl(hostname + "/" + shortUrl.getHashKey());
        shortUrlVo.setPageName("Name");
        shortUrlVo.setVisits(urlHitRepository.countByShortUrlId(shortUrl.getId()));
        PrettyTime prettyTime = new PrettyTime();
        shortUrlVo.setCreatedAt(prettyTime.format(shortUrl.getCreatedAt()));
        return shortUrlVo;
    }

    private void cleanUrl(ShortUrl shortUrl) {
        String url = shortUrl.getUrl();
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            shortUrl.setUrl("http://" + url);
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
