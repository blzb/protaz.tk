package com.blzb;

import com.blzb.data.repository.ShortUrlRepository;
import com.blzb.data.vo.CustomerShortUrlVo;
import com.blzb.data.vo.ShortUrlVo;
import com.blzb.service.IdTakenException;
import com.blzb.service.ShortUrlService;
import com.blzb.service.impl.ShortUrlServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProtaztkApplicationTests {
    @Autowired
    ShortUrlRepository shortUrlRepository;
    @Autowired
    ShortUrlService shortUrlService;

    @Test
    public void urlIsCreated() {
        CustomerShortUrlVo customerShortUrlVo = new CustomerShortUrlVo();
        customerShortUrlVo.setUrl("http://google.com");
        ShortUrlVo response = shortUrlService.saveAndCalculate(customerShortUrlVo, "");
        Assert.assertTrue(response.getShortUrl() != null);

        Assert.assertEquals(customerShortUrlVo.getUrl(), response.getOriginalUrl());
    }

    @Test
    public void customUrlIsCreated() {
        CustomerShortUrlVo customerShortUrlVo = new CustomerShortUrlVo();
        customerShortUrlVo.setUrl("http://google.com");
        customerShortUrlVo.setCustomStringId("googl");
        ShortUrlVo response = shortUrlService.saveAndCalculate(customerShortUrlVo, "");
        Assert.assertTrue(response.getShortUrl() != null);
        Assert.assertEquals("/" + customerShortUrlVo.getCustomStringId(), response);
        Assert.assertEquals(customerShortUrlVo.getUrl(), response.getOriginalUrl());
    }

    @Test(expected = IdTakenException.class)
    public void shouldFailIfCustomIsTaken() {
        CustomerShortUrlVo customerShortUrlVo = new CustomerShortUrlVo();
        customerShortUrlVo.setUrl("http://google.com");
        customerShortUrlVo.setCustomStringId("googl");
        ShortUrlVo response = shortUrlService.saveAndCalculate(customerShortUrlVo, "");
        Assert.assertTrue(response.getShortUrl() != null);
        Assert.assertEquals("/" + customerShortUrlVo.getCustomStringId(), response);
        Assert.assertEquals(customerShortUrlVo.getUrl(), response.getOriginalUrl());
        customerShortUrlVo = new CustomerShortUrlVo();
        customerShortUrlVo.setUrl("http://google.com");
        customerShortUrlVo.setCustomStringId("googl");
        response = shortUrlService.saveAndCalculate(customerShortUrlVo, "");
    }
/*
    @Test
    public void shouldRetryIfTaken() {
        shortUrlService.saveAndCalculate(
                new CustomerShortUrlVo("http://twitter.com", "zik0za"),
                ""
        );
        shortUrlService.saveAndCalculate(
                new CustomerShortUrlVo("http://facebook.com"),
                "zik0zb"
        );

        shortUrlService.saveAndCalculate(new CustomerShortUrlVo("http://skype.com"), "zik0zc");

        shortUrlService.saveAndCalculate(new CustomerShortUrlVo("http://yahoo.com"), "zik0zd");

        shortUrlService.saveAndCalculate(new CustomerShortUrlVo("http://slack.com"), "zik0ze");
        shortUrlService.saveAndCalculate(
                new CustomerShortUrlVo("http://postgres.com"),
                "zik0zf"
        );
        shortUrlService.saveAndCalculate(
                new CustomerShortUrlVo("http://postgres.com"),
                "zik0zg"
        );
        shortUrlService.saveAndCalculate(
                new CustomerShortUrlVo("http://postgres.com"),
                "zik0zh"
        );
        shortUrlService.saveAndCalculate(
                new CustomerShortUrlVo("http://postgres.com"),
                "zik0zi"
        );
        CustomerShortUrlVo test = new CustomerShortUrlVo("http://something.com");
        ShortUrlVo result = shortUrlService.saveAndCalculate(test, "");
        Assert.assertTrue(result.getShortUrl(), "");
    }
    */
}
