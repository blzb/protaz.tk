package com.blzb.data.vo;

/**
 * Created by apimentel on 4/24/17.
 */
public class ShortUrlVo {
    private String originalUrl;
    private String shortUrl;
    private String largerUrl;
    private Long visits;
    private String pageName;
    private String createdAt;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLargerUrl() {
        return largerUrl;
    }

    public void setLargerUrl(String largerUrl) {
        this.largerUrl = largerUrl;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
