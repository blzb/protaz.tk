package com.blzb.data.dbo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by apimentel on 4/25/17.
 */
@Entity
public class UrlHit {
    @Id
    @SequenceGenerator(name = "url_hit_id_seq",
            sequenceName = "url_hit_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "url_hit_id_seq")
    private Integer id;
    private Integer shortUrlId;
    private String language;
    private String userAgent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    private String clientIp;

    public Integer getShortUrlId() {
        return shortUrlId;
    }

    public void setShortUrlId(Integer shortUrlId) {
        this.shortUrlId = shortUrlId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
