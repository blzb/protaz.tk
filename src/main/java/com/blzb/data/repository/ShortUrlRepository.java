package com.blzb.data.repository;

import com.blzb.data.dbo.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by apimentel on 4/23/17.
 */
@RepositoryRestResource
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByHashKey(String hashKey);

    ShortUrl findByStringId(String stringId);
}