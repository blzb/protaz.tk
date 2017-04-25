package com.blzb.data.repository;

import com.blzb.data.dbo.ShortUrl;
import com.blzb.data.dbo.UrlHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by apimentel on 4/23/17.
 */
@RepositoryRestResource
public interface UrlHitRepository extends JpaRepository<UrlHit, Long> {
    Long countByShortUrlId(Integer shortUrlId);
}