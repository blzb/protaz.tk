package com.blzb.service;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.data.vo.ShortUrlVo;
import com.blzb.data.vo.TotalsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by apimentel on 4/23/17.
 */
public interface ShortUrlService {
    ShortUrlVo saveAndCalculate(ShortUrl shortUrl, String hostname);

    Page<ShortUrlVo> listAllByPage(Pageable pageable, String hostName);

    Page<ShortUrlVo> list();

    TotalsVo getTotal();
}
