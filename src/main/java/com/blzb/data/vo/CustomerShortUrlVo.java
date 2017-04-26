package com.blzb.data.vo;

/**
 * Created by apimentel on 4/26/17.
 */
public class CustomerShortUrlVo {
    private String customStringId;
    private String url;

    public CustomerShortUrlVo(String customStringId, String url) {
        this.customStringId = customStringId;
        this.url = url;
    }

    public CustomerShortUrlVo(String url) {
        this.url = url;
    }

    public CustomerShortUrlVo() {

    }

    public String getCustomStringId() {
        return customStringId;
    }

    public void setCustomStringId(String customStringId) {
        this.customStringId = customStringId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
