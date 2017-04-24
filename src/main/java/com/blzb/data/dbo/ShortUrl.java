package com.blzb.data.dbo;

import javax.persistence.*;

/**
 * Created by apimentel on 4/23/17.
 */
@Entity
public class ShortUrl {
    @Id
    @SequenceGenerator(name="short_url_id_seq",
            sequenceName="short_url_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="short_url_id_seq")
    private Integer id;
    private String url;
    private String hashKey;
    private String stringId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }
}
