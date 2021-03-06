package com.blzb.service.impl;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.data.repository.UrlHitRepository;
import com.blzb.data.vo.CustomerShortUrlVo;
import com.blzb.data.vo.ShortUrlVo;
import com.blzb.data.vo.TotalsVo;
import com.blzb.service.IdTakenException;
import com.blzb.service.ShortUrlService;
import com.blzb.service.UnacceptableUrlException;
import org.apache.commons.validator.routines.UrlValidator;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by apimentel on 4/23/17.
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    private UrlHitRepository urlHitRepository;
    private static final int MAX_APPENDS = 6;

    Random random = new Random();
    String[] schemes = {"http","https"};
    UrlValidator urlValidator = new UrlValidator(schemes);
    @Override
    public ShortUrlVo saveAndCalculate(CustomerShortUrlVo customerShortUrlVo, String hostname) {
        ShortUrl shortUrl = getEntity(customerShortUrlVo);
        if(!urlValidator.isValid(shortUrl.getUrl())){
            throw new UnacceptableUrlException();
        }
        ShortUrlVo shortUrlVo;
        if (shortUrl.isCustom()) {
            shortUrlVo = saveCustomUrl(hostname, shortUrl);
        } else {
            shortUrlVo = generateShortUrl(hostname, shortUrl);
        }
        return shortUrlVo;
    }

    private ShortUrlVo saveCustomUrl(String hostname, ShortUrl shortUrl) {
        ShortUrl existing = shortUrlRepository.findByStringId(shortUrl.getStringId());
        if (null == existing) {
            return getShortUrlVo(hostname, shortUrlRepository.save(shortUrl));
        } else {
            throw new IdTakenException();
        }
    }

    private ShortUrlVo generateShortUrl(String hostname, ShortUrl shortUrl) {
        String md5Url = DigestUtils.md5Hex(shortUrl.getUrl());
        ShortUrl existing = shortUrlRepository.findByHashKey(md5Url);
        if (existing == null) {
            shortUrl.setHashKey(md5Url);
            shortUrl = shortUrlRepository.save(shortUrl);
            shortUrl.setStringId(Long.toString(Integer.MAX_VALUE - shortUrl.getId(), Character.MAX_RADIX));
            boolean assignedId= retryIdGeneration(shortUrl);
            if (!assignedId) {
                shortUrl.setStringId(shortUrl.getHashKey());
                retryIdGeneration(shortUrl);
            }
            if(assignedId){
                shortUrl = shortUrlRepository.save(shortUrl);
            } else{
                throw new IdTakenException();
            }

            existing = shortUrl;
        }
        return getShortUrlVo(hostname, existing);
    }

    private boolean retryIdGeneration(ShortUrl shortUrl) {
        int counter;
        boolean assignedId = true;
        counter = 0;
        while (shortUrlRepository.findByStringId(shortUrl.getStringId()) != null) {
            char c = (char) (random.nextInt(26) + 'a');
            shortUrl.setStringId(shortUrl.getStringId() + c);
            counter++;
            if(counter > MAX_APPENDS){
                assignedId = false;
                break;
            }
        }
        return assignedId;
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

    private ShortUrl getEntity(CustomerShortUrlVo customerShortUrlVo) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setUrl(customerShortUrlVo.getUrl());
        String url = shortUrl.getUrl();
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            shortUrl.setUrl("http://" + url);
        }
        if (null != customerShortUrlVo.getCustomStringId()) {
            shortUrl.setStringId(customerShortUrlVo.getCustomStringId());
            shortUrl.setCustom(true);
        } else {
            shortUrl.setCustom(false);
        }
        return shortUrl;
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
