package org.timsoft.api.cache;

import javax.annotation.PostConstruct;
import javax.cache.CacheManager;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

/** Ensures CacheManager is initialized at application startup. */
@Singleton
@Startup
@Slf4j
public class CacheInitializer {

  @Inject private CacheManager cacheManager;

  @PostConstruct
  public void init() {
    log.info("CacheInitializer: Forcing CacheManager initialization");
    log.info("CacheInitializer: Available caches: {}", cacheManager.getCacheNames());
  }
}
